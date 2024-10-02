package JavaLabExercicio;


public class VagaPCD extends Vagas {
    private boolean espacoAdicional;

    public VagaPCD(String identificador, boolean espacoAdicional) {
        super(identificador);
        this.espacoAdicional = espacoAdicional;
    }

    @Override
    public double calcularTarifa() {
        // Implementar tarifa com 13% de desconto para Vaga PCD
        return super.calcularTarifa() * 0.87;
    }

    // colocar metodo de espaço adicional
}
