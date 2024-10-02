package JavaLabExercicio;

import java.util.Date;

public abstract class Vagas {
    private String identificador;
    private boolean ocupada;
    private Veiculos veiculo;
    private Date tempoInicial;
    private Date tempoFinal;

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

    public Veiculos getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculos veiculo) {
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

    public Vagas(String identificador) {
        this.identificador = identificador;

    }

    public void ocuparVaga(Veiculos veiculo) {
        this.veiculo = veiculo;
    }

    public boolean liberarVaga(Veiculos veiculo, boolean ocupada) {
        this.ocupada = false;
        this.veiculo = veiculo;
        if (ocupada) {
            return false;
        }
        return true;
    }

    public double calcularTarifa() {
        return 0;
    }
}
