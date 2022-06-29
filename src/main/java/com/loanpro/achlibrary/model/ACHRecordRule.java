package com.loanpro.achlibrary.model;

public class ACHRecordRule {
    private final Boolean required;
    private final String paddingPosition;
    private final String paddingChar;
    private final Integer paddingLength;
    private final Integer numberOfFields;
    private final Integer numberOfCharacters;
    private final Integer reportedChars;

    private ACHRecordRule(Boolean required, String paddingPosition, String paddingChar, Integer paddingLength, Integer numberOfFields, Integer numberOfCharacters, Integer reportedChars) {
        this.required = required;
        this.paddingPosition = paddingPosition;
        this.paddingChar = paddingChar;
        this.paddingLength = paddingLength;
        this.numberOfFields = numberOfFields;
        this.numberOfCharacters = numberOfCharacters;
        this.reportedChars = reportedChars;
    }

    public static ACHRecordRule createNewInstance(Boolean required, String paddingPosition, String paddingChar, Integer paddingLength, Integer numberOfFields, Integer numberOfCharacters, Integer reportedChars) {
        return new ACHRecordRule(required, paddingPosition, paddingChar, paddingLength, numberOfFields, numberOfCharacters, reportedChars);
    }

    public Boolean getRequired() {
        return required;
    }

    public String getPaddingPosition() {
        return paddingPosition;
    }

    public String getPaddingChar() {
        return paddingChar;
    }

    public Integer getPaddingLength() {
        return paddingLength;
    }

    public Integer getNumberOfFields() {
        return numberOfFields;
    }

    public Integer getNumberOfCharacters() {
        return numberOfCharacters;
    }

    public Integer getReportedChars() {
        return reportedChars;
    }
}
