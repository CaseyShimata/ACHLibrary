package com.loanpro.achlibrary.model;

public final class ACHFieldRule {
    final private int achFileNumber;
    final private int recordNumber;
    final private int fieldNumber;
    final private Integer objectsCharacterLengthIs;
    final private Integer objectTakesUpPositionInRecord;
    private String fieldName;
    private String fieldRuleDescription;
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


    private ACHFieldRule(int achFileNumber, int recordNumber, int fieldNumber, Integer objectsCharacterLengthIs, Integer objectTakesUpPositionInRecord) {
        this.achFileNumber = achFileNumber;
        this.recordNumber = recordNumber;
        this.fieldNumber = fieldNumber;
        this.objectsCharacterLengthIs = objectsCharacterLengthIs;
        this.objectTakesUpPositionInRecord = objectTakesUpPositionInRecord;
    }

    public static ACHFieldRule createNewInstance(int achFileNumber, int recordNumber, int fieldNumber, Integer objectsCharacterLengthIs, Integer objectTakesUpPositionInRecord) {
        return new ACHFieldRule(achFileNumber, recordNumber, fieldNumber,objectsCharacterLengthIs , objectTakesUpPositionInRecord);
    }


    public int getAchFileNumber() {
        return achFileNumber;
    }

    public int getRecordNumber() {
        return recordNumber;
    }

    public int getFieldNumber() {
        return fieldNumber;
    }

    public Integer getObjectsCharacterLengthIs() {
        return objectsCharacterLengthIs;
    }

    public Integer getObjectTakesUpPositionInRecord() {
        return objectTakesUpPositionInRecord;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldRuleDescription() {
        return fieldRuleDescription;
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

    public String getDataTypeIs() {
        return dataTypeIs;
    }

    public String getFollowsASiblingRecordFieldSequencingRule() {
        return followsASiblingRecordFieldSequencingRule;
    }

    public Integer getIsNumberOfRecordsDividedByTen() {
        return isNumberOfRecordsDividedByTen;
    }

    public Integer getIsTheObjectsImmediateChildCount() {
        return isTheObjectsImmediateChildCount;
    }

    public Integer getIsSumOfCountOfTwoChildren() {
        return isSumOfCountOfTwoChildren;
    }

    public Integer getIsValueOfOtherField() {
        return isValueOfOtherField;
    }

    public Integer getIsValueOfOtherFieldInOtherRecord() {
        return isValueOfOtherFieldInOtherRecord;
    }

    public Integer getIsSumOfAllSiblingOfOtherField() {
        return isSumOfAllSiblingOfOtherField;
    }

    public Integer getIs0dependentOnValueOfOtherField() {
        return is0dependentOnValueOfOtherField;
    }

    public Integer getIsTheCurrentObjectsIndexPlusOne() {
        return isTheCurrentObjectsIndexPlusOne;
    }

    public Integer getIsTheEndingNumberOfOtherField() {
        return isTheEndingNumberOfOtherField;
    }

    public int[] getIsOneOfASetOfValues() {
        return isOneOfASetOfValues;
    }
}
