package dao;

import model.Vaga;
import java.util.List;

public interface VagaDAO {
    void salvar(Vaga vaga);         // Salva uma nova vaga
    List<Vaga> buscarTodas();       // Retorna todas as vagas salvas
}
