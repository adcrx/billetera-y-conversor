package alkewallet.interfaces;

public interface OperacionesCuenta {

  void depositar(double monto);
  boolean retirar(double monto);
  double consultarSaldo();

}
