import java.util.Scanner;

public class Menu {
    private final RpnCalculator calculator = new RpnCalculator();
    private final Scanner scanner = new Scanner(System.in);

    public void show() {
        System.out.println("=== Calculadora RPN ===");
        
        boolean running = true;

        while (running) {
            System.out.print("\nIngrese expresión RPN (o 'salir' para terminar): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("salir")) {
                running = false;
                System.out.println("¡Hasta luego!");
            } else {
                try {
                    int resultado = calculator.evaluate(input);
                    System.out.println("Resultado: " + resultado);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
    }
}
