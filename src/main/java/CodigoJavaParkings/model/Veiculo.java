package model;

public class Veiculo {
    private static int id;            // ID único do veículo
    private static String placa;      // Placa do veículo
    private Vaga vaga;         // Vaga onde o veículo está estacionado

    // Construtor para inicializar o Veículo com placa
    public Veiculo(String placa) {
        this.placa = placa;
    }

    // Construtor para inicializar o Veículo com ID, placa e vaga
    public Veiculo(int id, String placa, Vaga vaga) {
        this.id = id;
        this.placa = placa;
        this.vaga = vaga;
    }

    // Getter para o ID do veículo
    public static int getId() {
        return id;
    }

    // Setter para o ID do veículo
    public void setId(int id) {
        this.id = id;
    }

    // Getter para a placa do veículo
    public static String getPlaca() {
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

    // Método para associar o veículo a uma vaga
    public void associarVaga(Vaga vaga) {
        this.vaga = vaga;
        vaga.setVeiculo(this);  // A vaga agora tem um veículo associado
    }

    // Método para desassociar o veículo da vaga
    public void desassociarVaga() {
        if (this.vaga != null) {
            this.vaga.setVeiculo(null);  // Desassociando o veículo da vaga
            this.vaga = null;
        }
    }
}
