package model;

import java.util.Date;

public class Fatura {
    private Veiculo veiculo;
    private Date tempoInicio;
    private Date tempoFim;
    private Double valor;
    private Double valorLimite;  // Pode ser usado para definir limites ou promoções, se necessário
    private int idEstacionamento;  // Adiciona o identificador do estacionamento
    private String tipoVaga;  // Tipo de vaga (VIP, Regular, PCD, etc.)

    // Construtor completo para inicializar a Fatura com veículo, datas de início e fim, tipo de vaga e id do estacionamento
    public Fatura(Veiculo veiculo, Date tempoInicio, Date tempoFim, String tipoVaga, int idEstacionamento, double valor) {
        this.veiculo = veiculo;
        this.tempoInicio = tempoInicio;
        this.tempoFim = tempoFim;
        this.tipoVaga = tipoVaga;
        this.idEstacionamento = idEstacionamento;
        this.valor = valor;
    }

    // Construtor básico, inicializando apenas com veículo e datas
    public Fatura(Veiculo veiculo, Date tempoInicio, Date tempoFim) {
        this.veiculo = veiculo;
        this.tempoInicio = tempoInicio;
        this.tempoFim = tempoFim;
        this.valor = 0.0;  // Valor inicializando como zero
    }

    // Construtor simplificado com placa, tempo e valor, para quando não tivermos o objeto Veiculo
    public Fatura(String placa, Date tempoInicio, Date tempoFim, double valor) {
        this.veiculo = new Veiculo(placa); // Cria o objeto Veiculo a partir da placa
        this.tempoInicio = tempoInicio;
        this.tempoFim = tempoFim;
        this.valor = valor;
    }

    // Construtor com placa, tipo de vaga e tempo, onde calculamos o valor
    public Fatura(String placa, Date tempoInicio, Date tempoFim, String tipoVaga, int idEstacionamento) {
        this.veiculo = new Veiculo(placa);
        this.tempoInicio = tempoInicio;
        this.tempoFim = tempoFim;
        this.tipoVaga = tipoVaga;
        this.idEstacionamento = idEstacionamento;
        this.calcularValor(); // Calcula o valor da fatura com base no tempo e tipo de vaga
    }

    public Fatura(String placa, double tempoPermanencia, double valor) {
        this.veiculo = new Veiculo(placa);
        this.tempoInicio = new Date();
        this.tempoFim = new Date();
        this.valor = valor;
        this.calcularValor();

    }

    public Fatura(String placa, Date tempoInicial, Date tempoFinal, double valor, int tipoVaga) {
        this.veiculo = new Veiculo(placa);
        this.tempoInicio = tempoInicial;
        this.tempoFim = tempoFinal;
        this.valor = valor;
        this.calcularValor();

    }

    // Método para calcular o valor da fatura com base no tempo de permanência
    public void calcularValor() {
        // Exemplo de cálculo: valor por hora
        long tempoDePermanencia = tempoFim.getTime() - tempoInicio.getTime(); // diferença em milissegundos
        long horas = tempoDePermanencia / (1000 * 60 * 60); // converte para horas

        // A lógica pode ser ajustada dependendo do tipo de vaga
        double valorPorHora = 10.0; // Valor base por hora
        if ("VIP".equalsIgnoreCase(tipoVaga)) {
            valorPorHora = 20.0; // Valor maior para vaga VIP
        } else if ("PCD".equalsIgnoreCase(tipoVaga)) {
            valorPorHora = 5.0; // Valor reduzido para vaga PCD
        } else if ("Idoso".equalsIgnoreCase(tipoVaga)) {
            valorPorHora = 7.0; // Valor específico para vaga Idoso
        }

        this.valor = horas * valorPorHora; // Valor final da fatura (exemplo)
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

    public int getIdEstacionamento() {
        return idEstacionamento;
    }

    public void setIdEstacionamento(int idEstacionamento) {
        this.idEstacionamento = idEstacionamento;
    }

    public String getTipoVaga() {
        return tipoVaga;
    }

    public void setTipoVaga(String tipoVaga) {
        this.tipoVaga = tipoVaga;
    }

    // Método para exibir os dados da fatura de forma legível
    @Override
    public String toString() {
        return "Fatura{" +
                "veiculo=" + veiculo.getPlaca() + ", " +
                "tempoInicio=" + tempoInicio + ", " +
                "tempoFim=" + tempoFim + ", " +
                "valor=" + valor + ", " +
                "idEstacionamento=" + idEstacionamento + ", " +
                "tipoVaga=" + tipoVaga +
                '}';
    }
}
