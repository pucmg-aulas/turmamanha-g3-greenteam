package controller;

import dao.VagaDAO;
import model.Estacionamento;
import model.Veiculo;
import model.Vaga;
import model.Fatura;
import model.Cliente;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EstacionamentoController {
    private Estacionamento estacionamento;
    private List<Fatura> faturas = new ArrayList<>();
    private VagaDAO vagaDAO; // Adicionando o DAO para persistência

    public EstacionamentoController(Estacionamento estacionamento, VagaDAO vagaDAO) {
        this.estacionamento = estacionamento;
        this.vagaDAO = vagaDAO;
        this.estacionamento.gerarVagas();  // Gera as vagas automaticamente ao instanciar o controlador
    }

    // Método para retornar as vagas do estacionamento
    public Estacionamento getEstacionamento() {
        return estacionamento;
    }

    // Método para obter uma lista de todas as vagas do estacionamento com seu status
    public List<Vaga> getVagasComStatus() {
        return estacionamento.getVagas();  // Retorna a lista de vagas com as informações de status
    }

    // Encontrar a próxima vaga livre de um tipo específico
    public Vaga encontrarVagaLivre(Class<? extends Vaga> tipoVaga) {
        for (Vaga vaga : estacionamento.getVagas()) {
            if (tipoVaga.isInstance(vaga) && !vaga.isOcupada()) {
                return vaga;
            }
        }
        return null; // Nenhuma vaga livre disponível
    }

    // Ocupar uma vaga com um veículo
    public boolean ocuparVaga(Veiculo veiculo, Class<? extends Vaga> tipoVaga) {
        Vaga vagaLivre = encontrarVagaLivre(tipoVaga);
        if (vagaLivre != null) {
            vagaLivre.setVeiculo(veiculo);
            vagaLivre.setTempoInicial(new Date());
            vagaLivre.setOcupada(true);

            // Persistir a vaga ocupada no DAO
            vagaDAO.salvar(vagaLivre);

            return true; // Vaga ocupada com sucesso
        }
        return false; // Nenhuma vaga do tipo solicitado está livre
    }

    // Liberar uma vaga e gerar fatura
    public Fatura liberarVaga(String identificadorVaga) {
        for (Vaga vaga : estacionamento.getVagas()) {
            if (vaga.getIdentificador().equals(identificadorVaga) && vaga.isOcupada()) {
                Date tempoFim = new Date();
                vaga.setTempoFinal(tempoFim);
                vaga.setOcupada(false);

                // Chama `calcularTarifa`, que invocará automaticamente a versão específica da subclasse
                double valorCobrado = vaga.calcularTarifa();

                // Cria uma fatura com o valor calculado
                Fatura fatura = new Fatura(vaga.getVeiculo(), vaga.getTempoInicial(), tempoFim);
                fatura.setValor(valorCobrado);

                // Libera a vaga
                vaga.setVeiculo(null);
                vaga.setTempoInicial(null);

                // Salva o estado atualizado da vaga (como desocupada) no DAO
                vagaDAO.salvar(vaga);

                // Armazena a fatura gerada
                faturas.add(fatura);

                return fatura;
            }
        }
        return null; // Vaga não encontrada ou já está livre
    }

    // Adicionar um veículo a um cliente
    public void adicionarVeiculoAoCliente(Cliente cliente, Veiculo veiculo) {
        cliente.getVeiculos().add(veiculo); // Adiciona o veículo à lista do cliente
    }

    public Vaga buscarVaga(String placaOuIdentificador) {
        // Aqui você deve implementar a lógica para buscar a vaga
        // por placa ou identificador
        for (Vaga vaga : estacionamento.getVagas()) {
            if (vaga.getIdentificador().equals(placaOuIdentificador) ||
                    vaga.getVeiculo().getPlaca().equals(placaOuIdentificador)) {
                return vaga;
            }
        }
        return null;
    }

}
