package view;

import controller.ArrecadacaoController;
import controller.FaturaController;
import controller.EstacionamentoController;
import model.Vaga;
import model.Veiculo;
import model.VagaVIP;
import model.VagaRegular;
import model.VagaPCD;
import model.VagaIdoso;
import model.Fatura;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class EstacionamentoView extends JFrame {

    private final EstacionamentoController estacionamentoController;
    private final JTable tabelaVagas;

    public EstacionamentoView(FaturaController faturaController, ArrecadacaoController arrecadacaoController, EstacionamentoController estacionamentoController) {
        this.estacionamentoController = estacionamentoController;

        // Configuração da janela principal
        setTitle("Estacionamento Xulambs");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Usar DISPOSE para manter a MainView ativa
        setLayout(new BorderLayout());

        // Título ou área de mensagens
        JPanel headerPanel = new JPanel();
        JLabel titleLabel = new JLabel("Bem-vindo ao Estacionamento Xulambs", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.setLayout(new BorderLayout());
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        add(headerPanel, BorderLayout.NORTH);

        // Tabela de Vagas
        String[] colunas = {"Identificador", "Tipo de Vaga", "Placa", "Status"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);
        tabelaVagas = new JTable(model);
        JScrollPane tabelaScrollPane = new JScrollPane(tabelaVagas);
        tabelaScrollPane.setPreferredSize(new Dimension(800, 200));
        add(tabelaScrollPane, BorderLayout.CENTER);

        // Painel de Botões e Campos de Registro
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adiciona margens internas

        // Botão para verificar vagas disponíveis
        JButton btnVagasDisponiveis = new JButton("Verificar Vagas Disponíveis");
        btnVagasDisponiveis.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVagasDisponiveis.addActionListener(e -> atualizarTabelaVagas());
        sidePanel.add(btnVagasDisponiveis);

        sidePanel.add(Box.createVerticalStrut(20)); // Espaçamento entre botões e campos

        // Campos de entrada para registrar veículo
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 5, 5)); // Layout em grid para os campos
        inputPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        inputPanel.add(new JLabel("Identificador da Vaga:"));
        JTextField fieldIdentificador = new JTextField();
        inputPanel.add(fieldIdentificador);

        inputPanel.add(new JLabel("Placa do Veículo:"));
        JTextField fieldPlaca = new JTextField();
        inputPanel.add(fieldPlaca);

        inputPanel.add(new JLabel("Tipo de Vaga:"));
        JComboBox<String> comboTipoVaga = new JComboBox<>(new String[]{"VIP", "Regular", "PCD", "Idoso"});
        inputPanel.add(comboTipoVaga);

        sidePanel.add(inputPanel);

        sidePanel.add(Box.createVerticalStrut(20)); // Espaçamento entre campos e botões

        // Botão para registrar veículo
        JButton btnRegistrar = new JButton("Registrar Veículo");
        btnRegistrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRegistrar.addActionListener(e -> {
            String identificador = fieldIdentificador.getText();
            String placa = fieldPlaca.getText();
            String tipoVagaSelecionado = (String) comboTipoVaga.getSelectedItem();

            // Criar veículo
            Veiculo veiculo = new Veiculo(placa);

            // Determinar tipo de vaga
            Class<? extends Vaga> tipoVaga;
            switch (tipoVagaSelecionado) {
                case "VIP":
                    tipoVaga = VagaVIP.class;
                    break;
                case "Regular":
                    tipoVaga = VagaRegular.class;
                    break;
                case "PCD":
                    tipoVaga = VagaPCD.class;
                    break;
                case "Idoso":
                    tipoVaga = VagaIdoso.class;
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Tipo de vaga inválido!");
                    return;
            }

            // Registrar vaga
            boolean sucesso = estacionamentoController.ocuparVaga(veiculo, tipoVaga);
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Veículo registrado com sucesso na vaga " + identificador);

                // Atualiza a tabela de vagas
                atualizarTabelaVagas();
            } else {
                JOptionPane.showMessageDialog(this, "Não foi possível registrar o veículo. Vaga ocupada ou tipo indisponível.");
            }
        });
        sidePanel.add(btnRegistrar);

        // Novo botão para gerar fatura
        JButton btnGerarFatura = new JButton("Gerar Fatura");
        btnGerarFatura.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGerarFatura.addActionListener(e -> {
            // Solicita a placa ou o identificador da vaga
            String placaOuIdentificador = JOptionPane.showInputDialog(this, "Digite a placa do veículo ou o identificador da vaga:");
            if (placaOuIdentificador == null || placaOuIdentificador.isEmpty()) {
                return;
            }

            // Solicita o tempo de permanência
            String tempoPermanenciaStr = JOptionPane.showInputDialog(this, "Digite o tempo de permanência (em minutos):");
            if (tempoPermanenciaStr == null || tempoPermanenciaStr.isEmpty()) {
                return;
            }

            try {
                double tempoPermanencia = Double.parseDouble(tempoPermanenciaStr);

                // Busca a vaga pelo identificador ou placa
                Vaga vaga = estacionamentoController.buscarVaga(placaOuIdentificador);
                if (vaga != null && vaga.isOcupada()) {
                    // Calcular o valor da fatura (exemplo: R$ 1,00 por minuto)
                    double valor = tempoPermanencia * 1.0; // R$ 1 por minuto de permanência

                    // Criar a fatura com o valor calculado
                    Fatura fatura = faturaController.gerarFatura(vaga.getVeiculo().getPlaca(), tempoPermanencia, valor);

                    // Exibe a fatura gerada em uma janela JOptionPane
                    JOptionPane.showMessageDialog(this, "Fatura gerada com sucesso! Valor a pagar: R$ " + String.format("%.2f", fatura.getValor()));
                    // Exibe a fatura gerada em uma nova janela
                    new FaturaView(fatura).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Vaga não encontrada ou não está ocupada.");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Tempo de permanência inválido.");
            }
        });
        sidePanel.add(Box.createVerticalStrut(20)); // Espaçamento
        sidePanel.add(btnGerarFatura);

        // Botão "Voltar para a Main"
        JButton btnVoltar = new JButton("Voltar para a Main");
        btnVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVoltar.setForeground(Color.RED); // Opcional: destaque visual
        btnVoltar.addActionListener(e -> {
            this.dispose(); // Fecha a EstacionamentoView
            MainView.main(new String[]{}); // Chama a MainView
        });
        sidePanel.add(Box.createVerticalStrut(20)); // Espaçamento
        sidePanel.add(btnVoltar);

        // Adicionar painel de botões ao frame
        add(sidePanel, BorderLayout.EAST);
    }

    // Atualiza a tabela com as vagas do estacionamento
    void atualizarTabelaVagas() {
        DefaultTableModel model = (DefaultTableModel) tabelaVagas.getModel();
        model.setRowCount(0); // Limpa a tabela

        // Itera sobre as vagas e adiciona as informações
        List<Vaga> vagas = estacionamentoController.getEstacionamento().getVagas();
        for (Vaga vaga : vagas) {
            String status = vaga.isOcupada() ? "Ocupada" : "Disponível";
            String placa = vaga.isOcupada() ? vaga.getVeiculo().getPlaca() : "N/A";
            String tipoVaga = vaga.getClass().getSimpleName();
            model.addRow(new Object[]{vaga.getIdentificador(), tipoVaga, placa, status});
        }
    }
}
