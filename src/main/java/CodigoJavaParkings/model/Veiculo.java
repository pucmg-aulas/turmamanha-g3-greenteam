package model;

public class Veiculo {
    private String placa; // Placa do veículo
    private Vaga vaga;    // Vaga onde o veículo está estacionado

    // Construtor para inicializar o Veiculo com a placa
    public Veiculo(String placa) {
        this.placa = placa;
    }

    // Getter para a placa do veículo
    public String getPlaca() {
        return placa;
    }

    // Setter para a placa do veículo
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    // Getter para a vaga onde o veículo está estacionado
    public Vaga getVaga() {
        return vaga;
    }

    // Setter para a vaga onde o veículo está estacionado
    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }
}
