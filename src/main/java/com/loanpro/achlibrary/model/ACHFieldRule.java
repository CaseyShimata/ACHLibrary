package com.loanpro.achlibrary.model;

public final class ACHFieldRule {
    final private int recordNumber;
    final private int fieldNumber;
    final private String fieldName;
    final private String fieldRuleDescription;
    final private String paddingPosition;
    final private String paddingChar;
    final private Integer paddingLength;
    final private String dataTypeIs;
    final private Integer objectsCharacterLengthIs;
    final private int[] objectTakesUpPositionInRecord;
    final private String followsASiblingRecordFieldSequencingRule;
    final private Integer isNumberOfRecordsDividedByTen;
    final private Integer isTheObjectsImmediateChildCount;
    final private Integer isSumOfCountOfTwoChildren;
    final private Integer isValueOfOtherField;
    final private Integer isValueOfOtherFieldInOtherRecord;
    final private Integer isSumOfAllSiblingOfOtherField;
    final private Integer is0dependentOnValueOfOtherField;
    final private Integer isTheCurrentObjectsIndexPlusOne;
    final private Integer isTheEndingNumberOfOtherField;
    final private int[] isOneOfASetOfValues;

    private ACHFieldRule(int recordNumber, int fieldNumber, String fieldName, String fieldRuleDescription, String paddingPosition, String paddingChar, Integer paddingLength, String dataTypeIs, Integer objectsCharacterLengthIs, int[] objectTakesUpPositionInRecord, String followsASiblingRecordFieldSequencingRule, Integer isNumberOfRecordsDividedByTen, Integer isTheObjectsImmediateChildCount, Integer isSumOfCountOfTwoChildren, Integer isValueOfOtherField, Integer isValueOfOtherFieldInOtherRecord, Integer isSumOfAllSiblingOfOtherField, Integer is0dependentOnValueOfOtherField, Integer isTheCurrentObjectsIndexPlusOne, Integer isTheEndingNumberOfOtherField, int[] isOneOfASetOfValues) {
        this.recordNumber = recordNumber;
        this.fieldNumber = fieldNumber;
        this.fieldName = fieldName;
        this.fieldRuleDescription = fieldRuleDescription;
        this.paddingPosition = paddingPosition;
        this.paddingChar = paddingChar;
        this.paddingLength = paddingLength;
        this.dataTypeIs = dataTypeIs;
        this.objectsCharacterLengthIs = objectsCharacterLengthIs;
        this.objectTakesUpPositionInRecord = objectTakesUpPositionInRecord;
        this.followsASiblingRecordFieldSequencingRule = followsASiblingRecordFieldSequencingRule;
        this.isNumberOfRecordsDividedByTen = isNumberOfRecordsDividedByTen;
        this.isTheObjectsImmediateChildCount = isTheObjectsImmediateChildCount;
        this.isSumOfCountOfTwoChildren = isSumOfCountOfTwoChildren;
        this.isValueOfOtherField = isValueOfOtherField;
        this.isValueOfOtherFieldInOtherRecord = isValueOfOtherFieldInOtherRecord;
        this.isSumOfAllSiblingOfOtherField = isSumOfAllSiblingOfOtherField;
        this.is0dependentOnValueOfOtherField = is0dependentOnValueOfOtherField;
        this.isTheCurrentObjectsIndexPlusOne = isTheCurrentObjectsIndexPlusOne;
        this.isTheEndingNumberOfOtherField = isTheEndingNumberOfOtherField;
        this.isOneOfASetOfValues = isOneOfASetOfValues;
    }

    public static ACHFieldRule createNewInstance(int recordNumber, int fieldNumber, String fieldName, String fieldRuleDescription, String paddingPosition, String paddingChar, Integer paddingLength, String dataTypeIs, Integer objectsCharacterLengthIs, int[] objectTakesUpPositionInRecord, String followsASiblingRecordFieldSequencingRule, Integer isNumberOfRecordsDividedByTen, Integer isTheObjectsImmediateChildCount, Integer isSumOfCountOfTwoChildren, Integer isValueOfOtherField, Integer isValueOfOtherFieldInOtherRecord, Integer isSumOfAllSiblingOfOtherField, Integer is0dependentOnValueOfOtherField, Integer isTheCurrentObjectsIndexPlusOne, Integer isTheEndingNumberOfOtherField, int[] isOneOfASetOfValues) {
        return new ACHFieldRule(recordNumber, fieldNumber, fieldName, fieldRuleDescription, paddingPosition, paddingChar, paddingLength, dataTypeIs, objectsCharacterLengthIs, objectTakesUpPositionInRecord, followsASiblingRecordFieldSequencingRule, isNumberOfRecordsDividedByTen, isTheObjectsImmediateChildCount, isSumOfCountOfTwoChildren, isValueOfOtherField, isValueOfOtherFieldInOtherRecord, isSumOfAllSiblingOfOtherField, is0dependentOnValueOfOtherField, isTheCurrentObjectsIndexPlusOne, isTheEndingNumberOfOtherField,isOneOfASetOfValues);
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

    public Integer getObjectsCharacterLengthIs() {
        return objectsCharacterLengthIs;
    }

    public int[] getObjectTakesUpPositionInRecord() {
        return objectTakesUpPositionInRecord;
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
