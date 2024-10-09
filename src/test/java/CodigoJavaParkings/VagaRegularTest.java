package CodigoJavaParkings;

import org.junit.Test;
import static org.junit.Assert.*;

public class VagaRegularTest {

    @Test
    public void testCalcularTarifa() {
        Vaga vaga = new VagaRegular("C01");
        double tarifa = vaga.calcularTarifa();
        assertEquals(10.0, tarifa, 0.001); // Verifica se a tarifa base permanece inalterada
    }
}
