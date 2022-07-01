package com.loanpro.achlibrary.model;

import java.util.ArrayList;

public class ACHPage {
    private int pageNumber;
    private String achRawPage;
    private ArrayList<ACHRecord> achRecords = new ArrayList<ACHRecord>();
    private ACHPageRule achPageRule;
    private ArrayList<ACHValidationTest> achValidationTests;



    public ACHPage(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getAchRawPage() {
        return achRawPage;
    }

    public void setAchRawPage(String achRawPage) {
        this.achRawPage = achRawPage;
    }

    public ACHRecord getOneInAchRecords(int index){
        return this.achRecords.get(index);
    }

    public ArrayList<ACHRecord> getAchRecords() {
        return achRecords;
    }

    public void setAchRecords(ArrayList<ACHRecord> achRecords) {
        this.achRecords = achRecords;
    }

    public void appendToRecords(ACHRecord achRecord){
        this.achRecords.add(achRecord);
    }

    public void insertToAchRecords(ACHRecord achRecord, int index){
        this.achRecords.add(index, achRecord);
    }

    public ACHPageRule getAchPageRule() {
        return achPageRule;
    }

    public void setAchPageRule(ACHPageRule achPageRule) {
        this.achPageRule = achPageRule;
    }

    public ArrayList<ACHValidationTest> getAchValidationTests() {
        return achValidationTests;
    }

    public void setACHValidationTests(ArrayList<ACHValidationTest> achValidationTests) {
        this.achValidationTests = achValidationTests;
    }

}