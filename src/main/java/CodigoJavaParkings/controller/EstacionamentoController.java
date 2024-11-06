package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Estacionamento;
import model.Veiculo;
import model.Vaga;
import model.Fatura;
import model.Cliente;

public class EstacionamentoController {
    private Estacionamento estacionamento;
    private List<Fatura> faturas = new ArrayList<>();


    public EstacionamentoController(Estacionamento estacionamento) {
        this.estacionamento = estacionamento;
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
            return true; // Vaga ocupada com sucesso
        }
        return false; // Nenhuma vaga do tipo solicitado está livre
    }

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
}
