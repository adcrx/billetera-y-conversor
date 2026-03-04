package alkewallet;

import java.util.Scanner;

import alkewallet.model.Wallet;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("================================");
        System.out.println("   BIENVENIDO A ALKE WALLET   ");
        System.out.println("================================");

        String nombre = "";

        while (nombre.isEmpty()) {
            System.out.print("\nIngresa tu nombre: ");
            nombre = scanner.nextLine().trim();

            if (nombre.isEmpty()) {
                System.out.println(" El nombre no puede estar vacío. Por favor, ingrese su nombre");
            }
        }

        System.out.println("Hola " + nombre);

        String moneda = "";
        while (!moneda.equals("CLP") && !moneda.equals("USD") && !moneda.equals("EUR")) {
            System.out.print("\nIngresa la moneda en que desea que esté su cuenta (CLP, USD, EUR): ");
            moneda = scanner.nextLine().toUpperCase().trim();

            if (!moneda.equals("CLP") && !moneda.equals("USD") && !moneda.equals("EUR")) {
                System.out.println(" Moneda inválida. Las opciones son: CLP, USD, EUR");
            }
        }

        Wallet miWallet = new Wallet(nombre, moneda);
        System.out.println("\n¡CUENTA CREADA EXITOSAMENTE!");
        System.out.println("   Titular: " + nombre);
        System.out.println("   Moneda: " + moneda);
        System.out.println("   Saldo inicial: $" + miWallet.consultarSaldo() + " " + moneda);

        int opcion;
        do {
            System.out.println("\n═════════════════════════════");
            System.out.println("          MENÚ PRINCIPAL     ");
            System.out.println("═════════════════════════════");
            System.out.println("1.  Depositar dinero");
            System.out.println("2.  Retirar dinero");
            System.out.println("3.  Consultar saldo");
            System.out.println("4.  Convertir moneda");
            System.out.println("0.  Salir");
            System.out.print("Selecciona una opción: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Error: El número que ingresaste no corresponde a ninguna opción");
                scanner.next();
                System.out.print("Selecciona una opción: ");
            }
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el monto a depositar:");
                    double montoDeposito;

                    while (true) {
                        while (!scanner.hasNextDouble()) {
                            System.out.println(" Debes ingresar un monto válido. Ejemplo: 5000");
                            scanner.next();
                            System.out.print(" Ingresa el monto a depositar: ");
                        }

                        montoDeposito = scanner.nextDouble();
                        scanner.nextLine();

                        if (montoDeposito > 0) {
                            break;
                        } else {
                            System.out.println(" El monto debe ser mayor que cero. Intenta de nuevo.");
                            System.out.print(" Ingresa el monto a depositar: ");
                        }
                    }

                    miWallet.depositar(montoDeposito);

                    break;
                case 2:
                    double montoRetiro;
                    boolean retiroExitoso = false;

                    while (!retiroExitoso) {
                        System.out.print("Ingrese el monto a retirar: ");

                        while (!scanner.hasNextDouble()) {
                            System.out.println(" Debes ingresar un monto válido. Ejemplo: 5000");
                            scanner.next();
                            System.out.print(" Ingresa el monto a retirar: ");
                        }

                        montoRetiro = scanner.nextDouble();
                        scanner.nextLine();

                        if (montoRetiro <= 0) {
                            System.out.println(" El monto debe ser mayor que cero. Intenta de nuevo.");
                            continue;
                        }

                        if (montoRetiro > miWallet.consultarSaldo()) {
                            System.out.println(" No tienes suficiente saldo.");
                            System.out.println(" Saldo disponible: $" + miWallet.consultarSaldo() + " " + moneda);
                            continue;
                        }

                        miWallet.retirar(montoRetiro);
                        retiroExitoso = true;
                    }
                    break;

                case 3:

                    System.out.println("Su saldo es: " + miWallet.consultarSaldo() + moneda);

                    break;

                case 4:
                    double saldoActual = miWallet.consultarSaldo();
                    System.out.println("Su saldo es: $" + saldoActual + " " + moneda);

                    while (true) {
                        System.out
                                .println("Ingrese el monto a convertir. Si desea convertir el total, presione ENTER.");
                        System.out.print("Ingrese el monto a convertir (o ENTER para todo): ");

                        String entradaMonto = scanner.nextLine().trim();
                        double montoConvertir;

                        if (entradaMonto.isEmpty()) {
                            montoConvertir = saldoActual;
                            System.out.println("Convertirá TODO su saldo: $" + montoConvertir + " " + moneda);
                        } else {
                            try {
                                montoConvertir = Double.parseDouble(entradaMonto);
                            } catch (NumberFormatException e) {
                                System.out.println("Error: Debes ingresar un número válido");
                                continue;
                            }
                            if (montoConvertir <= 0) {
                                System.out.println("El monto debe ser mayor que cero.");
                                continue;
                            }
                            if (montoConvertir > saldoActual) {
                                System.out.println(
                                        "No tienes suficiente saldo. Disponible: $" + saldoActual + " " + moneda);
                                continue;
                            }
                        }
                        System.out.print("Ingresa la moneda destino (USD, EUR): ");
                        String destino = scanner.nextLine().toUpperCase();

                        double resultado = miWallet.convertir(montoConvertir, destino);

                        if (resultado != -1.0) {
                            System.out.println("Conversión exitosa!");
                            break;
                        }

                    }
                    break;
            }

        }
        while (opcion != 0);
        scanner.close();

}}
