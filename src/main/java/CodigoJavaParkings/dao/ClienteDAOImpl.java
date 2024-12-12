package dao;

import BD.BancoDeDados;
import model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAOImpl implements ClienteDAO {
    @Override
    public void insert(Cliente cliente) {
        String sql = "insert into cliente values(?,?,?,?)";

        try (Connection conn = BancoDeDados.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Nome: " + rs.getString("nome"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ListarClientes() {
        String sql = "select * from cliente";

        try(Connection c = BancoDeDados.getConexao();
            PreparedStatement stmt = c.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){

            while(rs.next()){
                System.out.println("ID: " + rs.getInt("id") + ", Nome: " + rs.getString("nome"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}