package com.swlo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StorageData {


    private String[] rawStates;
    private String[] alphabet;
    private String rawInitialState;
    private String[] rawFinalStates;
    private NodeD[] states;
    private NodeD initialNode;
    private String[] trace;
    private String[][] matrix;


}
