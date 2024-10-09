package CodigoJavaParkings;

import org.junit.Test;
import static org.junit.Assert.*;

public class VagaPCDTest {

    @Test
    public void testCalcularTarifa() {
        Vaga vaga = new VagaPCD("B01");
        double tarifa = vaga.calcularTarifa();
        assertEquals(8.7, tarifa, 0.001); // Verifica se o desconto de 13% foi aplicado
    }

    @Test
    public void testEspacoAdicional() {
        VagaPCD vaga = new VagaPCD("B02");
        assertTrue(vaga.isEspacoAdicional()); // Verifica se a vaga PCD tem espaço adicional
    }
}
