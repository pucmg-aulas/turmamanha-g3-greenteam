package CodigoJavaParkings;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.List;

public class ClienteTest {

    @Test
    public void testCriarCliente() {
        Cliente cliente = new Cliente("123", "João");
        assertEquals("123", cliente.getId());
        assertEquals("João", cliente.getNome());
        assertEquals(0, cliente.getVeiculos().size());
    }

    @Test
    public void testAdicionarVeiculo() {
        Cliente cliente = new Cliente("123", "João");
        Veiculo veiculo = new Veiculo("ABC1234");
        cliente.adicionarVeiculo(veiculo);
        List<Veiculo> veiculos = cliente.getVeiculos();
        assertEquals(1, veiculos.size());
        assertEquals("ABC1234", veiculos.get(0).getPlaca());
    }
}

