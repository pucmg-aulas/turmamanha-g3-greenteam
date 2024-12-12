package model;

public class VagaVIP extends Vaga {

    public VagaVIP(String identificador) {
        super(identificador, "VIP"); // Passa o tipo "VIP"
    }

    @Override
    public double calcularTarifa(double tempoEmMinutos) {
        // Implementação da tarifa para VIP
        return tempoEmMinutos * 2; // Exemplo
    }
}
