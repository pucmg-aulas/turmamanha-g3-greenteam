package CodigoJavaParkings;

public class VagaPCD extends Vaga {
    private boolean espacoAdicional;

    public VagaPCD(String identificador) {
        super(identificador, 10.0); // Definindo tarifa base
        this.espacoAdicional = true;
    }

    @Override
    public double calcularTarifa() {
        return getTarifaBase() * 0.87; // Aplicando 13% de desconto
    }

    public boolean isEspacoAdicional() {
        return espacoAdicional;
    }
}
