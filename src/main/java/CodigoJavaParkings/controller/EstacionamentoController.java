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
    private VagaDAO vagaDAO;

    public EstacionamentoController(VagaDAO vagaDAO) {
        this.vagaDAO = vagaDAO;
        this.estacionamento = new Estacionamento(1, "Estacionamento Xulambs", 60);
        this.estacionamento.gerarVagas();
    }

    public Estacionamento getEstacionamento() {
        return estacionamento;
    }

    public List<Vaga> getVagasComStatus() {
        return estacionamento.getVagas();
    }

    public Vaga encontrarVagaLivre(String tipoVaga) {
        for (Vaga vaga : estacionamento.getVagas()) {
            // Use vaga.getTipo() para acessar o tipo da instância
            if (vaga.getTipo().equalsIgnoreCase(tipoVaga) && !vaga.isOcupada()) {
                return vaga;
            }
        }
        return null; // Retorna null se nenhuma vaga livre for encontrada
    }



    public boolean ocuparVaga(Veiculo veiculo, String tipoVaga) {
        Vaga vagaLivre = encontrarVagaLivre(tipoVaga);
        if (vagaLivre != null) {
            vagaLivre.setVeiculo(veiculo);
            vagaLivre.setTempoInicial(new Date());
            vagaLivre.setOcupada(true);
            return true;
        }
        return false;
    }

    public void liberarVaga(String identificador) {
        for (Vaga vaga : estacionamento.getVagas()) {
            if (vaga.getIdentificador().equals(identificador)) {
                vaga.setOcupada(false);
                vaga.setVeiculo(null);
                vaga.setTempoInicial(null);
                vagaDAO.salvar(vaga);
                break;
            }
        }
    }

    public void adicionarVeiculoAoCliente(Cliente cliente, Veiculo veiculo) {
        cliente.getVeiculos().add(veiculo);
    }

    public Vaga buscarVaga(String placaOuIdentificador) {
        for (Vaga vaga : estacionamento.getVagas()) {
            if (vaga.getIdentificador().equals(placaOuIdentificador) ||
                    (vaga.getVeiculo() != null && vaga.getVeiculo().getPlaca().equals(placaOuIdentificador))) {
                return vaga;
            }
        }
        return null;
    }

    public List<Vaga> getVagas() {
        return estacionamento.getVagas();
    }

    public int getEstacionamentoId() {
        return estacionamento.getId();
    }
}
