package controller;

import dao.FaturaDAO;
import dao.FaturaDAOImpl;
import model.Fatura;
import model.Vaga;
import java.util.List;
import java.util.Date;
import java.util.Map;
import controller.ArrecadacaoController;
import model.VagaRegular;
import model.VagaPCD;
import model.VagaVIP;
import model.VagaIdoso;

public class FaturaController {
    private final FaturaDAO faturaDAO;
    private EstacionamentoController estacionamentoController;
    private ArrecadacaoController arrecadacaoController;

    // Construtor com injeção de dependências para FaturaDAO e EstacionamentoController
    public FaturaController(FaturaDAO faturaDAO, EstacionamentoController estacionamentoController, ArrecadacaoController arrecadacaoController) {
        this.faturaDAO = faturaDAO;
        this.estacionamentoController = estacionamentoController;
        this.arrecadacaoController = arrecadacaoController;
    }

    public FaturaController(FaturaDAOImpl faturaDAO) {
        this.faturaDAO = faturaDAO;
    }

    // Calcula a tarifa com base no tipo de vaga e tempo de permanência (em minutos)
    public double calcularTarifa(Vaga vaga, double tempoPermanencia) {
        double tarifaBase;
        switch (vaga.getClass().getSimpleName()) {
            case "VagaVIP":
                tarifaBase = 16.0 + (16 * 0.2);
                break;
            case "VagaPCD":
                tarifaBase = 16.0 - (16 * 0.13);
                break;
            case "VagaIdoso":
                tarifaBase = 16.0 - (16 * 0.15);
                break;
            default: // VagaRegular
                tarifaBase = 16.0;
                break;
        }
        return tarifaBase * (tempoPermanencia / 60); // Calcula a tarifa por hora
    }

    // Método para gerar e inserir uma fatura no banco de dados
    public void gerarEInserirFatura(Vaga vaga, double tempoPermanencia) {
        // Calcular o valor da fatura
        double valor = calcularTarifa(vaga, tempoPermanencia);

        // Criar a fatura
        Fatura fatura = new Fatura(vaga.getIdentificador(), tempoPermanencia, valor);

    }


    // Métodos para manipulação de faturas
    public List<Fatura> listarFaturas() {
        return faturaDAO.listarFaturas();
    }

    public Fatura buscarFatura(String identificador) {
        return faturaDAO.buscarFatura(identificador);
    }


    public double calcularTotalArrecadado() {
        return faturaDAO.calcularTotalArrecadado();
    }

    public double calcularArrecadadoPorMes(int mes, int ano) {
        return faturaDAO.calcularArrecadadoPorMes(mes, ano);
    }


    public Map<String, Double> rankingClientesPorMes(int mes, int ano) {
        return faturaDAO.rankingClientesPorMes(mes, ano);
    }
}
