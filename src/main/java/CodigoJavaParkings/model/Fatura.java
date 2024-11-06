package model;

import java.util.Date;

public class Fatura {
    private Veiculo veiculo;
    private Date tempoInicio;
    private Date tempoFim;
    private Double valor;
    private Double valorLimite;  // Parece que este campo não está sendo utilizado na lógica da fatura

    // Construtor para inicializar a Fatura com veículo e datas de início e fim
    public Fatura(Veiculo veiculo, Date tempoInicio, Date tempoFim) {
        this.veiculo = veiculo;
        this.tempoInicio = tempoInicio;
        this.tempoFim = tempoFim;
        this.valor = 0.0; // Valor inicial da fatura
        this.valorLimite = 0.0; // Se for necessário, use este campo para definir algum tipo de limite
    }

    // Getters e Setters
    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

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

    // Método para calcular o valor da fatura com base no tempo de permanência
    public void calcularValor() {
        // Exemplo de cálculo: valor por hora
        long tempoDePermanencia = tempoFim.getTime() - tempoInicio.getTime(); // diferença em milissegundos
        long horas = tempoDePermanencia / (1000 * 60 * 60); // converte para horas

        // Suponha que o valor por hora seja 10.0 (valor arbitrário)
        this.valor = horas * 10.0; // Valor final da fatura (exemplo)
    }

    // Método para exibir os dados da fatura de forma legível
    @Override
    public String toString() {
        return "Fatura{" +
                "veiculo=" + veiculo.getPlaca() + ", " +
                "tempoInicio=" + tempoInicio + ", " +
                "tempoFim=" + tempoFim + ", " +
                "valor=" + valor +
                '}';
    }
}
