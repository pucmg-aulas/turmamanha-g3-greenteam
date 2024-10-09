package CodigoJavaParkings;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class VeiculoTest {

    @Test
    public void testCriarVeiculoComPlaca() {
        Veiculo veiculo = new Veiculo("ABC1234");
        assertEquals("ABC1234", veiculo.getPlaca());
    }

    @Test
    public void testAlterarPlacaVeiculo() {
        Veiculo veiculo = new Veiculo("XYZ9876");
        veiculo.setPlaca("LMN5678");
        assertEquals("LMN5678", veiculo.getPlaca());
    }
}
