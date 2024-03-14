package com.swlo;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    private static Main main;
    private static final CreateNode createNode = new CreateNode();
    private static Windows windows = new Windows();
    private static final StorageData storageData = new StorageData();
    private static Minimization minimization = new Minimization();

    public static void main(String[] args) {

        String[] rawStates = windows.states();
        String[] alphabet = windows.alphabet();
        String rawInitialState = windows.initialState(rawStates);
        String[] rawFinalStates = windows.finalStates(rawStates);
        String[][] tableTransition = windows.tableTransition(rawStates, alphabet);
        String[] trace = windows.testPath();

        storageData.setRawStates(rawStates);
        storageData.setRawInitialState(rawInitialState);
        storageData.setAlphabet(alphabet);
        storageData.setRawFinalStates(rawFinalStates);
        storageData.setMatrix(tableTransition);
        storageData.setTrace(trace);

        createNode.createNodes(rawStates);

        windows.showResult(new TraceTest().test());
        ArrayList<ArrayList<Boolean>> minimize = minimization.minimize(storageData.getStates(), storageData.getAlphabet());

        boolean itsMinized = minimization.itsMinimal(minimize);
        windows.showMinimizationResult(itsMinized);
        windows.tableMinimization(minimize, storageData.getStates());

        for (ArrayList<Boolean> booleans : minimize) {
            System.out.println(Arrays.toString(booleans.toArray()));
        }

    }


    public static Main getInstance() {
        if (main == null) {
            main = new Main();
        }
        return main;
    }

    public StorageData getStorageData() {
        return storageData;
    }
}
