package com.loanpro.achlibrary.model;

import java.util.List;

public class Field {


    private int[] currentValue;
    private int[] currentPosition;
    private Record record;

    public Field(int[] currentValue, int[] currentPosition, Record record) {
        this.currentValue = currentValue;
        this.currentPosition = currentPosition;
        this.record = record;
    }

    public int[] getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int[] currentValue) {
        this.currentValue = currentValue;
    }

    public int[] getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int[] currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}