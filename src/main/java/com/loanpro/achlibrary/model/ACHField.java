package com.loanpro.achlibrary.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ACHField {
    private int achPageNumber;
    private int achPageTypeNumber;
    private int achRecordNumber;
    private int achRecordTypeNumber;
    private int achFieldRuleNumber;
    private ArrayList<Character> currentValue;
    private Integer currentPosition;
    private ACHFieldRule achFieldRule;
    private HashMap<String, ACHValidationTest> achValidationTests;

    public ACHField(int achPageNumber, int achPageTypeNumber, int achRecordNumber, int achRecordTypeNumber, int achFieldRuleNumber, ArrayList<Character> currentValue, Integer currentPosition, ACHFieldRule achFieldRule) {
        this.achPageNumber = achPageNumber;
        this.achPageTypeNumber = achPageTypeNumber;
        this.achRecordNumber = achRecordNumber;
        this.achRecordTypeNumber = achRecordTypeNumber;
        this.achFieldRuleNumber = achFieldRuleNumber;
        this.currentValue = currentValue;
        this.currentPosition = currentPosition;
        this.achFieldRule = achFieldRule;
    }

    public int getAchPageNumber() {
        return achPageNumber;
    }

    public void setAchPageNumber(int achPageNumber) {
        this.achPageNumber = achPageNumber;
    }

    public int getAchRecordNumber() {
        return achRecordNumber;
    }

    public void setAchRecordNumber(int achRecordNumber) {
        this.achRecordNumber = achRecordNumber;
    }

    public int getAchRecordTypeNumber() {
        return achRecordTypeNumber;
    }

    public void setAchRecordTypeNumber(int achRecordTypeNumber) {
        this.achRecordTypeNumber = achRecordTypeNumber;
    }

    public int getAchFieldRuleNumber() {
        return achFieldRuleNumber;
    }

    public void setAchFieldRuleNumber(int achFieldRuleNumber) {
        this.achFieldRuleNumber = achFieldRuleNumber;
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

    public HashMap<String, ACHValidationTest> getAchValidationTests() {
        return achValidationTests;
    }

    public void setAchValidationTests(HashMap<String, ACHValidationTest> achValidationTests) {
        this.achValidationTests = achValidationTests;
    }

    public void addToAchValidationTests(String achValidationTestName, ACHValidationTest ACHValidationTest){
        this.achValidationTests.put(achValidationTestName, ACHValidationTest);
    }

    public void runAchFieldValidationTests(){
        // Run the ACHField's ACHFieldRule's tests adding the resulting ACHValidationTests to the current ACHField
        for (Map.Entry<String, Consumer<ACHField>> test : this.achFieldRule.getAchFieldRuleTests().entrySet()) {
            Consumer<ACHField> achValidationTest = test.getValue();
//            TODO: Add the Verify DataType regex to the tests.
            achValidationTest.accept(this);
        }
    }
}