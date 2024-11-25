package dao;

import model.Vaga;
import model.VagaVIP;
import model.VagaIdoso;
import model.VagaPCD;
import model.VagaRegular;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VagaDAOImpl implements VagaDAO {
    private static final String FILE_NAME = "vagas.txt";

    @Override
    public void salvar(Vaga vaga) {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            try {
                file.createNewFile(); // Cria o arquivo se não existir
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            // Começamos com o identificador da vaga, tipo e status de ocupação
            String linha = vaga.getIdentificador() + "," +
                    vaga.getClass().getSimpleName() + "," +
                    vaga.isOcupada();

            // Se a vaga está ocupada, adicionamos as informações do veículo
            if (vaga.isOcupada() && vaga.getVeiculo() != null) {
                linha += "," + vaga.getVeiculo().getPlaca();
            }

            writer.write(linha);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Vaga> buscarTodas() {
        List<Vaga> vagas = new ArrayList<>();

        // Verifica se o arquivo existe; se não, cria-o
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            try {
                file.createNewFile(); // Cria o arquivo vazio
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Continua com a leitura do arquivo se ele já existe ou foi criado
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Vaga vaga = converterLinhaParaVaga(linha);
                if (vaga != null) {
                    vagas.add(vaga);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return vagas;
    }


    // Método auxiliar para converter uma linha de texto em um objeto Vaga
    private Vaga converterLinhaParaVaga(String linha) {
        String[] dados = linha.split(","); // Divide a linha em partes

        if (dados.length < 3) {
            return null; // Retorna null se a linha não tem o número esperado de campos
        }

        String identificador = dados[0];
        String tipo = dados[1];
        boolean ocupada = Boolean.parseBoolean(dados[2]);

        Vaga vaga = null;

        // Verifica o tipo da vaga e cria o objeto correspondente
        switch (tipo) {
            case "VagaVIP":
                vaga = new VagaVIP(identificador);
                break;
            case "VagaIdoso":
                vaga = new VagaIdoso(identificador);
                break;
            case "VagaPCD":
                vaga = new VagaPCD(identificador);
                break;
            case "VagaRegular":
                vaga = new VagaRegular(identificador);
                break;
            default:
                System.out.println("Tipo de vaga desconhecido: " + tipo);
                break;
        }

        if (vaga != null) {
            vaga.setOcupada(ocupada); // Define o estado de ocupação da vaga
        }
        return vaga;
    }
}
