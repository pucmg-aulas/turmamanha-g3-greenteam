package dao;

import model.Fatura;
import model.Veiculo;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface FaturaDAO {

    // Buscar uma fatura pelo identificador
    Fatura buscarFatura(String identificador);

    // Listar todas as faturas
    List<Fatura> listarFaturas();

    // Buscar todas as faturas
    List<Fatura> buscarTodas();
    List<Fatura> buscarPorDataEPlaca(Date dataInicio, Date dataFim, String placa);

//-------------------------------------------------------------------------------------
    // BANCO DE DADOS
    public void inserirFatura(Fatura fatura);
    public void listarFaturas(String placa, Veiculo veiculo, double valor, String tipo);
    public double calcularTotalArrecadado();
    public double calcularTotalArrecadadoPorMes(int mes,int ano);
    public double calcularValorMedioPorUtilizacao();

    // Retorna o ranking dos clientes que mais geraram arrecadação em um determinado mês
    Map<String, Double> rankingClientesPorMes(int mes, int ano);

    double calcularArrecadadoPorMes(int mes, int ano);

}
