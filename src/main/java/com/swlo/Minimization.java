package com.swlo;


import java.util.ArrayList;
import java.util.Arrays;

public class Minimization implements Utils {

    public ArrayList<ArrayList<Boolean>> matrix;

    public ArrayList<ArrayList<Boolean>> minimize(NodeD[] nodes, String[] alphabet) {
        matrix = new ArrayList<>();

        for (int i = 0; i < (nodes.length-1); i++) {
            ArrayList<Boolean> row = new ArrayList<>();
            for (int j = 1; j < nodes.length - i; j++) {
                row.add(false);
            }
            matrix.add(row);
            System.out.println(Arrays.toString(row.toArray()));
        }

        System.out.println("Matrix created: " + matrix.size() + "x" + matrix.get(0).size());

        for (ArrayList<Boolean> booleans : matrix) {
            System.out.println(Arrays.toString(booleans.toArray()));
        }


        for (int i = 0; i < nodes.length - 1; i++) {
            NodeD first = nodes[i];
            for (int j = nodes.length - 1; j > 0; j--) {
                NodeD second = nodes[j];
                for (int k = 0; k < alphabet.length; k++) {
                    if (!first.getReceived().get(k).isEmpty() && !second.getReceived().get(k).isEmpty()) {
                        crossNodes(first, second, k);
                    }
                }

            }
        }


        return matrix;
    }


    public void crossNodes(NodeD firstNode, NodeD secondNode, int index) {

        ArrayList<NodeD> first = firstNode.getReceived().get(index);
        ArrayList<NodeD> second = secondNode.getReceived().get(index);

        for (int i = 0; i < first.size(); i++) {
            for (int j = 0; j < second.size(); j++) {

                NodeD firstCell = first.get(i);
                NodeD secondCell = second.get(j);
                if (firstCell.equals(secondCell)) {
                    continue;
                }


                int firstPosition = firstCell.getPosition();
                int secondPosition = secondCell.getPosition();

                System.out.println("First: " + firstCell.getName() + " Second: " + secondCell.getName() + " " + firstPosition + " " + secondPosition);

                if (firstPosition < secondPosition) {
                    secondPosition = getStates().length - 1 - secondPosition;

                    matrix.get(firstPosition).set(secondPosition, true);

                } else {

                    firstPosition = getStates().length - 1 - firstPosition;

                    matrix.get(secondPosition).set(firstPosition, true);

                }

            }

        }

    }


    public boolean itsMinimal(ArrayList<ArrayList<Boolean>> matrix) {
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                if (!matrix.get(i).get(j)) {
                    return false;
                }
            }
        }
        return true;
    }


}
