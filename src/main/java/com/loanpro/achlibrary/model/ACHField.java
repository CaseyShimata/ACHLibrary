package com.loanpro.achlibrary.model;

public class ACHField {


    private int[] currentValue;
    private int[] currentPosition;
    private ACHRecord achRecord;

    public ACHField(int[] currentValue, int[] currentPosition, ACHRecord achRecord) {
        this.currentValue = currentValue;
        this.currentPosition = currentPosition;
        this.achRecord = achRecord;
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

    public ACHRecord getRecord() {
        return achRecord;
    }

    public void setRecord(ACHRecord achRecord) {
        this.achRecord = achRecord;
    }
}