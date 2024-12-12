package dao;

import model.Fatura;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FaturaService {

    private final FaturaDAO faturaDAO;

    public FaturaService(FaturaDAO faturaDAO) {
        this.faturaDAO = faturaDAO;
    }

    // Calcula o valor total arrecadado
    public double calcularTotalArrecadado() {
        List<Fatura> faturas = faturaDAO.listarFaturas();
        return faturas.stream().mapToDouble(Fatura::getValor).sum();
    }

    // Calcula o valor arrecadado em um determinado mês
    public double calcularArrecadadoPorMes(int mes, int ano) {
        List<Fatura> faturas = faturaDAO.listarFaturas();
        return faturas.stream()
                .filter(f -> isMesAnoCorrespondente(f.getTempoFim(), mes, ano))
                .mapToDouble(Fatura::getValor)
                .sum();
    }

    // Calcula o valor médio por utilização do estacionamento
    public double calcularValorMedioUtilizacao() {
        List<Fatura> faturas = faturaDAO.listarFaturas();
        return faturas.isEmpty() ? 0.0 : faturas.stream().mapToDouble(Fatura::getValor).average().orElse(0.0);
    }


    // Método auxiliar para verificar se uma data pertence a um mês e ano específicos
    private boolean isMesAnoCorrespondente(Date date, int mes, int ano) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getMonthValue() == mes && localDate.getYear() == ano;
    }
}
