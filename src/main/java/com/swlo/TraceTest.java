package com.swlo;

public class TraceTest implements Utils {


    public boolean test() {

        String[] alphabet = getAlphabet();
        NodeD[] states = getStates();
        NodeD initialState = getInitialState();

        NodeD currentState = initialState;

        for (int i = 0; i < getTrace().length; i++) {
            String symbol = getTrace()[i];
            currentState = currentState.getTransitions().get(symbol);
        }
        return currentState.isFinal();

    }
}
