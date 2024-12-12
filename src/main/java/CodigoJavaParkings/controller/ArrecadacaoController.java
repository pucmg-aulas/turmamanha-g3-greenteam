package controller;

import dao.FaturaDAO;
import model.Fatura;
import java.util.List;

public class ArrecadacaoController {
    private final FaturaDAO faturaDAO = null;

    // Construtor agora recebe um FaturaDAO diretamente
    public ArrecadacaoController(FaturaDAO faturaDAO) {
    }

    public double calcularTotalArrecadado() {
        return faturaDAO.calcularTotalArrecadado();
    }


    public double calcularArrecadadoPorMes(int mes, int ano) {
        return faturaDAO.calcularArrecadadoPorMes(mes, ano);
    }


    // Método para buscar todas as faturas
    public List<Fatura> buscarFaturas() {
        return faturaDAO.buscarTodas(); // Delegando a busca para o FaturaDAO
    }
}
