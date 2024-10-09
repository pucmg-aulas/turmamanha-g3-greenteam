package CodigoJavaParkings;

import org.junit.Test;
import static org.junit.Assert.*;

public class VagaIdosoTest {

    @Test
    public void testCalcularTarifa() {
        Vaga vaga = new VagaIdoso("A01");
        double tarifa = vaga.calcularTarifa();
        assertEquals(8.5, tarifa, 0.001); // Verifica se o desconto de 15% foi aplicado
    }
}
