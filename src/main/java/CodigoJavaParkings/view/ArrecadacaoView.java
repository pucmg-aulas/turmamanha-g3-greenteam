package view;

import controller.ArrecadacaoController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArrecadacaoView extends JFrame {

    private ArrecadacaoController arrecadacaoController;

    public ArrecadacaoView(ArrecadacaoController arrecadacaoController) {
        this.arrecadacaoController = arrecadacaoController;

        setTitle("Informações Financeiras - Estacionamento");
        setSize(400, 300);
        setLocationRelativeTo(null);  // Centraliza a janela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fecha a janela atual sem fechar a aplicação

        setLayout(new FlowLayout());

        // Exibir o valor total arrecadado
        JButton btnTotalArrecadado = new JButton("Valor Total Arrecadado");
        btnTotalArrecadado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double total = arrecadacaoController.calcularTotalArrecadado();
                JOptionPane.showMessageDialog(ArrecadacaoView.this, "Valor Total Arrecadado: R$ " + total);
            }
        });

        // Exibir o valor arrecadado por mês
        JButton btnArrecadadoPorMes = new JButton("Valor Arrecadado por Mês");
        btnArrecadadoPorMes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mesStr = JOptionPane.showInputDialog(ArrecadacaoView.this, "Digite o mês (1-12):");
                String anoStr = JOptionPane.showInputDialog(ArrecadacaoView.this, "Digite o ano (AAAA):");

                int mes = Integer.parseInt(mesStr);
                int ano = Integer.parseInt(anoStr);

                double totalMes = arrecadacaoController.calcularArrecadadoPorMes(mes, ano);
                JOptionPane.showMessageDialog(ArrecadacaoView.this, "Valor Arrecadado em " + mes + "/" + ano + ": R$ " + totalMes);
            }
        });

        // Exibir a média de valor por utilização
        JButton btnMediaUtilizacao = new JButton("Média por Utilização");
        btnMediaUtilizacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double media = arrecadacaoController.calcularMediaDeUtilizacao();
                JOptionPane.showMessageDialog(ArrecadacaoView.this, "Média de valor por utilização: R$ " + media);
            }
        });

        // Exibir o ranking dos clientes
        JButton btnRanking = new JButton("Ranking dos Clientes");
        btnRanking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mesStr = JOptionPane.showInputDialog(ArrecadacaoView.this, "Digite o mês (1-12):");
                String anoStr = JOptionPane.showInputDialog(ArrecadacaoView.this, "Digite o ano (AAAA):");

                int mes = Integer.parseInt(mesStr);
                int ano = Integer.parseInt(anoStr);

                String ranking = arrecadacaoController.gerarRankingClientes(mes, ano);
                JOptionPane.showMessageDialog(ArrecadacaoView.this, ranking);
            }
        });

        add(btnTotalArrecadado);
        add(btnArrecadadoPorMes);
        add(btnMediaUtilizacao);
        add(btnRanking);
    }
}
