package CodigoJavaParkings;


public class VagaIdoso extends Vaga {

    public VagaIdoso(String identificador) {
        super(identificador, 10.0);
    }

    @Override
    public double calcularTarifa() {
        // Implementar tarifa com 15% de desconto para Vaga Idoso
        return getTarifaBase() * 0.85;
    }
}

