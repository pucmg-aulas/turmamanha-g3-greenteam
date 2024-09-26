package JavaLabExercicio;

import java.util.Date;

public class Fatura {
    private Veiculos veiculo;
    private Date tempoInicio;
    private Date tempoFim;
    private Double valor;
    private Double valorLimite;

    public Fatura(Veiculos veiculo, Date tempoInicio, Date tempoFim) {
        this.veiculo = veiculo;
        this.tempoInicio = tempoInicio;
        this.tempoFim = tempoFim;
    }
    public double calcularCobranca(double valor, double valorLimite) {
        this.valor = valor;
        this.valorLimite = valorLimite;

        return valor * valorLimite;
    }
}
