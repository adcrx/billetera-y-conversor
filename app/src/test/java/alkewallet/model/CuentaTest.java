package alkewallet.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CuentaTest {

    @Test
    void ingresarDinero_deberiaSumarAlSaldo() {
        Cuenta cuenta = new Cuenta();

        cuenta.ingresarDinero(10000);

        assertEquals(10000.0, cuenta.getSaldo());
    }

    @Test
    void ingresarDinero_noDeberiaAceptarMontoCeroONegativo() {
        Cuenta cuenta = new Cuenta();

        cuenta.ingresarDinero(0);
        cuenta.ingresarDinero(-5000);

        assertEquals(0.0, cuenta.getSaldo());
    }

    @Test
    void retirarDinero_deberiaRestarSiHaySaldoSuficiente() {
        Cuenta cuenta = new Cuenta();
        cuenta.ingresarDinero(10000);

        boolean retiroExitoso = cuenta.retirarDinero(3000);

        assertTrue(retiroExitoso);
        assertEquals(7000.0, cuenta.getSaldo());
    }

    @Test
    void retirarDinero_noDeberiaPermitirRetirarMasDelSaldo() {
        Cuenta cuenta = new Cuenta();
        cuenta.ingresarDinero(2000);

        boolean retiroExitoso = cuenta.retirarDinero(3000);

        assertFalse(retiroExitoso);
        assertEquals(2000.0, cuenta.getSaldo());
    }
}
