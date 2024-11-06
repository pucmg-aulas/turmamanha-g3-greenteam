package model;

public class Veiculo {
    private String placa;

    // Construtor para inicializar o Veiculo com a placa
    public Veiculo(String placa) {
        this.placa = placa;
    }

    // Getter e Setter para a placa do veículo
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}