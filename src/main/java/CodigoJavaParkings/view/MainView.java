package view;

import BD.BancoDeDados;
import controller.ArrecadacaoController;
import controller.EstacionamentoController;
import dao.*;
import model.Estacionamento;
import model.Fatura;
import view.ArrecadacaoView;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainView {
    private static JFrame mainFrame;

    public static void main(String[] args) {  // Corrigido: Removido <ArrecadacaoView>
        // Instâncias de DAO
        FaturaDAO faturaDAO = new FaturaDAOImpl();
        VagaDAO vagaDAO = new VagaDAOImpl();
        BancoDeDados bancoDeDados = new BancoDeDados();

        List<Fatura> faturas = faturaDAO.listarFaturas();  // Obtém a lista de faturas

        // Instância do Estacionamento
        Estacionamento estacionamento = new Estacionamento(1, "Estacionamento Xulambs", 60);

        // Instâncias dos Controllers
        ArrecadacaoController arrecadacaoController = new ArrecadacaoController(faturaDAO);
        EstacionamentoController estacionamentoController = new EstacionamentoController(vagaDAO);

        // Configurações da janela principal
        mainFrame = new JFrame("Sistema de Estacionamento");
        mainFrame.setSize(500, 600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);  // Centraliza a janela
        mainFrame.setLayout(new BorderLayout(10, 10));

        // Definindo o ícone da janela
        mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("path_to_icon.png"));  // Ajuste o caminho conforme necessário

        // Título da janela
        JLabel titleLabel = new JLabel("Bem-vindo ao Sistema de Estacionamento", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.DARK_GRAY);
        mainFrame.add(titleLabel, BorderLayout.NORTH);

        // Painel de botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        // Botão Arrecadação
        JButton arrecadacaoButton = new JButton("Arrecadação");
        estilizarBotao(arrecadacaoButton);
        arrecadacaoButton.addActionListener(e -> {
            // Cria e exibe a tela de Arrecadação
            ArrecadacaoView arrecadacaoView = new ArrecadacaoView(arrecadacaoController);
            arrecadacaoView.setVisible(true);  // Torna a janela visível
        });

        // Botão Estacionamento
        JButton estacionamentoButton = new JButton("Estacionamento");
        estilizarBotao(estacionamentoButton);
        estacionamentoButton.addActionListener(e -> {
            EstacionamentoView estacionamentoView = new EstacionamentoView(estacionamentoController, new MainView());
            estacionamentoView.setVisible(true);
        });

        // Adicionando os botões ao painel
        buttonPanel.add(arrecadacaoButton);
        buttonPanel.add(estacionamentoButton);

        // Adicionando o painel de botões ao centro da janela
        mainFrame.add(buttonPanel, BorderLayout.CENTER);

        // Rodapé da janela
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(new Color(220, 220, 220));  // Cor de fundo do rodapé
        JLabel footerLabel = new JLabel("© 2024 Sistema de Estacionamento - Todos os direitos reservados");
        footerLabel.setFont(new Font("Arial", Font.ITALIC, 12));  // Texto em itálico
        footerLabel.setForeground(Color.DARK_GRAY);
        footerPanel.add(footerLabel);
        mainFrame.add(footerPanel, BorderLayout.SOUTH);

        // Tornando a janela principal visível
        mainFrame.setVisible(true);
    }

    // Método para estilizar os botões
    private static void estilizarBotao(JButton botao) {
        botao.setPreferredSize(new Dimension(200, 40));  // Tamanho fixo do botão
        botao.setFont(new Font("Arial", Font.PLAIN, 14));  // Fonte
        botao.setBackground(new Color(34, 193, 195));  // Cor de fundo
        botao.setForeground(Color.WHITE);  // Cor do texto
        botao.setFocusPainted(false);  // Remover foco do botão
        botao.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));  // Borda arredondada
    }

    // Método para controlar a visibilidade da janela principal
    public static void setVisible(boolean b) {
        mainFrame.setVisible(b);
    }
}
