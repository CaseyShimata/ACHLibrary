package com.loanpro.achlibrary.model;

import java.util.ArrayList;

public class ACHField {
    private int achPageNumber;
    private int achLineNumber;
    private int achRecordTypeNumber;
    private int achFieldNumber;
    private ArrayList<Character> currentValue;
    private Integer currentPosition;
    private ACHFieldRule achFieldRule;
    private ArrayList<ACHValidationTest> achValidationTests;

    //todo: step through and determine where to get the page, line, record, and field number then use this constructor
    public ACHField(int achPageNumber, int achLineNumber, int achRecordTypeNumber, int achFieldNumber, ArrayList<Character> currentValue, Integer currentPosition) {
        this.achPageNumber = achPageNumber;
        this.achLineNumber = achLineNumber;
        this.achRecordTypeNumber = achRecordTypeNumber;
        this.achFieldNumber = achFieldNumber;
        this.currentValue = currentValue;
        this.currentPosition = currentPosition;
    }

    public ACHField(ArrayList<Character> currentValue, Integer currentPosition) {
        this.currentValue = currentValue;
        this.currentPosition = currentPosition;
    }

    public int getAchPageNumber() {
        return achPageNumber;
    }

    public void setAchPageNumber(int achPageNumber) {
        this.achPageNumber = achPageNumber;
    }

    public int getAchLineNumber() {
        return achLineNumber;
    }

    public void setAchLineNumber(int achLineNumber) {
        this.achLineNumber = achLineNumber;
    }

    public int getAchRecordTypeNumber() {
        return achRecordTypeNumber;
    }

    public void setAchRecordTypeNumber(int achRecordTypeNumber) {
        this.achRecordTypeNumber = achRecordTypeNumber;
    }

    public int getAchFieldNumber() {
        return achFieldNumber;
    }

    public void setAchFieldNumber(int achFieldNumber) {
        this.achFieldNumber = achFieldNumber;
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

    public ACHFieldRule getAchFieldRule() {
        return achFieldRule;
    }

    public void setAchFieldRule(ACHFieldRule achFieldRule) {
        this.achFieldRule = achFieldRule;
    }

    public ArrayList<ACHValidationTest> getAchValidationTests() {
        return achValidationTests;
    }

    public void setAchValidationTests(ArrayList<ACHValidationTest> achValidationTests) {
        this.achValidationTests = achValidationTests;
    }

    public void appendToValidationTests(ACHValidationTest ACHValidationTest){
        this.achValidationTests.add(ACHValidationTest);
    }
}