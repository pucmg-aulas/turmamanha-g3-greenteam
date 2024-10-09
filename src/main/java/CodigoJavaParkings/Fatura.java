package CodigoJavaParkings;

import java.util.Date;

public class Fatura {
    private Veiculo veiculo;
    private Date tempoInicio;
    private Date tempoFim;
    private Double valor;
    private Double valorLimite;

    public Date getTempoInicio() {
        return tempoInicio;
    }

    public void setTempoInicio(Date tempoInicio) {
        this.tempoInicio = tempoInicio;
    }

    public Date getTempoFim() {
        return tempoFim;
    }

    public void setTempoFim(Date tempoFim) {
        this.tempoFim = tempoFim;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getValorLimite() {
        return valorLimite;
    }

    public void setValorLimite(Double valorLimite) {
        this.valorLimite = valorLimite;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Fatura(Veiculo veiculo, Date tempoInicio, Date tempoFim) {
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
