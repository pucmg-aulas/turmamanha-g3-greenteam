package dao;

import model.Cliente;
import java.util.List;

public interface ClienteDAO {
    void salvar(Cliente cliente);         // Salva um novo cliente
    List<Cliente> buscarTodos();          // Retorna todos os clientes salvos
}
