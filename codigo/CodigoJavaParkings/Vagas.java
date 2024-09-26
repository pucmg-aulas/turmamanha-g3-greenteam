package JavaLabExercicio;

import java.util.Date;

public class Vagas {
    private String identificador;
    private boolean ocupada;
    private Veiculos veiculo;
    private Date tempoInicial;
    private Date tempoFinal;


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
}
