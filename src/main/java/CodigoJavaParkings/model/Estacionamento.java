package model;

import java.util.ArrayList;
import java.util.List;

public class Estacionamento {
    private String nome;
    private List<Vaga> vagas;
    private int numeroPreDeterminadoVagas;
    private int id;  // Identificador único para o estacionamento

    // Construtor agora também recebe um id
    public Estacionamento(int id, String nome, int numeroPreDeterminadoVagas) {
        this.id = id; // Corrigido para usar o parâmetro recebido
        this.nome = nome;
        this.numeroPreDeterminadoVagas = numeroPreDeterminadoVagas;
        this.vagas = new ArrayList<>();
        gerarVagas();
    }


    // Getter e Setter para id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public List<Vaga> getVagas() {
        return vagas; // Retorna a lista de vagas
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    // Método para gerar vagas com as proporções especificadas
    public void gerarVagas() {
        char fila = 'A'; // Fila começa pela identificação 'A'
        int numeroVaga = 1; // Contador para o número de vagas

        for (int i = 0; i < numeroPreDeterminadoVagas; i++) {
            String identificador = fila + String.format("%02d", numeroVaga);

            Vaga vaga;
            if (i < numeroPreDeterminadoVagas * 0.10) {
                vaga = new VagaVIP(identificador); // 10% VIP
            } else if (i < numeroPreDeterminadoVagas * 0.20) {
                vaga = new VagaIdoso(identificador); // Próximos 10% Idoso
            } else if (i < numeroPreDeterminadoVagas * 0.30) {
                vaga = new VagaPCD(identificador); // Próximos 10% PCD
            } else {
                vaga = new VagaRegular(identificador); // Restante Regular
            }

            vagas.add(vaga); // Adiciona a vaga à lista de vagas

            numeroVaga++;
            if (numeroVaga > 20) { // Limita o número de vagas por fila a 20
                numeroVaga = 1;
                fila++;
            }
        }
    }

    // Método para localizar uma vaga pelo identificador
    public Vaga getVagaPorIdentificador(String identificador) {
        for (Vaga vaga : vagas) {
            if (vaga.getIdentificador().equals(identificador)) {
                return vaga;
            }
        }
        return null; // Retorna null se a vaga não for encontrada
    }
}
