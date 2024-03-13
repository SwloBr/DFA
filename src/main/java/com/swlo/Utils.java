package com.swlo;

public interface Utils {


    default String[] getAlphabet() {
        return Main.getInstance().getStorageData().getAlphabet();
    }

    default NodeD[] getStates() {
        return Main.getInstance().getStorageData().getStates();
    }

    default NodeD getInitialState() {
        return Main.getInstance().getStorageData().getInitialNode();
    }

    default String[] getTrace() {
        return Main.getInstance().getStorageData().getTrace();
    }

    default String[] getRawStates() {
        return Main.getInstance().getStorageData().getRawStates();
    }

    default String[] getRawFinalStates() {
        return Main.getInstance().getStorageData().getRawFinalStates();
    }


    default String getRawInitialState() {
        return Main.getInstance().getStorageData().getRawInitialState();
    }

    default String[][] getMatrix() {
        return Main.getInstance().getStorageData().getMatrix();
    }


}
