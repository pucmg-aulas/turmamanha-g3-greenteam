package model;

public class VagaPCD extends Vaga {

    public VagaPCD(String identificador) {
        super(identificador, "PCD"); // Passa o tipo "PCD"
    }

    @Override
    public double calcularTarifa(double tempoEmMinutos) {
        // Implementação da tarifa para PCD
        return tempoEmMinutos * 1.5; // Exemplo
    }
}
