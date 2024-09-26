package JavaLabExercicio;

public class Estacionamento {
    private String nome;
    private Vagas[] vagasEstacionamento;
    int numeroPreDeterminadoVagas;

    public Estacionamento(String nome, Vagas[] vagasEstacionamento) {
        this.nome = nome;
        this.vagasEstacionamento = vagasEstacionamento;
    }

    public void adicionarVaga(Vagas vaga) {

    }

    public Vagas encontrarVagaLivre(){
        return null;
    }

    public Vagas ocuparVaga(Vagas vaga) {
        return null;
    }

    public void liberarVaga(Vagas vaga) {

    }

    public Vagas[] getVagasEstacionamento() {
        return vagasEstacionamento;
    }
}
