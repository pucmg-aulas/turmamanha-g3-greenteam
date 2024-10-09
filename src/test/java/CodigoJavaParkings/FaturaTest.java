package CodigoJavaParkings;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Date;

public class FaturaTest {

    @Test
    public void testCriacaoFatura() {
        // Configuração dos dados de teste
        Veiculo veiculo = new Veiculo("ABC1234");
        Date inicio = new Date();
        Date fim = new Date(inicio.getTime() + 3600 * 1000); // 1 hora depois

        // Criação de uma nova fatura
        Fatura fatura = new Fatura(veiculo, inicio, fim);

        // Verificação dos resultados
        assertEquals(veiculo, fatura.getVeiculo());
        assertEquals(inicio, fatura.getTempoInicio());
        assertEquals(fim, fatura.getTempoFim());
        assertNull(fatura.getValorLimite()); // o valor limite não foi definido
    }

    @Test
    public void testAtualizacaoValorLimite() {
        // Configuração dos dados de teste
        Veiculo veiculo = new Veiculo("XYZ5678");
        Date inicio = new Date();
        Date fim = new Date(inicio.getTime() + 1800 * 1000); // 30 minutos depois
        Double valor = 5.0;

        // Criação de uma nova fatura e atualização do valor limite
        Fatura fatura = new Fatura(veiculo, inicio, fim);

        // Verificação dos resultados
        assertEquals(veiculo, fatura.getVeiculo());
        assertEquals(inicio, fatura.getTempoInicio());
        assertEquals(fim, fatura.getTempoFim());
    }
}
