
package alkewallet;

public class Cuenta {

    private double saldo;
    private Moneda moneda;

    public Cuenta() {
    this.saldo = 0;
    this.moneda = Moneda.CLP;
    }

    public double getSaldo() {
        return saldo;
    }

    public Moneda getMoneda() {
    return moneda;
    }

    public void ingresarDinero(double monto) {
        if (monto > 0) {
            saldo = saldo + monto;
        }
    }

    public void cambiarMoneda(Moneda nuevaMoneda, double nuevoSaldo) {
        this.moneda = nuevaMoneda;
        this.saldo = nuevoSaldo;
    }

    public boolean retirarDinero(double monto) {
        if (monto > 0 && monto <= saldo) {
            saldo = saldo - monto;
            return true;
        }
        return false;
    }
}
