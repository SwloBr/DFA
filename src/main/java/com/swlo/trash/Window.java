package com.swlo.trash;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public Window() {
        getContentPane().setBackground(Color.DARK_GRAY);
        setSize(1200, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTable jTable = new JTable(10, 5);
        jTable.setGridColor(Color.DARK_GRAY);

        // Use o contentPane como o contêiner para o GroupLayout
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        // Configuração das posições dos componentes
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)

                .addComponent(jTable));
        layout.setVerticalGroup(layout.createSequentialGroup()

                .addComponent(jTable));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Window window = new Window();
            window.setVisible(true);
        });
    }
}
