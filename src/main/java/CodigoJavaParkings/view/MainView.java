package view;

import controller.ArrecadacaoController;
import controller.FaturaController;
import controller.EstacionamentoController;
import dao.FaturaDAO;
import dao.FaturaDAOImpl;
import dao.VagaDAO;
import dao.VagaDAOImpl;
import model.Estacionamento;
import model.Veiculo;

import javax.swing.*;
import java.awt.*;

public class MainView {

    public static void main(String[] args) {
        // Criação dos DAOs necessários
        FaturaDAO faturaDAO = new FaturaDAOImpl();
        VagaDAO vagaDAO = new VagaDAOImpl(); // Instanciando o VagaDAO para persistência de vagas

        // Criação das instâncias dos controladores
        ArrecadacaoController arrecadacaoController = new ArrecadacaoController();
        FaturaController faturaController = new FaturaController(faturaDAO);

        // Criação do estacionamento com id, nome e número de vagas predefinido
        Estacionamento estacionamento = new Estacionamento(1, "Estacionamento Xulambs", 60);

        // Criação do EstacionamentoController com estacionamento e vagaDAO
        EstacionamentoController estacionamentoController = new EstacionamentoController(estacionamento, vagaDAO);

        // Configuração da janela principal
        JFrame mainFrame = new JFrame("Sistema de Estacionamento");
        mainFrame.setSize(500, 600); // Aumentei a altura para acomodar o footer
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null); // Centraliza a janela
        mainFrame.setLayout(new BorderLayout(10, 10));

        // Definir o ícone da janela
        mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("path_to_icon.png")); // Adicione o caminho do ícone

        // Adicionando um título na parte superior da janela
        JLabel titleLabel = new JLabel("Bem-vindo ao Sistema de Estacionamento", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));  // Título com fonte maior e negrito
        titleLabel.setForeground(Color.DARK_GRAY);  // Cor do título
        mainFrame.add(titleLabel, BorderLayout.NORTH);

        // Painel central com botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));  // Layout de fluxo centralizado

        // Estilizando e adicionando os botões
        JButton arrecadacaoButton = new JButton("Arrecadação");
        estilizarBotao(arrecadacaoButton);
        arrecadacaoButton.addActionListener(e -> {
            ArrecadacaoView arrecadacaoView = new ArrecadacaoView(arrecadacaoController);
            arrecadacaoView.setVisible(true);
        });

        JButton faturaButton = new JButton("Faturas");
        estilizarBotao(faturaButton);
        faturaButton.addActionListener(e -> {
            // Passando o identificador do estacionamento para a FaturaView
            FaturaView faturaView = new FaturaView(faturaController, estacionamento.getId());
            faturaView.setVisible(true);
        });

        JButton estacionamentoManagerButton = new JButton("Gerenciar Estacionamento");
        estilizarBotao(estacionamentoManagerButton);
        estacionamentoManagerButton.addActionListener(e -> {
            EstacionamentoManagerView estacionamentoManagerView = new EstacionamentoManagerView(estacionamentoController);
            estacionamentoManagerView.setVisible(true);
        });

        JButton estacionamentoButton = new JButton("Estacionamento");
        estilizarBotao(estacionamentoButton);
        estacionamentoButton.addActionListener(e -> {
            EstacionamentoView estacionamentoView = new EstacionamentoView(faturaController, arrecadacaoController, estacionamentoController);
            estacionamentoView.setVisible(true);
        });

        // Adicionando os botões ao painel
        buttonPanel.add(arrecadacaoButton);
        buttonPanel.add(faturaButton);
        buttonPanel.add(estacionamentoManagerButton);
        buttonPanel.add(estacionamentoButton);

        // Adicionando o painel de botões ao centro da janela
        mainFrame.add(buttonPanel, BorderLayout.CENTER);

        // Adicionando o footer (rodapé)
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(new Color(220, 220, 220)); // Fundo suave para o rodapé
        JLabel footerLabel = new JLabel("© 2024 Sistema de Estacionamento - Todos os direitos reservados");
        footerLabel.setFont(new Font("Arial", Font.ITALIC, 12)); // Texto em itálico
        footerLabel.setForeground(Color.DARK_GRAY); // Cor do texto do rodapé
        footerPanel.add(footerLabel);
        mainFrame.add(footerPanel, BorderLayout.SOUTH);

        // Tornando a janela principal visível
        mainFrame.setVisible(true);
    }

    // Método para estilizar os botões, para evitar repetição de código
    private static void estilizarBotao(JButton botao) {
        botao.setPreferredSize(new Dimension(200, 40));  // Tamanho fixo
        botao.setFont(new Font("Arial", Font.PLAIN, 14));  // Fonte dos botões
        botao.setBackground(new Color(34, 193, 195));  // Cor de fundo do botão
        botao.setForeground(Color.WHITE);  // Cor do texto
        botao.setFocusPainted(false);  // Remover o foco (contorno) quando pressionado
        botao.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true)); // Borda arredondada
    }
}
