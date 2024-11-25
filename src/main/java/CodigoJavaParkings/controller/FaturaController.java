package controller;

import dao.FaturaDAO;
import model.Fatura;
import model.Vaga;
import model.Veiculo;

import java.util.Date;
import java.util.List;

public class FaturaController {

    private FaturaDAO faturaDAO;
    private EstacionamentoController estacionamentoController;

    // Construtor que recebe o DAO de faturas e o controlador de estacionamento
    public FaturaController(FaturaDAO faturaDAO) {
        this.faturaDAO = faturaDAO;
        this.estacionamentoController = estacionamentoController;
    }

    // Método para gerar uma nova fatura
    public Fatura gerarFatura(String placa, Date tempoInicial, Date tempoFinal, int tipoVaga) {
        // Calcular o valor da fatura
        double valor = calcularTarifa(tipoVaga, tempoInicial, tempoFinal);

        // Criar a fatura com o valor calculado
        Fatura fatura = new Fatura(placa, tempoInicial, tempoFinal, valor, tipoVaga);
        // Salvar a fatura
        faturaDAO.salvar(fatura);

        return fatura;
    }

    // Método auxiliar para calcular a tarifa (Exemplo simples de cálculo, pode ser modificado)
    private double calcularTarifa(int tipoVaga, Date tempoInicial, Date tempoFinal) {
        long tempoEstacionado = tempoFinal.getTime() - tempoInicial.getTime(); // Diferença de tempo em milissegundos
        double horasEstacionado = tempoEstacionado / (1000 * 60 * 60); // Convertendo milissegundos para horas

        double tarifaBase = 10.0; // Tarifa base (pode ser modificada conforme o tipo de vaga)
        switch (tipoVaga) {
            case 1: // Tipo VIP
                tarifaBase = 20.0;
                break;
            case 2: // Tipo Regular
                tarifaBase = 10.0;
                break;
            case 3: // Tipo PCD
                tarifaBase = 15.0;
                break;
            case 4: // Tipo Idoso
                tarifaBase = 12.0;
                break;
        }

        return tarifaBase * horasEstacionado; // Cálculo da tarifa com base no tempo
    }

    // Método para buscar todas as faturas
    public List<Fatura> buscarFaturas() {
        return faturaDAO.buscarTodas();  // Retorna todas as faturas salvas
    }

    // Novo método para gerar fatura com base em placa ou identificador e tempo de permanência
    public Fatura gerarFatura(String placaOuIdentificador, double tempoPermanencia) {
        // Procurar pela vaga usando o identificador ou placa
        Vaga vaga = estacionamentoController.buscarVaga(placaOuIdentificador);
        if (vaga == null) {
            return null; // Vaga não encontrada
        }

        // Obter a placa do veículo e o tipo de vaga
        String placa = vaga.getVeiculo().getPlaca();
        int tipoVaga = vaga.getClass().hashCode(); // Obtém o tipo de vaga pelo hashcode

        // Calcular a data final de permanência (supondo que o tempo de permanência seja em horas)
        Date tempoFinal = new Date();
        Date tempoInicial = new Date(tempoFinal.getTime() - (long) (tempoPermanencia * 60 * 60 * 1000)); // Calculando o tempo inicial

        // Gerar e retornar a fatura
        return gerarFatura(placa, tempoInicial, tempoFinal, tipoVaga);
    }

    public Fatura gerarFatura(String placa, double tempoPermanencia, double valor) {
        // Aqui você pode usar um modelo de fatura para armazenar as informações
        Veiculo veiculo = new Veiculo(placa); // Assumindo que a classe Veiculo tenha um construtor simples
        return new Fatura(placa, tempoPermanencia, valor);
    }
}
