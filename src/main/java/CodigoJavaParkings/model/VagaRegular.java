package model;

public class VagaRegular extends Vaga {

    public VagaRegular(String identificador) {
        super(identificador, 10.0); // Define tarifa base como 10.0
    }

    @Override
    public double calcularTarifa() {
        // Retorna a tarifa padrão sem descontos para Vaga Regular
        return getTarifaBase();
    }
    @Override
    public String getTipoVaga() {
        return "Regular";
    }
}
