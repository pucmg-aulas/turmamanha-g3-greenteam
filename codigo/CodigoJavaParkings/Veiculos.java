package JavaLabExercicio;

public class Veiculos {
    private String placa;
    private Cliente cliente;

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculos(String placa, Cliente cliente) {
        this.placa = placa;
        this.cliente = cliente;
    }
}
