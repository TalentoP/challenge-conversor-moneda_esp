package challenge.conversor.util;

import challenge.conversor.exception.CurrencyNotFoundException;
import challenge.conversor.logic.CurrencyConverterLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Conversor {
    private final CurrencyConverterLogic logic = new CurrencyConverterLogic();
    private final List<ConversionHistoryEntry> history = new ArrayList<>();

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> convert("USD", "ARS", scanner);
                case 2 -> convert("ARS", "USD", scanner);
                case 3 -> convert("USD", "BRL", scanner);
                case 4 -> convert("BRL", "USD", scanner);
                case 5 -> convert("USD", "COP", scanner);
                case 6 -> convert("COP", "USD", scanner);
                case 7 -> {
                    System.out.print("Moneda origen (ej. USD): ");
                    String from = scanner.nextLine().toUpperCase();
                    System.out.print("Moneda destino (ej. MXN): ");
                    String to = scanner.nextLine().toUpperCase();
                    convert(from, to, scanner);
                }
                case 8 -> {
                    System.out.println("Historial de conversiones:");
                    history.forEach(System.out::println);
                }
                case 9 -> {
                    running = false;
                    System.out.println("Gracias por usar el conversor. ¡Hasta luego!");
                }
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }

            System.out.println();
        }

        scanner.close();
    }

    private void convert(String from, String to, Scanner scanner) {
        System.out.print("Ingrese el valor que deseas convertir: ");
        double amount = readAmount(scanner);

        try {
            double result = logic.getConvertedAmount(from, to, amount);

            history.add(new ConversionHistoryEntry(from, to, amount, result));
            System.out.printf("El valor %.2f [%s] corresponde al valor final de =>>> %.2f [%s]\n",
                    amount, from, result, to);
        } catch (CurrencyNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        }
    }

    private double readAmount(Scanner scanner) {
        double amount = -1;
        boolean valid = false;

        while (!valid) {
            System.out.print("Ingrese el valor que deseas convertir: ");

            if (scanner.hasNextDouble()) {
                amount = scanner.nextDouble();
                scanner.nextLine();
                if (amount >= 0) {
                    valid = true;
                } else {
                    System.out.println("El valor debe ser mayor o igual a 0.");
                }
            } else {
                System.out.println("Entrada inválida. Por favor ingrese un número (ej: 25.5).");
                scanner.nextLine();
            }
        }

        return amount;
    }

    private void printMenu() {
        System.out.println(
                """
                        **************************************************
                        Sea bienvenido/a al Conversor de Moneda =]
                        1) Dólar =>> Peso argentino"
                        2) Peso argentino =>> Dólar
                        3) Dólar =>> Real brasileño
                        4) Real brasileño =>> Dólar
                        5) Dólar =>> Peso colombiano
                        6) Peso colombiano =>> Dólar
                        7) Usar conversión distinta
                        8) Ver historial de conversiones
                        9) Salir
                        Elija una opción válida
                        **************************************************"""
        );
    }
}
