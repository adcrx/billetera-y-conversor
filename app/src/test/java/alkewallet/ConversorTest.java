package alkewallet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ConversorTest {

    @Test
    void convertir_mismaMoneda_deberiaDevolverMismoMonto() {
        Conversor conversor = new Conversor();
        double resultado = conversor.convertir(10000, Moneda.CLP, Moneda.CLP);

        assertEquals(10000.0, resultado);
    }

    @Test
    void convertir_deberiaCambiarElMontoSiLaMonedaEsDistinta() {
        Conversor conversor = new Conversor();
        double resultado = conversor.convertir(9500, Moneda.CLP, Moneda.USD);

        assertTrue(resultado > 0);
        assertNotEquals(9500.0, resultado);
    }
}
