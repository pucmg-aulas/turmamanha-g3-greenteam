package model;

import java.util.ArrayList;
import java.util.List;

public class Estacionamento {
    private String nome;
    private List<Vaga> vagas;
    private int numeroPreDeterminadoVagas;

    // Construtor para inicializar o Estacionamento com nome e número de vagas
    public Estacionamento(String nome, int numeroPreDeterminadoVagas) {
        this.nome = nome;
        this.numeroPreDeterminadoVagas = numeroPreDeterminadoVagas;
        this.vagas = new ArrayList<>();
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Vaga> getVagas() {
        return vagas;
    }

    public void setVagas(List<Vaga> vagas) {
        this.vagas = vagas;
    }

    public int getNumeroPreDeterminadoVagas() {
        return numeroPreDeterminadoVagas;
    }

    public void setNumeroPreDeterminadoVagas(int numeroPreDeterminadoVagas) {
        this.numeroPreDeterminadoVagas = numeroPreDeterminadoVagas;
    }
}
