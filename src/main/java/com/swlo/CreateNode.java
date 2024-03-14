package com.swlo;


import java.util.ArrayList;

public class CreateNode implements Utils {


    public void createNodes(String[] getRawStates) {
        NodeD[] states = new NodeD[getRawStates.length];
        for (int i = 0; i < getRawStates.length; i++) {

            states[i] = new NodeD(getRawStates[i], i);

            for (int j = 0; j < getAlphabet().length; j++) {
                states[i].getReceived().add(new ArrayList<>());
            }
        }

        Main.getInstance().getStorageData().setStates(states);

        for (int i = 0; i < getRawFinalStates().length; i++) {
            for (int j = 0; j < getRawStates.length; j++) {
                if (getRawFinalStates()[i].equals(getRawStates[j])) {
                    states[j].setFinal(true);
                }
            }
        }

        for (int i = 0; i < getRawInitialState().length(); i++) {
            if (getRawInitialState().equals(getRawStates[i])) {
                states[i].setInitial(true);
                Main.getInstance().getStorageData().setInitialNode(states[i]);
            }

        }

        for (int i = 0; i < getRawStates().length; i++) {
            NodeD node = states[i];
            String[] matrix = getMatrix()[i];
            for (int j = 0; j < getAlphabet().length; j++) {
                NodeD state = getStateByName(matrix[j]);
                node.addTransition(getAlphabet()[j], state);

                state.getReceived().get(j).add(node);


            }
        }


    }

    private NodeD getStateByName(String name) {
        for (int i = 0; i < getRawStates().length; i++) {
            if (getRawStates()[i].equals(name)) {
                return getStates()[i];
            }
        }
        return null;
    }
}