package alkewallet.interfaces;

public interface ConversorMoneda {


  double convertir(double monto, String monedaDestino);

  double obtenerTipoCambio(String moneda);
}
