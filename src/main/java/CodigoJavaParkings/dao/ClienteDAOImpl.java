package dao;

import model.Cliente;
import model.Veiculo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements ClienteDAO {
    private static final String FILE_NAME = "clientes.txt";

    @Override
    public void salvar(Cliente cliente) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            // Converte o cliente em uma linha de texto formatada
            StringBuilder linha = new StringBuilder();
            linha.append(cliente.getId()).append(",")
                    .append(cliente.getNome()).append(",");

            // Concatena as placas dos veículos separados por ponto e vírgula
            List<Veiculo> veiculos = cliente.getVeiculos();
            for (int i = 0; i < veiculos.size(); i++) {
                linha.append(veiculos.get(i).getPlaca());
                if (i < veiculos.size() - 1) {
                    linha.append(";"); // Separa as placas com ponto e vírgula
                }
            }

            // Escreve a linha no arquivo
            writer.write(linha.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Cliente> buscarTodos() {
        List<Cliente> clientes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Cliente cliente = converterLinhaParaCliente(linha);
                if (cliente != null) {
                    clientes.add(cliente);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return clientes;
    }

    // Auxiliar para converter uma linha de texto em um objeto Cliente
    private Cliente converterLinhaParaCliente(String linha) {
        String[] dados = linha.split(","); // Divide a linha em partes

        if (dados.length < 2) {
            return null; // Retorna null se a linha não tem o número esperado de campos
        }

        String id = dados[0];            // ID do cliente
        String nome = dados[1];          // Nome do cliente
        List<Veiculo> veiculos = new ArrayList<>();

        if (dados.length > 2) {
            String[] placas = dados[2].split(";"); // Divide as placas dos veículos
            for (String placa : placas) {
                veiculos.add(new Veiculo(placa)); // Cria um Veiculo para cada placa
            }
        }

        // Cria e retorna o objeto Cliente
        Cliente cliente = new Cliente(id, nome);
        cliente.setVeiculos(veiculos); // Associa a lista de veículos ao cliente
        return cliente;
    }
}
