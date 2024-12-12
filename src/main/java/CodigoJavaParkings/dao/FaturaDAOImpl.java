package dao;

import BD.BancoDeDados;
import model.Fatura;
import model.Vaga;
import model.Veiculo;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.stream.Collectors;

public class FaturaDAOImpl implements FaturaDAO {

    private final List<Fatura> faturas = new ArrayList<>(); // Simulação de banco de dados em memória

    @Override
    public List<Fatura> listarFaturas() {
        return new ArrayList<>(faturas); // Retorna uma cópia da lista
    }

    @Override
    public List<Fatura> buscarTodas() {
        return new ArrayList<>(faturas); // Retorna todas as faturas
    }

    @Override
    public List<Fatura> buscarPorDataEPlaca(Date dataInicio, Date dataFim, String placa) {
        return faturas.stream()
                .filter(f -> !f.getTempoFim().before(dataInicio) && !f.getTempoFim().after(dataFim))
                .filter(f -> f.getVeiculo().getPlaca().equals(placa))
                .collect(Collectors.toList());
    }

    @Override
    public void inserirFatura(Fatura fatura) {
        String comandoSql = "INSERT INTO fatura (id, placa_carro, valor,estacionamento_id) VALUES (?, ?, ?,?)";

        Connection conn = BancoDeDados.getConexao();
        if (conn == null) {
            System.err.println("Erro: Conexão com o banco de dados não foi estabelecida.");
            return;
        }

        try (PreparedStatement stmt = conn.prepareStatement(comandoSql)) {
            stmt.setInt(1, fatura.getIdFatura());
            stmt.setString(2, fatura.getVeiculo().getPlaca());
            stmt.setDouble(3, fatura.getValor());
            stmt.setInt(4, fatura.getIdEstacionamento());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Fatura inserida com sucesso!");
            } else {
                System.out.println("Falha ao inserir a fatura.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao inserir fatura: " + e.getMessage());
        }
    }

    @Override
    public void listarFaturas(String placa, Veiculo veiculo, double valor, String tipo) {

    }





    @Override
    public Fatura buscarFatura(String identificador) {
        return faturas.stream()
                .filter(f -> f.getIdentificador().equals(identificador))
                .findFirst()
                .orElse(null); // Retorna null caso não encontre
    }

    public double calcularTotalArrecadado() {
        String comandoSql = "SELECT SUM(valor) AS total_arrecadado FROM fatura";

        try (Connection conn = BancoDeDados.getConexao();
             PreparedStatement stmt = conn.prepareStatement(comandoSql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getDouble("total_arrecadado");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao calcular o total arrecadado: " + e.getMessage());
        }
        return 0.0;
    }


    public double calcularTotalArrecadadoPorMes(int mes, int ano) {
        String comandoSql = "SELECT SUM(valor) AS total_arrecadado FROM fatura";

        try (Connection conn = BancoDeDados.getConexao();
             PreparedStatement stmt = conn.prepareStatement(comandoSql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getDouble("total_arrecadado");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao calcular o total arrecadado: " + e.getMessage());
        }
        return 0;
    }




    public double calcularValorMedioPorUtilizacao() {
        String comandoSql = "SELECT AVG(valor) AS valor_medio FROM fatura";

        try (Connection conn = BancoDeDados.getConexao();
             PreparedStatement stmt = conn.prepareStatement(comandoSql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getDouble("valor_medio");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao calcular o valor médio por utilização: " + e.getMessage());
        }
        return 0.0;
    }



        @Override
    public Map<String, Double> rankingClientesPorMes(int mes, int ano) {
        return Map.of();
    }

    @Override
    public double calcularArrecadadoPorMes(int mes, int ano) {
        return 0;
    }



}
