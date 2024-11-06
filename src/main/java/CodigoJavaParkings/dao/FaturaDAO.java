package dao;

import model.Fatura;
import java.util.Date;
import java.util.List;

public interface FaturaDAO {
    void salvar(Fatura fatura);             // Salva uma nova fatura
    List<Fatura> buscarTodas();             // Retorna todas as faturas salvas

    // Novo método para buscar faturas filtradas por intervalo de datas e placa
    List<Fatura> buscarPorDataEPlaca(Date dataInicio, Date dataFim, String placa);
}
