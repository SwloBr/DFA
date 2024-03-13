package com.swlo;

public class Minimization implements Utils {

    public NodeD[][] minimize(NodeD[] nodes, String[] alphabet) {
        NodeD[][] matrix = new NodeD[nodes.length - 1][nodes.length - 1];
        for (int i = 0; i < nodes.length - 1; i++) {
            for (int j = 1; j < nodes.length; j++) {
                if (i != j) {
                    NodeD node1 = nodes[i];
                    NodeD node2 = nodes[j];
                    if (node1.isFinal() != node2.isFinal()) {
                        matrix[i][j] = node1;
                    } else {
                        boolean areEquivalent = true;
                        for (String symbol : alphabet) {
                            NodeD nextNode1 = node1.getTransitions().get(symbol);
                            NodeD nextNode2 = node2.getTransitions().get(symbol);
                            if (nextNode1 != null && nextNode2 != null) {
                                int index1 = -1;
                                int index2 = -1;
                                for (int k = 0; k < nodes.length; k++) {
                                    if (nodes[k] == nextNode1) {
                                        index1 = k;
                                    }
                                    if (nodes[k] == nextNode2) {
                                        index2 = k;
                                    }
                                }
                                if (index1 != index2) {
                                    areEquivalent = false;
                                    break;
                                }
                            } else if (nextNode1 != null || nextNode2 != null) {
                                areEquivalent = false;
                                break;
                            }
                        }
                        if (areEquivalent) {
                            matrix[i][j] = node1;
                        }
                    }
                }
            }
        }

        return matrix;
    }


}
