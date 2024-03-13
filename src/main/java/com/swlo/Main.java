package com.swlo;

import java.util.Arrays;

public class Main{

    private static Main main;
    private static final CreateNode createNode = new CreateNode();
    private static Windows windows = new Windows();
    private static final StorageData storageData = new StorageData();

    public static void main(String[] args) {

        String[] rawStates = windows.states();
        String rawInitialState = windows.initialState(rawStates);
        String[] alphabet = windows.alphabet();
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

        System.out.println("The automaton is: " + (new TraceTest().test() ? "Correct" : "Incorrect"));


        System.out.println("The minimized automaton is: " + (Arrays.deepToString(new Minimization().minimize(storageData.getStates(), alphabet))));




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
