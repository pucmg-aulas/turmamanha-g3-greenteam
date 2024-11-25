package dao;

import model.Fatura;
import model.Veiculo;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FaturaDAOImpl implements FaturaDAO {
    private static final String FILE_NAME = "faturas.txt";

    @Override
    public void salvar(Fatura fatura) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            // Converte a fatura em uma linha de texto formatada
            String linha = fatura.getVeiculo().getPlaca() + "," +
                    fatura.getTempoInicio().getTime() + "," +
                    fatura.getTempoFim().getTime() + "," +
                    fatura.getValor() + "," +
                    fatura.getIdEstacionamento(); // Inclui o ID do estacionamento

            // Escreve a linha no arquivo
            writer.write(linha);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Fatura> buscarTodas() {
        List<Fatura> faturas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Fatura fatura = converterLinhaParaFatura(linha);
                if (fatura != null) {
                    faturas.add(fatura);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return faturas;
    }

    @Override
    public List<Fatura> buscarPorDataEPlaca(Date dataInicio, Date dataFim, String placa) {
        List<Fatura> faturasFiltradas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Fatura fatura = converterLinhaParaFatura(linha);
                if (fatura != null) {
                    // Verifica se a fatura está no intervalo de datas e se a placa do veículo corresponde
                    if (isDentroDoIntervalo(fatura.getTempoInicio(), fatura.getTempoFim(), dataInicio, dataFim) &&
                            fatura.getVeiculo().getPlaca().equals(placa)) {
                        faturasFiltradas.add(fatura);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return faturasFiltradas;
    }

    // Método auxiliar para verificar se a fatura está dentro do intervalo de datas
    private boolean isDentroDoIntervalo(Date tempoInicio, Date tempoFim, Date dataInicio, Date dataFim) {
        return (tempoInicio.after(dataInicio) || tempoInicio.equals(dataInicio)) &&
                (tempoFim.before(dataFim) || tempoFim.equals(dataFim));
    }

    // Auxiliar para converter uma linha de texto em um objeto Fatura
    private Fatura converterLinhaParaFatura(String linha) {
        String[] dados = linha.split(","); // Divide a linha em partes

        if (dados.length < 5) {  // A linha precisa ter pelo menos 5 dados
            return null; // Retorna null se a linha não tem o número esperado de campos
        }

        try {
            String placa = dados[0];                     // Placa do veículo
            long tempoInicioMillis = Long.parseLong(dados[1]); // Timestamp de início em milissegundos
            long tempoFimMillis = Long.parseLong(dados[2]);    // Timestamp de fim em milissegundos
            double valor = Double.parseDouble(dados[3]);       // Valor da fatura
            int idEstacionamento = Integer.parseInt(dados[4]); // ID do estacionamento

            // Converte timestamps para objetos Date
            Date tempoInicio = new Date(tempoInicioMillis);
            Date tempoFim = new Date(tempoFimMillis);

            // Cria um objeto Veiculo com a placa
            Veiculo veiculo = new Veiculo(placa);

            // Cria e retorna o objeto Fatura
            Fatura fatura = new Fatura(veiculo, tempoInicio, tempoFim);
            fatura.setValor(valor);
            fatura.setIdEstacionamento(idEstacionamento); // Define o ID do estacionamento
            return fatura;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null; // Retorna null se houver erro na conversão de dados
        }
    }
}
