package CodigoJavaParkings;

import org.junit.Test;
import static org.junit.Assert.*;

public class VagaVIPTest {

    @Test
    public void testCalcularTarifa() {
        Vaga vaga = new VagaVIP("D01");
        double tarifa = vaga.calcularTarifa();
        assertEquals(12.0, tarifa, 0.001); // Verifica se o acréscimo de 20% foi aplicado
    }

    @Test
    public void testCaracteristicasVIP() {
        VagaVIP vaga = new VagaVIP("D02");
        assertTrue(vaga.isCoberta()); // Verifica se a vaga VIP é coberta
        assertTrue(vaga.isProximaPortao()); // Verifica se a vaga VIP é próxima ao portão
    }
}
