package com.loanpro.achlibrary.model;

public class ACHRecordRule {
    private final String achRecordNumber;
    private final String achRecordType;
    private final Boolean required;
    private final String expectedPaddingPosition;
    private final String expectedPaddingChar;
    private final Integer expectedPaddingLength;
    private final Integer expectedNumberOfFields;
    private final Integer expectedNumberOfCharacters;

    //todo: use this when fully setting up the dictionary
    public ACHRecordRule(String achRecordNumber, String achRecordType, Boolean required, String expectedPaddingPosition, String expectedPaddingChar, Integer expectedPaddingLength, Integer expectedNumberOfFields, Integer expectedNumberOfCharacters) {
        this.achRecordNumber = achRecordNumber;
        this.achRecordType = achRecordType;
        this.required = required;
        this.expectedPaddingPosition = expectedPaddingPosition;
        this.expectedPaddingChar = expectedPaddingChar;
        this.expectedPaddingLength = expectedPaddingLength;
        this.expectedNumberOfFields = expectedNumberOfFields;
        this.expectedNumberOfCharacters = expectedNumberOfCharacters;
    }


    public static ACHRecordRule createNewInstance(String achRecordNumber, String achRecordType, Boolean required, String expectedPaddingPosition, String expectedPaddingChar, Integer expectedPaddingLength, Integer expectedNumberOfFields, Integer expectedNumberOfCharacters) {
        return new ACHRecordRule(achRecordNumber,achRecordType,required, expectedPaddingPosition, expectedPaddingChar, expectedPaddingLength, expectedNumberOfFields, expectedNumberOfCharacters);
    }

    public Boolean getRequired() {
        return required;
    }

    public String getExpectedPaddingPosition() {
        return expectedPaddingPosition;
    }

    public String getExpectedPaddingChar() {
        return expectedPaddingChar;
    }

    public Integer getExpectedPaddingLength() {
        return expectedPaddingLength;
    }

    public Integer getExpectedNumberOfFields() {
        return expectedNumberOfFields;
    }

    public Integer getExpectedNumberOfCharacters() {
        return expectedNumberOfCharacters;
    }

}
