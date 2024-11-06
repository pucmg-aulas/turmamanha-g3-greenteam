

import controller.FaturaController;
import model.Fatura;
import model.Veiculo;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.List;

public class FaturaView extends JFrame {
    private FaturaController faturaController;

    public FaturaView(FaturaController faturaController) {
        this.faturaController = faturaController;
        setTitle("Faturas - Estacionamento");
        setSize(400, 300);
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fecha a janela sem fechar o aplicativo

        // Configuração da interface gráfica
        setLayout(new FlowLayout());

        JButton gerarFaturaButton = new JButton("Gerar Fatura");
        gerarFaturaButton.addActionListener(e -> gerarFatura());

        JButton listarFaturasButton = new JButton("Listar Faturas");
        listarFaturasButton.addActionListener(e -> listarFaturas());

        add(gerarFaturaButton);
        add(listarFaturasButton);
    }

    // Método para gerar uma fatura
    private void gerarFatura() {
        String placa = JOptionPane.showInputDialog("Digite a placa do veículo:");

        // Criação do veículo
        Veiculo veiculo = new Veiculo(placa);

        // Coleta as datas de início e fim
        String dataEntradaStr = JOptionPane.showInputDialog("Digite a data de entrada (dd/MM/yyyy):");
        String dataSaidaStr = JOptionPane.showInputDialog("Digite a data de saída (dd/MM/yyyy):");

        // Parse das datas
        Date tempoInicio = parseDate(dataEntradaStr);
        Date tempoFim = parseDate(dataSaidaStr);

        // Gerar a fatura
        Fatura fatura = faturaController.gerarFatura(veiculo, tempoInicio, tempoFim);

        // Exibir a fatura gerada
        JOptionPane.showMessageDialog(this, "Fatura gerada com sucesso!\n" + fatura);
    }

    // Método para listar todas as faturas geradas
    private void listarFaturas() {
        List<Fatura> faturas = faturaController.buscarFaturas();

        if (faturas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhuma fatura encontrada.");
        } else {
            StringBuilder sb = new StringBuilder("Faturas geradas:\n");
            for (Fatura fatura : faturas) {
                sb.append(fatura.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString());
        }
    }

    // Método para fazer o parsing da data de entrada
    private Date parseDate(String dateStr) {
        try {
            return new java.text.SimpleDateFormat("dd/MM/yyyy").parse(dateStr);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Data inválida!");
            return null;
        }
    }
}
