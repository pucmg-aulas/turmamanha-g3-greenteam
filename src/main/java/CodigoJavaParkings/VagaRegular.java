package CodigoJavaParkings;

public class VagaRegular extends Vaga {

    public VagaRegular(String identificador) {
        super(identificador, 10.0); // Definindo tarifa base como 10
    }

    @Override
    public double calcularTarifa() {
        return getTarifaBase(); // Tarifa padrão sem alterações
    }
}
