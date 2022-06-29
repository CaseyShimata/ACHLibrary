package com.loanpro.achlibrary.model;

import java.util.ArrayList;

public class ACHField {


    private ArrayList<Character> currentValue;
    private Integer currentPosition;

    public ACHField(ArrayList<Character> currentValue, Integer currentPosition) {
        this.currentValue = currentValue;
        this.currentPosition = currentPosition;
    }

    public ArrayList<Character> getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(ArrayList<Character> currentValue) {
        this.currentValue = currentValue;
    }

    public Integer getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Integer currentPosition) {
        this.currentPosition = currentPosition;
    }
}