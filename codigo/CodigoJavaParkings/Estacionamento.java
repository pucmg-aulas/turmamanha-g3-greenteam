package JavaLabExercicio;

import java.util.*;

public class Estacionamento {
    private String nome;
    private List<Vagas> vagas;
    private int numeroPreDeterminadoVagas;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Vagas> getVagas() {
        return vagas;
    }

    public void setVagas(List<Vagas> vagas) {
        this.vagas = vagas;
    }

    public int getNumeroPreDeterminadoVagas() {
        return numeroPreDeterminadoVagas;
    }

    public void setNumeroPreDeterminadoVagas(int numeroPreDeterminadoVagas) {
        this.numeroPreDeterminadoVagas = numeroPreDeterminadoVagas;
    }

    public Estacionamento(String nome, int numeroPreDeterminadoVagas) {
        this.nome = nome;
        this.numeroPreDeterminadoVagas = numeroPreDeterminadoVagas;
        this.vagas = new ArrayList<>();
    }


    public void adicionarVaga(Vagas vaga) {

    }

    public Vagas encontrarVagaLivre() {
        return null;
    }

    public Vagas ocuparVaga(Vagas vaga) {
        return null;
    }

    public void liberarVaga(Vagas vaga) {
    }


}
