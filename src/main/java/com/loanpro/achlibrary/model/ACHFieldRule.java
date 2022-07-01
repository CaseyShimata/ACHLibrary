package com.loanpro.achlibrary.model;

public final class ACHFieldRule {
    final private int achRecordNumber;
    final private int achFieldNumber;
    private String achFieldName;
    private String achFieldRuleDescription;
    final private Integer objectsCharacterLengthIs;
    final private Integer objectTakesUpPositionInRecord;



    private String paddingPosition;
    private String paddingChar;
    private Integer paddingLength;
    private String dataTypeIs;
    private String followsASiblingRecordFieldSequencingRule;
    private Integer isNumberOfRecordsDividedByTen;
    private Integer isTheObjectsImmediateChildCount;
    private Integer isSumOfCountOfTwoChildren;
    private Integer isValueOfOtherField;
    private Integer isValueOfOtherFieldInOtherRecord;
    private Integer isSumOfAllSiblingOfOtherField;
    private Integer is0dependentOnValueOfOtherField;
    private Integer isTheCurrentObjectsIndexPlusOne;
    private Integer isTheEndingNumberOfOtherField;
    private int[] isOneOfASetOfValues;


    private ACHFieldRule(int achRecordNumber, int achFieldNumber, Integer objectsCharacterLengthIs, Integer objectTakesUpPositionInRecord) {
        this.achRecordNumber = achRecordNumber;
        this.achFieldNumber = achFieldNumber;
        this.objectsCharacterLengthIs = objectsCharacterLengthIs;
        this.objectTakesUpPositionInRecord = objectTakesUpPositionInRecord;
    }

    public static ACHFieldRule createNewInstance(int recordNumber, int fieldNumber, Integer objectsCharacterLengthIs, Integer objectTakesUpPositionInRecord) {
        return new ACHFieldRule(recordNumber, fieldNumber,objectsCharacterLengthIs , objectTakesUpPositionInRecord);
    }

    public int getAchRecordNumber() {
        return achRecordNumber;
    }

    public int getAchFieldNumber() {
        return achFieldNumber;
    }

    public String getAchFieldName() {
        return achFieldName;
    }

    public String getAchFieldRuleDescription() {
        return achFieldRuleDescription;
    }

    public Integer getObjectsCharacterLengthIs() {
        return objectsCharacterLengthIs;
    }

    public Integer getObjectTakesUpPositionInRecord() {
        return objectTakesUpPositionInRecord;
    }
}
