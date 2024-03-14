package com.swlo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;

public class NodeD {

    public NodeD(String name, boolean isFinal, boolean isInitial) {
        this.name = name;
        this.isFinal = isFinal;
        this.isInitial = isInitial;
    }

    public NodeD(String name, int position) {
        this.name = name;
        this.position = position;
    }

    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private boolean isFinal;
    @Setter
    @Getter
    private boolean isInitial;
    @Getter
    private HashMap<String, NodeD> transitions = new HashMap<>();
    @Getter
    private ArrayList<ArrayList<NodeD>> received = new ArrayList<>();
    @Getter
    @Setter
    private int position;

    public void addTransition(String symbol, NodeD node) {
        transitions.put(symbol, node);
    }

}
