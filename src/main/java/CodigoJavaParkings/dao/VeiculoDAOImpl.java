package dao;

import model.Veiculo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAOImpl implements VeiculoDAO {
    private static final String FILE_NAME = "veiculos.txt";

    @Override
    public void salvar(Veiculo veiculo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            // Grava a placa do veículo em uma linha
            writer.write(veiculo.getPlaca());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Veiculo> buscarTodos() {
        List<Veiculo> veiculos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Veiculo veiculo = converterLinhaParaVeiculo(linha);
                if (veiculo != null) {
                    veiculos.add(veiculo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return veiculos;
    }

    // Auxiliar para converter uma linha de texto em um objeto Veiculo
    private Veiculo converterLinhaParaVeiculo(String linha) {
        return new Veiculo(linha); // Cria um Veiculo com a placa
    }
}
