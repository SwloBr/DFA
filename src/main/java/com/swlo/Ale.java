package com.swlo;

import javax.swing.*;

public class Ale {
    private static int[][] transicao = {{0, 1}, {1, 0}};
    private static int estadoInicial = 0;
    private static int[] aceitacao = {1};


    public static void main(String[] args) {
        String cadeia = JOptionPane.showInputDialog("Digite a cadeia que será testada");
        int estadoAtual = estadoInicial;
        int posicao = 0;

        while (posicao < cadeia.length()) {
            char elemento = cadeia.charAt(posicao);
            int elementoInt = Integer.parseInt(elemento + "");
            estadoAtual = transicao[estadoAtual][elementoInt];
            posicao++;
        }
        boolean aceita = false;
        for (int i : aceitacao) {
            if (estadoAtual == i) {
                aceita = true;
            }
        }

        JOptionPane.showMessageDialog(null, (aceita) ? "Aceita" : "Rejeita");

    }
}
