package com.swlo;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Windows {

    public String[] states() {
        String chainStates = JOptionPane.showInputDialog("Digite os estados separados por virgula:");
        chainStates = chainStates.replace(" ", "");
        String[] states = chainStates.split(",");
        return states;
    }


    public String[] alphabet() {
        String chainAlphabet = JOptionPane.showInputDialog("Digite o alfabeto separados por virgula:");
        chainAlphabet = chainAlphabet.replace(" ", "");
        String[] alphabet = chainAlphabet.split(",");
        return alphabet;
    }

    public String initialState(String[] states) {
        String initialState = (String) JOptionPane.showInputDialog(
                null,
                "Selecione o estado inicial:",
                "Seleção de Estados",
                JOptionPane.QUESTION_MESSAGE,
                null,
                states,
                states[0]); // Opção padrão

        // Verificando se o usuário selecionou um estado
        if (initialState != null) {
            JOptionPane.showMessageDialog(null, "Você selecionou " + initialState + " como estado inicial");
        }
        return initialState;
    }

    public String[] finalStates(String[] states) {
        String quantityFinalStates = JOptionPane.showInputDialog("Digite a quantidade de estados finais:");
        int quantity = Integer.parseInt(quantityFinalStates);


        List<String> availableStates = new ArrayList<>();
        for (String state : states) {
            availableStates.add(state);
        }

        List<String> selectedStates = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            String finalState = (String) JOptionPane.showInputDialog(
                    null,
                    "Selecione o(s) estado(s) final(is):",
                    "Seleção de Estados",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    availableStates.toArray(),
                    availableStates.get(0)); // Opção padrão

            selectedStates.add(finalState);
            availableStates.remove(finalState);
        }

        return selectedStates.toArray(new String[0]);
    }

    public String[] testPath() {
        String path = JOptionPane.showInputDialog("Digite a cadeia que será testada:");
        path = path.replace(" ", "");
        String[] testPath = path.split("");
        return testPath;
    }

    public String[][] tableTransition(String[] states, String[] alphabet) {
        // Criando uma tabela de transição vazia
        DefaultTableModel model = new DefaultTableModel(states.length + 1, alphabet.length + 1) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0 && row != 0; // Permitir edição apenas para células internas
            }
        };

        JTable table = new JTable(model);

        // Configurando o cabeçalho da tabela como null para removê-lo
        table.setTableHeader(null);

        // Preenchendo a primeira linha com os símbolos do alfabeto
        for (int i = 0; i < alphabet.length; i++) {
            model.setValueAt(alphabet[i], 0, i + 1);
        }

        model.setValueAt("Estados", 0, 0);

        // Preenchendo a primeira coluna com os estados
        for (int i = 0; i < states.length; i++) {
            model.setValueAt(states[i], i + 1, 0);
        }


        // Criando um ComboBox com os estados
        JComboBox<String> ComboBoxstates = new JComboBox<>(states);

        // Definindo um editor de célula personalizado para cada célula da tabela
        for (int row = 1; row < model.getRowCount(); row++) {
            for (int col = 1; col < model.getColumnCount(); col++) {
                table.getColumnModel().getColumn(col).setCellEditor(new DefaultCellEditor(ComboBoxstates));
            }
        }

        // Renderizador de célula personalizado para colorir as células dos estados e do alfabeto
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row == 0 || column == 0) {
                    c.setBackground(Color.YELLOW); // Cor para as células dos estados e do alfabeto
                } else {
                    c.setBackground(table.getBackground()); // Cor para as células internas
                }
                return c;
            }
        };

        // Aplicando o renderizador de célula personalizado à tabela
        table.setDefaultRenderer(Object.class, renderer);


        // Exibindo a tabela de transição para o usuário
        JOptionPane.showMessageDialog(null, new JScrollPane(table), "Tabela de Transição", JOptionPane.PLAIN_MESSAGE);

        // Obtendo os dados preenchidos na tabela e transformando em uma matriz
        String[][] transitionMatrix = new String[states.length][alphabet.length];
        for (int row = 1; row < model.getRowCount(); row++) {
            for (int col = 1; col < model.getColumnCount(); col++) {
                String valorCelula = (String) model.getValueAt(row, col);
                transitionMatrix[row - 1][col - 1] = valorCelula;
            }
        }
        return transitionMatrix;
    }

    public void showResult(boolean result) {
        if (result) {
            JOptionPane.showMessageDialog(null, "Cadeia aceita!!!");
        } else {
            JOptionPane.showMessageDialog(null, "Cadeia rejeitada!!!");
        }
    }

    public void showMinimizationResult(boolean result) {
        if (result) {
            JOptionPane.showMessageDialog(null, "O autômato está minimizado!!!");
        } else {
            JOptionPane.showMessageDialog(null, "O autômato não está minimizado!!!");
        }
    }

    public void tableMinimization(ArrayList<ArrayList<Boolean>> matrix, NodeD[] nodes) {
        // Criando uma tabela de transição vazia
        DefaultTableModel model = new DefaultTableModel(nodes.length, nodes.length) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);

        // Configurando o cabeçalho da tabela como null para removê-lo
        table.setTableHeader(null);

        // Preenchendo a primeira linha com os estados
        for (int i = nodes.length - 1, j = 1; i > 0; i--, j++) {
            model.setValueAt(nodes[i].getName(), 0, j);
        }

        model.setValueAt("Estados", 0, 0);

        // Preenchendo a primeira coluna com os estados
        for (int i = 0; i < nodes.length - 1; i++) {
            model.setValueAt(nodes[i].getName(), i + 1, 0);
        }

        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                boolean m = matrix.get(i).get(j);

                if (m = true) {
                    model.setValueAt("X", i + 1, j + 1);

                } else {
                    model.setValueAt(" ", i + 1, j + 1);
                }
            }

            // Renderizador de célula personalizado para colorir as células dos estados e do alfabeto
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    if (value != null && value.toString().equals(" ")) {
                        c.setBackground(Color.YELLOW); // Cor para as células dos estados e do alfabeto
                    } else if (value == null) {
                        c.setBackground(Color.GRAY); // Cor para as células dos estados e do alfabeto
                    } else {
                        c.setBackground(table.getBackground()); // Cor para as células internas
                    }
                    return c;
                }
            };

            // Aplicando o renderizador de célula personalizado à tabela
            table.setDefaultRenderer(Object.class, renderer);

        }
        // Exibindo a tabela de transição para o usuário
        JOptionPane.showMessageDialog(null, new JScrollPane(table), "Tabela de Transição", JOptionPane.PLAIN_MESSAGE);


    }
}

