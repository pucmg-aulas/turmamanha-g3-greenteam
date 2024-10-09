package CodigoJavaParkings;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Date;

public class FaturaTest {

    @Test
    public void testCriarFatura() {
        Veiculo veiculo = new Veiculo("ABC1234");
        Date inicio = new Date();
        Date fim = new Date();
        Fatura fatura = new Fatura(veiculo, inicio, fim);
        assertEquals(veiculo, fatura.getVeiculo());
        assertEquals(inicio, fatura.getTempoInicio());
        assertEquals(fim, fatura.getTempoFim());
    }

    @Test
    public void testCalcularCobranca() {
        Veiculo veiculo = new Veiculo("XYZ9876");
        Fatura fatura = new Fatura(veiculo, new Date(), new Date());
        double valor = 10.0;
        double valorLimite = 2.0;
        double total = fatura.calcularCobranca(valor, valorLimite);
        assertEquals(20.0, total, 0.001); // Verifica se o cálculo está correto
    }
}
