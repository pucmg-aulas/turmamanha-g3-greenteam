package CodigoJavaParkings;

import java.io.*;
import java.util.*;

public class Estacionamento {
    private String nome;
    private List<Vaga> vagas;
    private int numeroPreDeterminadoVagas;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Vaga> getVagas() {
        return vagas;
    }

    public void setVagas(List<Vaga> vagas) {
        this.vagas = vagas;
    }

    public int getNumeroPreDeterminadoVagas() {
        return numeroPreDeterminadoVagas;
    }

    public void setNumeroPreDeterminadoVagas(int numeroPreDeterminadoVagas) {
        this.numeroPreDeterminadoVagas = numeroPreDeterminadoVagas;
    }

    public Estacionamento(String nome, int numeroPreDeterminadoVagas) {
        this.nome = nome;
        this.numeroPreDeterminadoVagas = numeroPreDeterminadoVagas;
        this.vagas = new ArrayList<>();
        gerarVagas();
    }

    public void gerarVagas() {
        char fila = 'A'; // Fila começa pela identificação 'A'
        int numeroVaga = 1; // Contador para o número de vagas

        for (int i = 0; i < numeroPreDeterminadoVagas; i++) {
            String identificador = fila + String.format("%02d", numeroVaga); //Ajusta o identificador de cada vaga para um formato alfanumérico
            Vaga vaga = new VagaRegular(identificador);
            vagas.add(vaga);

            numeroVaga++;
            if (numeroVaga > 20) { // Limita o número de vagas por fila a 20
                numeroVaga = 1;
                fila++;
            }
        }

    }

    public Vaga encontrarVagaLivre() {
        for (Vaga vaga : vagas) {
            if (!vaga.isOcupada()) {
                return vaga; // Retorna a primeira vaga livre encontrada
            }
        }
        return null; // Nenhuma vaga livre disponível
    }

    public void ocuparVaga(Vaga vaga, Veiculo veiculo) {
        if (!vaga.isOcupada()) {
            vaga.ocuparVaga(veiculo);
        } else {
            System.out.println("Vaga já ocupada.");
        }
    }

    public void liberarVaga(Vaga vaga) {
        if (vaga.isOcupada()) {
            vaga.liberarVaga();
        } else {
            System.out.println("Vaga já está livre.");
        }
    }

    // Escrever as vagas em arquivo texto
    public void escreverVagasParaArquivo(String caminhoArquivo) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo));

        for (Vaga vaga : vagas) {
            StringBuilder linha = new StringBuilder();
            linha.append(vaga.getIdentificador()).append(",");
            linha.append(vaga.getClass().getSimpleName()).append(",");
            linha.append(vaga.isOcupada()).append(",");
            linha.append(vaga.getVeiculo() != null ? vaga.getVeiculo().getPlaca() : "").append(",");
            linha.append(vaga.getTarifaBase());

            // Verifica atributos específicos de vagas
            if (vaga instanceof VagaVIP) {
                VagaVIP vip = (VagaVIP) vaga;
                linha.append(",").append(vip.isCoberta()).append(",").append(vip.isProximaPortao());
            } else if (vaga instanceof VagaPCD) {
                VagaPCD pcd = (VagaPCD) vaga;
                linha.append(",").append(pcd.isEspacoAdicional());
            }

            writer.write(linha.toString());
            writer.newLine();
        }

        writer.close();
    }


    // Ler as vagas de um arquivo texto
    public void lerVagasDeArquivo(String caminhoArquivo) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo));
        String linha;

        while ((linha = reader.readLine()) != null) {
            String[] partes = linha.split(",");
            String identificador = partes[0];
            String tipoVaga = partes[1];
            boolean ocupada = Boolean.parseBoolean(partes[2]);
            String placa = partes[3];  // Lê a placa do veículo
            double tarifaBase = Double.parseDouble(partes[4]);

            Vaga vaga = null;

            // Reconstruir o objeto Vaga com base no tipo
            switch (tipoVaga) {
                case "VagaRegular":
                    vaga = new VagaRegular(identificador);
                    break;
                case "VagaVIP":
                    vaga = new VagaVIP(identificador);
                    break;
                case "VagaIdoso":
                    vaga = new VagaIdoso(identificador);
                    break;
                case "VagaPCD":
                    vaga = new VagaPCD(identificador);
                    break;
            }

            if (vaga != null) {
                vaga.setOcupada(ocupada);

                // Se a vaga está ocupada e há uma placa associada, cria o veículo
                if (ocupada && placa != null && !placa.isEmpty()) {
                    Veiculo veiculo = new Veiculo(placa);  // Constrói o veículo pela placa
                    vaga.ocuparVaga(veiculo);
                }

                vagas.add(vaga);
            }
        }
        reader.close();
    }

}



