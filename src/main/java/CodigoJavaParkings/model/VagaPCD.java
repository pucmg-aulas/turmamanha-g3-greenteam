package model;

public class VagaPCD extends Vaga {
    private boolean espacoAdicional;

    public VagaPCD(String identificador) {
        super(identificador, 10.0); // Define tarifa base como 10.0
        this.espacoAdicional = true; // Atributo específico de VagaPCD
    }

    @Override
    public double calcularTarifa() {
        // Aplica 13% de desconto para Vaga PCD
        return getTarifaBase() * 0.87;
    }

    public boolean isEspacoAdicional() {
        return espacoAdicional;
    }

    @Override
    public String getTipoVaga() {
        return "PCD";
    }
}
