package model;

public class VagaIdoso extends Vaga {

    public VagaIdoso(String identificador) {
        super(identificador, "Idoso"); // Passa "Idoso" como tipo para o construtor da classe pai
    }

    @Override
    public double calcularTarifa(double tempoEmMinutos) {
        double tarifaBase = 3.0; // Valor base por hora com desconto
        return (tempoEmMinutos / 60) * tarifaBase;
    }
}
