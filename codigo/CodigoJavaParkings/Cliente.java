package JavaLabExercicio;

import java.util.*;

public class Cliente {
    private String id;
    private String nome;
    private List<Veiculos> veiculos;

    public String getId() {
        return id;
    }

    public List<Veiculos> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<Veiculos> veiculos) {
        this.veiculos = veiculos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Cliente(String id, String nome) {
        this.id = id;
        this.nome = nome;
        this.veiculos = new ArrayList<>();
    }

    public void adicionarVeiculo(Veiculos veiculo) {
        veiculos.add(veiculo);
    }
}
