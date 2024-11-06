package model;

public class VagaVIP extends Vaga {
    private boolean coberta;
    private boolean proximaPortao;

    public VagaVIP(String identificador) {
        super(identificador, 10.0); // Define tarifa base como 10.0
        this.coberta = true;        // Todas as vagas VIP são cobertas
        this.proximaPortao = true;  // Todas as vagas VIP são próximas ao portão
    }

    @Override
    public double calcularTarifa() {
        // Aplica aumento de 20% para Vaga VIP
        return getTarifaBase() * 1.20;
    }

    public boolean isCoberta() {
        return coberta;
    }

    public boolean isProximaPortao() {
        return proximaPortao;
    }
}
