package challenge.conversor.util;

import java.time.LocalDateTime;

public class ConversionHistoryEntry {
    private final String fromCurrency;
    private final String toCurrency;
    private final double originalAmount;
    private final double convertedAmount;
    private final LocalDateTime timestamp;

    public ConversionHistoryEntry(String from, String to, double original, double converted) {
        this.fromCurrency = from;
        this.toCurrency = to;
        this.originalAmount = original;
        this.convertedAmount = converted;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f [%s] => %.2f [%s]",
                timestamp, originalAmount, fromCurrency, convertedAmount, toCurrency);
    }
}
