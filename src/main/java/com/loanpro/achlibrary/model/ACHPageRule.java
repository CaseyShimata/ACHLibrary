package com.loanpro.achlibrary.model;

public class ACHPageRule {

    private final int expectedRecordCount;

    public ACHPageRule(int expectedRecordCount) {
        this.expectedRecordCount = expectedRecordCount;
    }

    public int getExpectedRecordCount() {
        return expectedRecordCount;
    }

    //Check if the pages record count is modulo of the expectedRecordCount. it returns a new ACHValidationTest with the results.
    public final ACHValidationTest isExpectedRecordCount(ACHPage achPage){
        String message = "the record count is " + achPage.getAchRecords().size() +  " the expected count is " + expectedRecordCount ;
        boolean isPass = achPage.getAchRecords().size() % expectedRecordCount == 0 ? true : false;

        return new ACHValidationTest(achPage.getPageNumber(),"isExpectedRecordCount", message, isPass);
    }
}
