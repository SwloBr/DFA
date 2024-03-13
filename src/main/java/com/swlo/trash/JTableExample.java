package com.swlo.trash;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JTableExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("JTable Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Create a 3x3 JTable
            String[] columnNames = {"Coluna 1", "Coluna 2", "Coluna 3"};
            Object[][] data = new Object[3][3];
            DefaultTableModel model = new DefaultTableModel(data, columnNames);
            JTable table = new JTable(model);

            // Set custom cell renderer to display options in a JOptionPane
            table.setDefaultRenderer(Object.class, new TableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value,
                                                               boolean isSelected, boolean hasFocus,
                                                               int row, int column) {
                    if (hasFocus) {
                        // Show a JOptionPane with numeric options
                        String[] options = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
                        String selectedValue = (String) JOptionPane.showInputDialog(
                                table, "Selecione um número:", "Escolha",
                                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                        if (selectedValue != null) {
                            value = selectedValue;
                        }
                    }
                    return new DefaultTableCellRenderer().getTableCellRendererComponent(
                            table, value, isSelected, hasFocus, row, column);
                }
            });

            // Add the JTable to a JScrollPane
            JScrollPane scrollPane = new JScrollPane(table);
            frame.add(scrollPane, BorderLayout.CENTER);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
