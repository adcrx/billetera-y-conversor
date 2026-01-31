package alkewallet;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("~~~~ALKE WALLET~~~~");
        System.out.print("Ingrese su nombre: ");
        String nombreUsuario = scanner.nextLine();
        Cuenta cuenta = new Cuenta();
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            int opcion = obtenerOpcion(scanner);

            switch (opcion) {
                case 1:
                    System.out.println("Saldo actual: $" + cuenta.getSaldo() + " " + cuenta.getMoneda());
                    break;
                case 2:
                    System.out.print("Ingrese monto a ingresar: ");
                    double montoIngreso = obtenerMonto(scanner);
                    cuenta.ingresarDinero(montoIngreso);
                    System.out.println("Saldo actual: $" + cuenta.getSaldo());
                    break;
                case 3:
                    System.out.print("Ingrese monto a retirar: ");
                    double montoRetiro = obtenerMonto(scanner);
                    boolean retiroExitoso = cuenta.retirarDinero(montoRetiro);

                    if (retiroExitoso) {
                     System.out.println("Retiro realizado con éxito.");
                     System.out.println("Saldo actual: $" + cuenta.getSaldo());
                    } else {
                     System.out.println("No se pudo realizar el retiro.");
                     System.out.println("El monto ingresado excede el máximo de su saldo.");
                    }
                    break;
               case 4:
                    System.out.println("Moneda actual: " + cuenta.getMoneda());
                    System.out.print("Ingrese moneda destino (CLP/USD/EUR): ");
                    Moneda monedaDestino = obtenerMoneda(scanner);

                    Conversor conversor = new Conversor();
                    double saldoConvertido = conversor.convertir(cuenta.getSaldo(), cuenta.getMoneda(), monedaDestino);

                    cuenta.cambiarMoneda(monedaDestino, saldoConvertido);

                    System.out.println("Conversión realizada.");
                    System.out.println("Saldo actual: $" + cuenta.getSaldo() + " " + cuenta.getMoneda());
                     break;
                case 5:
                    System.out.println("¡Hasta luego, " + nombreUsuario + "!");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }

        scanner.close();
    }

    public static void mostrarMenu() {
        System.out.println("\n----- MENÚ -----");
        System.out.println("1. Ver saldo");
        System.out.println("2. Ingresar dinero");
        System.out.println("3. Retirar dinero");
        System.out.println("4. Convertir moneda");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public static int obtenerOpcion(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Por favor, ingrese un número válido: ");
            scanner.next();
        }
        int opcion = scanner.nextInt();
        scanner.nextLine();
        return opcion;
    }

    public static double obtenerMonto(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            System.out.print("Por favor, ingrese un monto válido: ");
            scanner.next();
        }
        double monto = scanner.nextDouble();
        scanner.nextLine();
        return monto;
    }

    public static Moneda obtenerMoneda(Scanner scanner) {
    while (true) {
        String texto = scanner.nextLine().trim().toUpperCase();

        try {
            return Moneda.valueOf(texto);
        } catch (IllegalArgumentException e) {
            System.out.print("Moneda inválida. Ingrese CLP, USD o EUR: ");
            }
          }
    }
}
