package controller;

import dao.FaturaDAO;
import model.Fatura;
import model.Veiculo;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class FaturaController {
    private FaturaDAO faturaDAO;  // Dependência do DAO para persistir as faturas

    // Construtor que recebe o DAO de faturas
    public FaturaController(FaturaDAO faturaDAO) {
        this.faturaDAO = faturaDAO;
    }

    // Método para gerar uma nova fatura
    public Fatura gerarFatura(Veiculo veiculo, Date tempoInicio, Date tempoFim) {
        // A lógica para calcular o valor da fatura pode ser personalizada
        double valor = calcularValorFatura(tempoInicio, tempoFim);
        Fatura fatura = new Fatura(veiculo, tempoInicio, tempoFim);
        fatura.setValor(valor);

        // Salva a fatura no banco de dados (DAO)
        faturaDAO.salvar(fatura);

        return fatura;
    }

    // Método para calcular o valor da fatura
    private double calcularValorFatura(Date tempoInicio, Date tempoFim) {
        // A lógica de cálculo do valor pode ser baseada na diferença entre as datas
        long diff = tempoFim.getTime() - tempoInicio.getTime();
        long horas = diff / (1000 * 60 * 60); // Convertendo a diferença de tempo para horas

        // Vamos assumir que a tarifa é de R$ 10 por hora
        return horas * 10.0;
    }

    // Método para buscar todas as faturas
    public List<Fatura> buscarFaturas() {
        return faturaDAO.buscarTodas();  // Retorna todas as faturas salvas
    }

    // Método para buscar faturas por data e placa
    public List<Fatura> buscarFaturasPorDataEPlaca(Date dataInicio, Date dataFim, String placa) {
        // Obtém todas as faturas
        List<Fatura> todasFaturas = faturaDAO.buscarTodas();

        // Filtra as faturas por data e placa usando Stream API
        return todasFaturas.stream()
                .filter(fatura -> !fatura.getTempoInicio().before(dataInicio) && !fatura.getTempoFim().after(dataFim))
                .filter(fatura -> fatura.getVeiculo().getPlaca().equalsIgnoreCase(placa))
                .collect(Collectors.toList());
    }
}
