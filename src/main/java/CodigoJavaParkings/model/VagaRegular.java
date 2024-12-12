package model;

public class VagaRegular extends Vaga {

    public VagaRegular(String identificador) {
        super(identificador, "Regular"); // Passa "Regular" como tipo para o construtor da classe pai
    }

    @Override
    public double calcularTarifa(double tempoEmMinutos) {
        double tarifaBase = 5.0; // Valor base por hora sem desconto
        return (tempoEmMinutos / 60) * tarifaBase;
    }
}
