package CodigoJavaParkings;

import org.junit.Test;
import static org.junit.Assert.*;

public class EstacionamentoTest {

    @Test
    public void testCriarEstacionamento() {
        Estacionamento estacionamento = new Estacionamento("Estacionamento Central", 40);
        assertEquals("Estacionamento Central", estacionamento.getNome());
        assertEquals(40, estacionamento.getVagas().size());
    }

    @Test
    public void testEncontrarVagaLivre() {
        Estacionamento estacionamento = new Estacionamento("Estacionamento Central", 2);
        Vaga vagaLivre = estacionamento.encontrarVagaLivre();
        assertNotNull(vagaLivre); // Deve retornar uma vaga disponível
        assertFalse(vagaLivre.isOcupada()); // A vaga deve estar desocupada
    }

}
