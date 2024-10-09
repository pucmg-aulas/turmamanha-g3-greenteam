package CodigoJavaParkings;

import org.junit.Test;
import static org.junit.Assert.*;

public class VagaTest {

    @Test
    public void testOcuparVaga() {
        Vaga vaga = new VagaRegular("A01");
        Veiculo veiculo = new Veiculo("ABC1234");
        vaga.ocuparVaga(veiculo);
        assertTrue(vaga.isOcupada());
        assertEquals(veiculo, vaga.getVeiculo());
        assertNotNull(vaga.getTempoInicial());
    }

    @Test
    public void testLiberarVaga() {
        Vaga vaga = new VagaRegular("A01");
        Veiculo veiculo = new Veiculo("ABC1234");
        vaga.ocuparVaga(veiculo);
        vaga.liberarVaga();
        assertFalse(vaga.isOcupada());
        assertNull(vaga.getVeiculo());
        assertNotNull(vaga.getTempoFinal());
    }
}
