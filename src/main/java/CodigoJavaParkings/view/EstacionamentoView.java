package view;

import controller.FaturaController;
import controller.EstacionamentoController;
import dao.FaturaDAOImpl;
import dao.VagaDAOImpl;
import model.Vaga;
import model.Veiculo;
import model.Fatura;
import dao.FaturaDAOImpl;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;  // Classe java.sql.Date
import java.util.List;

public class EstacionamentoView extends JFrame {

    private final EstacionamentoController estacionamentoController;
    private final FaturaController faturaController;
    private final JTable tabelaVagas;
    private final JTable tabelaFaturas;

    public EstacionamentoView(EstacionamentoController estacionamentoController, MainView mainView) {
        this.estacionamentoController = estacionamentoController;

        // Inicializa o FaturaController com FaturaDAO
        this.faturaController = new FaturaController(new FaturaDAOImpl());

        setTitle("Estacionamento Xulambs");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Inicializar a tabela de vagas
        String[] colunasVagas = {"Identificador", "Tipo de Vaga", "Placa", "Status"};
        DefaultTableModel modelVagas = new DefaultTableModel(colunasVagas, 0);
        tabelaVagas = new JTable(modelVagas);
        JScrollPane tabelaScrollPane = new JScrollPane(tabelaVagas);
        tabelaScrollPane.setPreferredSize(new Dimension(800, 200));
        add(tabelaScrollPane, BorderLayout.CENTER);

        // Inicializar a tabela de faturas
        String[] colunasFaturas = {"Identificador", "Tempo de Permanência", "Valor"};
        DefaultTableModel modelFaturas = new DefaultTableModel(colunasFaturas, 0);
        tabelaFaturas = new JTable(modelFaturas);
        JScrollPane tabelaFaturasScroll = new JScrollPane(tabelaFaturas);
        tabelaFaturasScroll.setPreferredSize(new Dimension(800, 200));
        add(tabelaFaturasScroll, BorderLayout.SOUTH);

        // Painel de Botões
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton btnRegistrarVeiculo = new JButton("Registrar Veículo");
        btnRegistrarVeiculo.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRegistrarVeiculo.addActionListener(e -> registrarVeiculo());
        sidePanel.add(btnRegistrarVeiculo);



        JButton btnGerarFatura = new JButton("Gerar Fatura");
        btnGerarFatura.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGerarFatura.addActionListener(e -> gerarFatura());
        sidePanel.add(btnGerarFatura);

        JButton btnListarFaturas = new JButton("ListarFaturas");
        btnListarFaturas.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnListarFaturas.addActionListener(e -> ListarFaturas());
        sidePanel.add(btnListarFaturas);

        add(sidePanel, BorderLayout.EAST);



        atualizarTabelas();
    }



    private void registrarVeiculo() {

        VagaDAOImpl vagaDAO = new VagaDAOImpl();

        String placa = JOptionPane.showInputDialog(this, "Digite a placa do veículo:");
        if (placa == null || placa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Placa inválida.");
            return;
        }

        String[] tipos = {"Regular", "VIP", "PCD", "Idoso"};
        String tipoSelecionado = (String) JOptionPane.showInputDialog(
                this, "Selecione o tipo de vaga:", "Registro de Veículo",
                JOptionPane.QUESTION_MESSAGE, null, tipos, tipos[0]
        );

        if (tipoSelecionado == null) return;

        boolean sucesso = estacionamentoController.ocuparVaga(new Veiculo(placa), tipoSelecionado);
        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Veículo registrado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Nenhuma vaga disponível para o tipo selecionado.");
        }
        atualizarTabelas();

    }

        private void gerarFatura() {
            VagaDAOImpl vagaDAO = new VagaDAOImpl();
            FaturaDAOImpl faturaDAO = new FaturaDAOImpl();

            String identificador = JOptionPane.showInputDialog(this, "Digite o identificador da vaga:");
            if (identificador == null || identificador.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Identificador inválido.");
                return;
            }

            String tempoInput = JOptionPane.showInputDialog(this, "Digite o tempo de permanência (em minutos):");
            try {
                double tempo = Double.parseDouble(tempoInput);
                Vaga vaga = estacionamentoController.buscarVaga(identificador);

                if (vaga == null || !vaga.isOcupada()) {
                    JOptionPane.showMessageDialog(this, "Vaga não encontrada ou já desocupada.");
                    return;
                }

                double valor = faturaController.calcularTarifa(vaga, tempo);

                Fatura fatura = new Fatura(
                        vaga.getVeiculo(),
                        vaga.getTempoInicial(),
                        new Date(System.currentTimeMillis()),
                        vaga.getTipo(),
                        estacionamentoController.getEstacionamentoId(),
                        valor
                );

                vagaDAO.inserirVaga(vaga);
                faturaDAO.inserirFatura(fatura);

                JOptionPane.showMessageDialog(this, "Fatura gerada com sucesso! Valor: R$ " + String.format("%.2f", valor));

                estacionamentoController.liberarVaga(identificador);  // Libera a vaga após o pagamento
                atualizarTabelas();  // Atualiza a tabela de faturas

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Tempo inválido.");
            }
        }

    private void atualizarTabelas() {
        atualizarTabelaVagas();
    }

    void atualizarTabelaVagas() {

        DefaultTableModel model = (DefaultTableModel) tabelaVagas.getModel();
        model.setRowCount(0);

        List<Vaga> vagas = estacionamentoController.getVagas();
        for (Vaga vaga : vagas) {
            String status = vaga.isOcupada() ? "Ocupada" : "Disponível";
            String placa = vaga.isOcupada() ? Veiculo.getPlaca() : "N/A";
            model.addRow(new Object[]{vaga.getIdentificador(), vaga.getClass().getSimpleName(), placa, status});
        }
    }
    private void retornarParaMainView() {
        dispose();
        SwingUtilities.invokeLater(() -> new MainView().setVisible(true));
    }
    private void ListarFaturas() {
        List<Fatura> faturas;
        try {
            FaturaDAOImpl faturaDAO = new FaturaDAOImpl();
            faturas = faturaDAO.listarFaturas();
            preencherTabelaFaturas(faturas); // Método para preencher a tabela
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao listar faturas: " + e.getMessage());
        }
    }

    private void preencherTabelaFaturas(List<Fatura> faturas) {
        DefaultTableModel model = (DefaultTableModel) tabelaFaturas.getModel();
        model.setRowCount(0);

        for (Fatura fatura : faturas) {
            model.addRow(new Object[]{fatura.getIdentificador(), fatura.getTempoPermanencia(), fatura.getValor()});
        }
    }


}
