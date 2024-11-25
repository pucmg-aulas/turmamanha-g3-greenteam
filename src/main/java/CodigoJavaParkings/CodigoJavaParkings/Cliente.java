package CodigoJavaParkings;

import java.util.*;

public class Cliente {
    private String id;
    private String nome;
    private List<Veiculo> veiculos;

    public String getId() {
        return id;
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<Veiculo> veiculos) {
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

    public void adicionarVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
    }
}
