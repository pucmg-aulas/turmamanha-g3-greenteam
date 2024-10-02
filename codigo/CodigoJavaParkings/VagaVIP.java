package JavaLabExercicio;

class VagaVIP extends Vagas {
    private boolean cobertura;

    public boolean isCobertura() {
        return cobertura;
    }

    public void setCobertura(boolean cobertura) {
        this.cobertura = cobertura;
    }

    public VagaVIP(String identificador, boolean cobertura) {
        super(identificador);
        this.cobertura = cobertura;
    }

    @Override
    public double calcularTarifa() {
        // Implementar tarifa 20% mais cara para Vaga VIP
        return super.calcularTarifa() * 1.2;
    }
}

