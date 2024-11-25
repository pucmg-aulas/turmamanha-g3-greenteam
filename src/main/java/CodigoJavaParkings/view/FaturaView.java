package view;

import controller.FaturaController;
import model.Fatura;
import javax.swing.*;
import java.awt.*;

public class FaturaView extends JFrame {

    private Fatura fatura;

    public FaturaView(Fatura fatura) {
        this.fatura = fatura;

        // Configuração da janela
        setTitle("Fatura - Estacionamento Xulambs");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel de exibição
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2)); // Ajustado para 6 linhas, incluindo um campo adicional para 'ID do Estacionamento'

        // Exibe os dados da fatura
        panel.add(new JLabel("Placa do Veículo:"));
        panel.add(new JLabel(fatura.getVeiculo().getPlaca() != null ? fatura.getVeiculo().getPlaca() : "N/A"));

        panel.add(new JLabel("Tipo de Vaga:"));
        panel.add(new JLabel(fatura.getTipoVaga() != null ? fatura.getTipoVaga().toString() : "N/A"));

        panel.add(new JLabel("Data de Entrada:"));
        panel.add(new JLabel(fatura.getTempoInicio() != null ? fatura.getTempoInicio().toString() : "N/A"));

        panel.add(new JLabel("Data de Saída:"));
        panel.add(new JLabel(fatura.getTempoFim() != null ? fatura.getTempoFim().toString() : "N/A"));

        panel.add(new JLabel("Valor Total:"));
        panel.add(new JLabel(String.format("R$ %.2f", fatura.getValor() != null ? fatura.getValor() : 0.0)));

        // Adicionando ID do Estacionamento
        panel.add(new JLabel("ID do Estacionamento:"));
        panel.add(new JLabel(String.valueOf(fatura.getIdEstacionamento())));

        add(panel, BorderLayout.CENTER);

        // Botão para fechar a fatura
        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());
        add(btnFechar, BorderLayout.SOUTH);
    }

    public FaturaView(FaturaController faturaController, int id) {

    }
}
