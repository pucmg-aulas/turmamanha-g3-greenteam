package model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Fatura {
    private int IdFatura;
    private Veiculo veiculo;
    private Date tempoInicio;
    private Date tempoFim;
    private Double valor;
    private String tipoVaga;
    private int idEstacionamento;

    // Construtor completo
    public Fatura(Veiculo veiculo, Date tempoInicio, Date tempoFim, String tipoVaga, int idEstacionamento, double valor) {
        this.veiculo = veiculo;
        this.tempoInicio = tempoInicio;
        this.tempoFim = tempoFim;
        this.tipoVaga = tipoVaga;
        this.idEstacionamento = idEstacionamento;
        this.valor = valor;
    }

    public Fatura(String identificador, double tempoPermanenciaStr, double tarifa) {

    }

    public Fatura(int identificador, double tempoPermanencia, double valor) {

    }

    // Método para calcular o valor com base no tipo de vaga e horas
    public double calcularValor() {
        long horas = Math.max(getTempoPermanencia(), 1); // No mínimo, 1 hora
        double valorPorHora = calcularValorPorHora();
        this.valor = horas * valorPorHora;
        return valorPorHora;
    }

    private double calcularValorPorHora() {
        switch (tipoVaga.toUpperCase()) {
            case "VIP":
                return 20.0;
            case "PCD":
                return 5.0;
            case "IDOSO":
                return 7.0;
            default:
                return 10.0;
        }
    }

    // Método para obter o tempo de permanência em horas
    public long getTempoPermanencia() {
        long tempoDePermanencia = tempoFim.getTime() - tempoInicio.getTime(); // diferença em milissegundos
        return tempoDePermanencia / (1000 * 60 * 60); // converte para horas
    }

    // --------------------------------------------
    // Métodos Estáticos para Cálculo em Lote
    // --------------------------------------------

    // Calcula o valor total arrecadado a partir de uma lista de faturas
    public static double calcularTotalArrecadado(List<Fatura> faturas) {
        return faturas.stream().mapToDouble(Fatura::getValor).sum();
    }

    // Calcula o valor arrecadado em um determinado mês a partir de uma lista de faturas
    public static double calcularArrecadadoPorMes(List<Fatura> faturas, int mes, int ano) {
        return faturas.stream()
                .filter(f -> isMesAnoCorrespondente(f.getTempoFim(), mes, ano))
                .mapToDouble(Fatura::getValor)
                .sum();
    }

    // Calcula o valor médio por utilização do estacionamento
    public static double calcularValorMedioUtilizacao(List<Fatura> faturas) {
        return faturas.isEmpty() ? 0.0 : faturas.stream().mapToDouble(Fatura::getValor).average().orElse(0.0);
    }

    // Retorna o ranking dos clientes que mais geraram arrecadação em um determinado mês
    public static Map<String, Double> rankingClientesPorMes(List<Fatura> faturas, int mes, int ano) {
        return faturas.stream()
                .filter(f -> isMesAnoCorrespondente(f.getTempoFim(), mes, ano))
                .collect(Collectors.groupingBy(f -> f.getVeiculo().getPlaca(), Collectors.summingDouble(Fatura::getValor)))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(5) // Top 5 clientes
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    // Método auxiliar para verificar se a data está no mês e ano corretos
    private static boolean isMesAnoCorrespondente(Date date, int mes, int ano) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getMonthValue() == mes && localDate.getYear() == ano;
    }

    // --------------------------------------------
    // Getters e Setters
    // --------------------------------------------

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

    public String getTipoVaga() {
        return tipoVaga;
    }

    public void setTipoVaga(String tipoVaga) {
        this.tipoVaga = tipoVaga;
    }

    public int getIdEstacionamento() {
        return idEstacionamento;
    }

    public void setIdEstacionamento(int idEstacionamento) {
        this.idEstacionamento = idEstacionamento;
    }

    @Override
    public String toString() {
        return "Fatura{" +
                "veiculo=" + veiculo.getPlaca() +
                ", tempoInicio=" + tempoInicio +
                ", tempoFim=" + tempoFim +
                ", valor=" + valor +
                ", idEstacionamento=" + idEstacionamento +
                ", tipoVaga=" + tipoVaga +
                '}';
    }

    public Object getPlacaVeiculo() {
        return null;
    }

    public LocalDate getDataPagamento() {
        return null;
    }

    public String getIdentificador() {
        return null;
    }

    public int getIdFatura() {
        return this.IdFatura;
    }

    public String getTempoInicial() {
        return null;
    }

    public String getTempoFinal() {
        return null;
    }

    public Thread getEstacionamento() {
        return null;
    }

    public void setTempoPermanencia(double tempoPermanencia) {

    }
}
