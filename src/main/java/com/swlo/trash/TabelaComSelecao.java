package com.swlo.trash;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class TabelaComSelecao {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Exemplo de JTable com Seleção");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);

            // Dados da tabela (vazia inicialmente)
            String[] colunas = {"Nome", "Telefone", "Email"};
            Object[][] dados = new Object[3][colunas.length]; // Inicialmente sem dados

            // Modelo de tabela customizado para permitir seleção
            DefaultTableModel modeloTabela = new DefaultTableModel(dados, colunas) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    // Todas as células são editáveis, exceto a primeira coluna (cabeçalho)
                    return column != 0;
                }
            };

            JTable tabela = new JTable(modeloTabela);

            // Configuração da tabela
            tabela.getColumnModel().getColumn(0).setPreferredWidth(150); // Largura da coluna "Nome"
            tabela.getColumnModel().getColumn(1).setPreferredWidth(100); // Largura da coluna "Telefone"
            tabela.getColumnModel().getColumn(2).setPreferredWidth(200); // Largura da coluna "Email"

            // Opções para a lista suspensa
            Vector<Integer> opcoesLista = new Vector<>();
            for (int i = 0; i <= 9; i++) {
                opcoesLista.add(i);
            }

            // Criação do editor de células com lista suspensa
            JComboBox<Integer> comboBoxEditor = new JComboBox<>(opcoesLista);
            DefaultCellEditor cellEditor = new DefaultCellEditor(comboBoxEditor);
            tabela.getColumnModel().getColumn(1).setCellEditor(cellEditor); // Coluna "Telefone"
            tabela.getColumnModel().getColumn(2).setCellEditor(cellEditor); // Coluna "Email"

            // Adiciona a tabela a um JScrollPane
            JScrollPane barraRolagem = new JScrollPane(tabela);

            // Painel principal
            JPanel painelFundo = new JPanel();
            painelFundo.setLayout(new BorderLayout());
            painelFundo.add(barraRolagem, BorderLayout.CENTER);

            frame.add(painelFundo);
            frame.setVisible(true);
        });
    }
}
