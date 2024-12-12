package view;

import controller.ArrecadacaoController;
import dao.FaturaDAOImpl;
import model.Fatura;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ArrecadacaoView extends JFrame {

    private static final FaturaDAOImpl faturaDAO = new FaturaDAOImpl(); // Instância do DAO para interagir diretamente com o banco
    private JTable tabelaFaturas;
    private JLabel totalArrecadadoLabel; // Label para exibir o total arrecadado

    public ArrecadacaoView(ArrecadacaoController arrecadacaoController) {
        // Configurações da janela
        setTitle("Relatório de Arrecadação");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Inicializando a tabela
        String[] colunas = {"Identificador", "Tempo de Permanência", "Valor"};
        DefaultTableModel tableModel = new DefaultTableModel(colunas, 0);
        tabelaFaturas = new JTable(tableModel);

        // Adicionando a tabela em um JScrollPane
        JScrollPane scrollPane = new JScrollPane(tabelaFaturas);
        add(scrollPane, BorderLayout.CENTER);

        // Painel para botões
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        add(panelBotoes, BorderLayout.NORTH);

        // Botões de ação
        JButton btnValorMedio = new JButton("Valor Médio");
        btnValorMedio.addActionListener(e -> calcularValorMedio());
        panelBotoes.add(btnValorMedio);

        JButton btnArrecadacaoMensal = new JButton("Arrecadação Mensal");
        btnArrecadacaoMensal.addActionListener(e -> calcularTotalArrecadadoPorMes());
        panelBotoes.add(btnArrecadacaoMensal);

        JButton btnCalcularTotalMes = new JButton("Total Arrecadado");
        btnCalcularTotalMes.addActionListener(e -> calcularTotalArrecadado());
        panelBotoes.add(btnCalcularTotalMes);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> retornarParaMainView());
        panelBotoes.add(btnVoltar);

        // Painel para exibir o total arrecadado
        JPanel panelTotalArrecadado = new JPanel();
        totalArrecadadoLabel = new JLabel("Total Arrecadado: R$ 0,00");
        panelTotalArrecadado.add(totalArrecadadoLabel);
        add(panelTotalArrecadado, BorderLayout.SOUTH);

        // Exibir faturas ao carregar
        exibirFaturas();
    }

    private void calcularTotalArrecadadoPorMes() {
        String mesStr = JOptionPane.showInputDialog(this, "Digite o mês (1-12):");
        String anoStr = JOptionPane.showInputDialog(this, "Digite o ano:");

        if (mesStr != null && anoStr != null && !mesStr.isEmpty() && !anoStr.isEmpty()) {
            try {
                int mes = Integer.parseInt(mesStr);
                int ano = Integer.parseInt(anoStr);
                double total = faturaDAO.calcularTotalArrecadadoPorMes(mes, ano);
                JOptionPane.showMessageDialog(this, "Total arrecadado em " + mes + "/" + ano + ": R$ " + total);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Mês ou ano inválido.");
            }
        }
    }

    private void calcularValorMedio() {
        try {
            double valorMedio = faturaDAO.calcularValorMedioPorUtilizacao();
            JOptionPane.showMessageDialog(this, "Valor médio por utilização: R$ " + String.format("%.2f", valorMedio));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao calcular o valor médio: " + e.getMessage());
        }
    }

    private void exibirFaturas() {
        // Chama o método para buscar todas as faturas
        List<Fatura> faturas = faturaDAO.buscarTodas();

        if (faturas.isEmpty()) {
            // Se não houver faturas, mostrar uma mensagem
            JOptionPane.showMessageDialog(this, "Nenhuma fatura gerada.");
        } else {
            // Atualiza a tabela de faturas
            DefaultTableModel tableModel = (DefaultTableModel) tabelaFaturas.getModel();
            tableModel.setRowCount(0); // Limpa a tabela

            // Adiciona cada fatura à tabela
            for (Fatura fatura : faturas) {
                tableModel.addRow(new Object[] {
                        fatura.getIdentificador(),
                        fatura.getTempoPermanencia(),
                        String.format("%.2f", fatura.getValor())
                });
            }
        }
    }

    private void calcularTotalArrecadado() {
        // Obtém o total arrecadado de todas as faturas
        double total = faturaDAO.calcularTotalArrecadado();
        JOptionPane.showMessageDialog(this, "Total arrecadado: R$ " + String.format("%.2f", total));
        totalArrecadadoLabel.setText("Total Arrecadado: R$ " + String.format("%.2f", total)); // Atualiza o label com o valor
    }

    private void retornarParaMainView() {
        // Fecha a janela atual e abre a MainView
        dispose(); // Fecha a ArrecadacaoView
        SwingUtilities.invokeLater(() -> new MainView().setVisible(true));
    }

    public static void main(String[] args) {
        // Inicializa a view diretamente
        SwingUtilities.invokeLater(() -> {
            new ArrecadacaoView(new ArrecadacaoController(faturaDAO)).setVisible(true);
        });
    }
}
