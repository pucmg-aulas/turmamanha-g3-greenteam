package dao;

import model.Vaga;
import java.util.List;

public interface VagaDAO {
    void salvar(Vaga vaga);         // Salva uma nova vaga
    List<Vaga> buscarTodas();

    public void listarVagas();
    public void inserirVaga(Vaga vaga);
    public void vagaRegularComMaiorFatura(Vaga vaga);
}
