package view;

import controller.EstacionamentoController;
import model.Vaga;
import model.Veiculo;
import model.VagaVIP;
import model.VagaRegular;
import model.VagaPCD;
import model.VagaIdoso;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EstacionamentoManagerView extends JFrame {
    private EstacionamentoController estacionamentoController;
    private EstacionamentoView estacionamentoView; // Referência à EstacionamentoView

    public EstacionamentoManagerView(EstacionamentoController estacionamentoController) {
        this.estacionamentoController = estacionamentoController;
        this.estacionamentoView = estacionamentoView; // Inicializa a referência

        setTitle("Gerenciar Estacionamento");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        // Definindo o layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Margens entre os componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        // Campo para o identificador da vaga
        JLabel labelIdentificador = new JLabel("Identificador da Vaga:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(labelIdentificador, gbc);

        JTextField fieldIdentificador = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(fieldIdentificador, gbc);

        // Campo para a placa do veículo
        JLabel labelPlaca = new JLabel("Placa do Veículo:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(labelPlaca, gbc);

        JTextField fieldPlaca = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(fieldPlaca, gbc);

        // Seleção do tipo de vaga
        JLabel labelTipoVaga = new JLabel("Tipo de Vaga:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(labelTipoVaga, gbc);

        String[] tiposVaga = {"VIP", "Regular", "PCD", "Idoso"};
        JComboBox<String> comboTipoVaga = new JComboBox<>(tiposVaga);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(comboTipoVaga, gbc);

        // Botão para registrar o veículo na vaga
        JButton btnRegistrar = new JButton("Registrar Veículo");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnRegistrar, gbc);

        // Ação do botão registrar
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String identificador = fieldIdentificador.getText();
                String placa = fieldPlaca.getText();
                String tipoVagaSelecionado = (String) comboTipoVaga.getSelectedItem();

                // Criar o veículo
                Veiculo veiculo = new Veiculo(placa);

                // Determinar o tipo da vaga
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
                        JOptionPane.showMessageDialog(EstacionamentoManagerView.this, "Tipo de vaga inválido!");
                        return;
                }

                // Registrar o veículo na vaga
                boolean sucesso = estacionamentoController.ocuparVaga(veiculo, tipoVaga);
                if (sucesso) {
                    JOptionPane.showMessageDialog(EstacionamentoManagerView.this, "Veículo registrado com sucesso na vaga " + identificador);
                    // Atualizar a tabela de vagas na EstacionamentoView
                    estacionamentoView.atualizarTabelaVagas();
                } else {
                    JOptionPane.showMessageDialog(EstacionamentoManagerView.this, "Não foi possível registrar o veículo. Vaga ocupada ou tipo de vaga indisponível.");
                }
            }
        });
    }
}
