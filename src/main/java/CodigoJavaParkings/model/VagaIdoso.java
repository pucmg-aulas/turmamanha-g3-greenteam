package model;

public class VagaIdoso extends Vaga {

    public VagaIdoso(String identificador) {
        super(identificador, 10.0); // Definindo tarifa base como 10.0
    }

    @Override
    public double calcularTarifa() {
        // Aplica 15% de desconto para Vaga Idoso
        return getTarifaBase() * 0.85;
    }
}
