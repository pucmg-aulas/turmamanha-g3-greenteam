package CodigoJavaParkings;


public class VagaIdoso extends Vaga {

    public VagaIdoso(String identificador) {
        super(identificador, 10.0); // Definindo tarifa base como 10 e multiplicando pelo valor adequado pelo tipo dela
    }

    @Override
    public double calcularTarifa() {
        // Implementar tarifa com 15% de desconto para Vaga Idoso
        return getTarifaBase() * 0.85;
    }
}

