package model;

import java.util.Date;

public abstract class Vaga {
    private String identificador;
    private boolean ocupada;
    private Veiculo veiculo;
    private double tarifaBase;
    private Date tempoInicial;
    private Date tempoFinal;

    // Construtor para inicializar a Vaga com identificador e tarifa base
    public Vaga(String identificador, double tarifa) {
        this.identificador = identificador;
        this.ocupada = false;
        this.tarifaBase = tarifa;
    }

    // Getters e Setters
    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Date getTempoInicial() {
        return tempoInicial;
    }

    public void setTempoInicial(Date tempoInicial) {
        this.tempoInicial = tempoInicial;
    }

    public Date getTempoFinal() {
        return tempoFinal;
    }

    public void setTempoFinal(Date tempoFinal) {
        this.tempoFinal = tempoFinal;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    public void setTarifaBase(double tarifaBase) {
        this.tarifaBase = tarifaBase;
    }

    // Cálculo de tarifa, mas sem implementação ABSTRACT
    public abstract double calcularTarifa();
}
