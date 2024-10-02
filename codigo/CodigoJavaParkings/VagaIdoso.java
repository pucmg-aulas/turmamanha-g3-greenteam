package JavaLabExercicio;


public class VagaIdoso extends Vagas {

    public VagaIdoso(String identificador) {
        super(identificador);
    }

    @Override
    public double calcularTarifa() {
        // Implementar tarifa com 15% de desconto para Vaga Idoso
        return super.calcularTarifa() * 0.85;
    }
}

