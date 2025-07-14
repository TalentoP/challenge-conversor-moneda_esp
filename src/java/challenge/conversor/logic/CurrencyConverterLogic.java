package challenge.conversor.logic;

import challenge.conversor.exception.CurrencyNotFoundException;
import challenge.conversor.models.ExchangeRateResponse;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyConverterLogic {
    private static final String API_KEY = "45137bdd1ea9fef78069a250"; // Replace this
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    private final Gson gson = new Gson();
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public double getConvertedAmount(String from, String to, double amount) {
        try {
            String url = BASE_URL + API_KEY + "/latest/" + from;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 404) {
                throw new CurrencyNotFoundException("Moneda base inválida: " + from);
            }

            if (response.statusCode() != 200) {
                throw new RuntimeException("HTTP error code: " + response.statusCode());
            }

            ExchangeRateResponse rateResponse = gson.fromJson(response.body(), ExchangeRateResponse.class);

            Double rate = rateResponse.getConversion_rates().get(to);
            if (rate == null) {
                throw new CurrencyNotFoundException("Moneda destino inválida: " + to);
            }

            return amount * rate;

        } catch (CurrencyNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error general al consultar la API: " + e.getMessage(), e);
        }
    }
}
