package model;

import java.util.Date;

public abstract class Vaga {
    private String identificador;
    private boolean ocupada;
    private Veiculo veiculo;
    private Date tempoInicial;
    private String tipo; // Adicionando tipo

    public Vaga(String identificador, String tipo) {
        this.identificador = identificador;
        this.ocupada = false;
        this.veiculo = null;
        this.tempoInicial = null;
        this.tipo = tipo; // Inicializa o tipo
    }

    public String getIdentificador() {
        return this.identificador;
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

    public String getTipo() {
        return tipo; // Retorna o tipo da vaga
    }

    // Método abstrato para calcular a tarifa, que será implementado nas subclasses concretas
    public abstract double calcularTarifa(double tempoEmMinutos);

    // Método para verificar se a vaga está ocupada ou não
    public void ocupar(Veiculo veiculo) {
        this.veiculo = veiculo;
        this.ocupada = true;
        this.tempoInicial = new Date(); // Marca o tempo de ocupação
    }

    // Método para liberar a vaga
    public void liberar() {
        this.veiculo = null;
        this.ocupada = false;
        this.tempoInicial = null; // Reseta o tempo inicial
    }
}
