package alkewallet.model;

import java.util.Map;
import java.util.HashMap;
import alkewallet.interfaces.*;

public class Wallet implements ConversorMoneda, OperacionesCuenta {

  private String titular;
  private double saldo;
  private String moneda;
  private Map<String, Double> tiposCambio;


  public Wallet(String titular, String moneda) {
    this.titular = titular;
    this.moneda = moneda;
    this.saldo = 0.0;
    this.tiposCambio = new HashMap<>();
  }


  @Override

  public void depositar(double monto) {
    if (monto <= 0) {
      System.out.println("Error: El monto a depositar debe ser mayor que cero.");
      return;

      this.saldo = this.saldo + monto;

      System.out.println("Monto depositado: " + monto);
      System.out.println("Su nuevo saldo es: " + this.saldo);
    }
  }

  public double consultarSaldo() {
    System.out.println("Su saldo es: " + this.saldo + " " + this.moneda);
    return this.saldo;
  }

  public boolean retirar(double monto) {
    System.out.println("Ingrese el monto a retirar");
    if (monto <= 0) {
      System.out.println("El monto a retirar debe ser mayor que cero.");
      return false;
    }
    if (monto > this.saldo) {
      System.out.println("El monto a retirar es mayor que el saldo de la cuenta");
      return false;
    }

    this.saldo = this.saldo - monto;
    System.out.println("Retiro exitoso. Su nuvo saldo es: " + this.saldo + " " + this.moneda);
    return true;
  }

  public double obtenerTipoCambio(String moneda) {

    if (moneda == null || moneda.trim().isEmpty()) {
      System.out.println("Debes especificar una moneda");
      return -1.0;

    moneda = moneda.toUpperCase();

    if(!this.tiposCambio.containsKey(moneda)){
      System.out.println("No tenemos tipo de cambio para: " + moneda);
      System.out.println("Monedas disponibles para convertir: " + this.tiposCambio.keySet());
      return -1.0;
    }

    double tiposCambio = this.tiposCambio.get(moneda);


  }

  public double convertir(double monto, String monedaDestino) {
    if (monto <= 0) {
      System.out.println("El monto ingresado debe ser mayor que cero.");
    }
    if (monto < this.saldo) {
      System.out.println("El monto ingresado debe ser menor o igual a su saldo");
      System.out.println("Usted ingresó: " + this.saldo + this.moneda);
      return -1.0;
    }
    if (monedaDestino == null || monedaDestino.trim().isEmpty()) {
      System.out.println("Debes ingresar una moneda para convertir");
      return -1.0;
    }

    monedaDestino = monedaDestino.toUpperCase();

    double tipoCambio = obtenerTipoCambio(monedaDestino);

    if (monedaDestino.equals(monedaDestino)) {
      System.out.println("Elija un tipo de moneda diferente al tipo de moneda actual.");
      return -1.0;
    }

    double montoConvertido = monto / tipoCambio;
    System.out.println("   $" + monto + " " + this.moneda + " = $" + montoConvertido + " " + monedaDestino);
    return montoConvertido;
  }
}
