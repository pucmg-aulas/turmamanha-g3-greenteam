package BD;

import dao.FaturaDAOImpl;
import model.Fatura;
import model.Veiculo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BancoDeDados {
    private static String Usuario = "root"; // Usuário do banco
    private static String Senha = "Thomasramos18"; // Senha do banco
    private static String url = "jdbc:mysql://127.0.0.1:3306/BancoJavaParking";

    private static BancoDeDados instancia;
    private static Connection conexao;

    public BancoDeDados() {
        conectar();  // Conectar no banco quando a instância for criada
    }

    public static BancoDeDados getInstancia() {
        if (instancia == null) {
            instancia = new BancoDeDados();
        }
        return instancia;
    }

    private void conectar() {
        try {
            if (conexao == null || conexao.isClosed()) {
                conexao = DriverManager.getConnection(url, Usuario, Senha);
                System.out.println("Conectado com sucesso!");
            }
        } catch (SQLException e) {
            Logger.getLogger(BancoDeDados.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Erro ao conectar com o banco de dados!");
        }
    }

    public static Connection getConexao() {
        if (conexao == null) {
            getInstancia();  // Garante que a instância será criada e a conexão será estabelecida
        }
        return conexao;
    }

    public void desconectar() {
        if (conexao != null) {
            try {
                conexao.close();
                System.out.println("Conexão encerrada com sucesso!");
            } catch (SQLException e) {
                Logger.getLogger(BancoDeDados.class.getName()).log(Level.SEVERE, null, e);
            }
        } else {
            System.out.println("Nenhuma conexão para encerrar.");
        }
    }


    public static void main(String[] args) {
        BancoDeDados banco = BancoDeDados.getInstancia();
        if (banco.getConexao() != null) {
            System.out.println("Conexão com o banco de dados bem-sucedida!");
        } else {
            System.out.println("Falha na conexão com o banco de dados.");
            return;
        }

        Veiculo veiculo = new Veiculo("ABC1234");

        Fatura fatura = new Fatura(1, veiculo.getId(), 100.0);  // Exemplo de fatura

        FaturaDAOImpl faturaDAO = new FaturaDAOImpl();

        faturaDAO.inserirFatura(fatura);

        banco.desconectar();
    }
}
