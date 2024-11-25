package controller;

import model.Fatura;
import dao.FaturaDAO;
import dao.FaturaDAOImpl;

import java.util.List;
import java.util.Calendar;

public class ArrecadacaoController {

    private FaturaDAO faturaDAO;

    public ArrecadacaoController() {
        this.faturaDAO = new FaturaDAOImpl(); // Inicializa o DAO para acesso às faturas
    }

    // Calcular o valor total arrecadado
    public double calcularTotalArrecadado() {
        List<Fatura> faturas = faturaDAO.buscarTodas();
        double total = 0.0;
        for (Fatura fatura : faturas) {
            total += fatura.getValor();
        }
        return total;
    }

    // Calcular o valor arrecadado em um mês específico
    public double calcularArrecadadoPorMes(int mes, int ano) {
        List<Fatura> faturas = faturaDAO.buscarTodas();
        double total = 0.0;

        for (Fatura fatura : faturas) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(fatura.getTempoInicio());
            int faturaMes = cal.get(Calendar.MONTH) + 1; // Meses começam de 0, então somamos 1
            int faturaAno = cal.get(Calendar.YEAR);

            if (faturaMes == mes && faturaAno == ano) {
                total += fatura.getValor();
            }
        }

        return total;
    }

    // Calcular a média de valor por utilização do estacionamento
    public double calcularMediaDeUtilizacao() {
        List<Fatura> faturas = faturaDAO.buscarTodas();
        if (faturas.isEmpty()) {
            return 0.0;
        }
        
        double totalArrecadado = calcularTotalArrecadado();
        return totalArrecadado / faturas.size();
    }

    // Ranking dos clientes que mais geraram arrecadação em um mês específico
    public String gerarRankingClientes(int mes, int ano) {
        List<Fatura> faturas = faturaDAO.buscarTodas();
        StringBuilder ranking = new StringBuilder("Ranking dos Clientes - " + mes + "/" + ano + ":\n");

        // Dicionário para armazenar a soma do valor por cliente
        java.util.Map<String, Double> arrecadacaoPorCliente = new java.util.HashMap<>();

        for (Fatura fatura : faturas) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(fatura.getTempoInicio());
            int faturaMes = cal.get(Calendar.MONTH) + 1;
            int faturaAno = cal.get(Calendar.YEAR);

            if (faturaMes == mes && faturaAno == ano) {
                String placaCliente = fatura.getVeiculo().getPlaca();
                arrecadacaoPorCliente.put(placaCliente, arrecadacaoPorCliente.getOrDefault(placaCliente, 0.0) + fatura.getValor());
            }
        }

        // Organizar o ranking por maior arrecadação
        arrecadacaoPorCliente.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue())) // Ordena por valor
                .forEach(entry -> ranking.append("Cliente: ").append(entry.getKey()).append(" - R$ ").append(entry.getValue()).append("\n"));

        return ranking.toString();
    }
}
