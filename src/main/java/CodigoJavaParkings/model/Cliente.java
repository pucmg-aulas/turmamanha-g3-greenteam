package model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String id;
    private String nome;
    private List<Veiculo> veiculos;

    // Construtor para inicializar o Cliente
    public Cliente(String id, String nome) {
        this.id = id;
        this.nome = nome;
        this.veiculos = new ArrayList<>();
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }
}