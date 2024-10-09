package CodigoJavaParkings;

public class VagaVIP extends Vaga {
    private boolean coberta;
    private boolean proximaPortao;

    public VagaVIP(String identificador) {
        super(identificador, 10.0);
        this.coberta = true;  // Todas as vagas VIP são cobertas
        this.proximaPortao = true;  // Todas as vagas VIP são próximas ao portão
    }

    // Calcular a tarifa VIP (20% mais cara que a regular)
    @Override
    public double calcularTarifa() {
        return getTarifaBase() * 1.20;
    }

    public boolean isCoberta() {
        return coberta;
    }

    public boolean isProximaPortao() {
        return proximaPortao;
    }
}


