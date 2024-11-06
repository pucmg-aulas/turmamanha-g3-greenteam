package dao;

import model.Veiculo;
import java.util.List;

public interface VeiculoDAO {
    void salvar(Veiculo veiculo);      // Salva um novo veículo
    List<Veiculo> buscarTodos();       // Retorna todos os veículos salvos
}
