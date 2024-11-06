package dao;

import model.Fatura;
import java.util.List;

public interface FaturaDAO {
    void salvar(Fatura fatura);       // Salva uma nova fatura
    List<Fatura> buscarTodas();       // Retorna todas as faturas salvas
}
