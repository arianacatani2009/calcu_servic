import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class cliente {
    public static void main(String[] args) {
        try {
            // Buscar el registro RMI
            Registry registro = LocateRegistry.getRegistry("localhost", 1099);

            // Buscar la implementación de la calculadora en el registro RMI
            interfaz calculadora = (interfaz) registro.lookup("ClienteRemoto");

            // Crear un escáner para leer la entrada del usuario
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Elige una operación: sumar, restar, multiplicar, dividir o salir:");
                String operacion = scanner.nextLine();

                if (operacion.equalsIgnoreCase("salir")) {
                    break;
                }

                System.out.print("Introduce el primer número: ");
                double num1 = scanner.nextDouble();

                System.out.print("Introduce el segundo número: ");
                double num2 = scanner.nextDouble();
                scanner.nextLine(); // Consumir el salto de línea

                double resultado = 0;
                boolean operacionValida = true;

                switch (operacion.toLowerCase()) {
                    case "sumar":
                        resultado = calculadora.sumar(num1, num2);
                        break;
                    case "restar":
                        resultado = calculadora.restar(num1, num2);
                        break;
                    case "multiplicar":
                        resultado = calculadora.multiplicar(num1, num2);
                        break;
                    case "dividir":
                        try {
                            resultado = calculadora.dividir(num1, num2);
                        } catch (ArithmeticException e) {
                            System.out.println("Error: " + e.getMessage());
                            operacionValida = false;
                        }
                        break;
                    default:
                        System.out.println("Operación no válida.");
                        operacionValida = false;
                        break;
                }

                if (operacionValida) {
                    System.out.println("Resultado: " + resultado);
                }
            }

            scanner.close();
        } catch (Exception e) {
            System.err.println("Error en el cliente de Calculadora: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
