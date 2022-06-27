package com.loanpro.achlibrary.model;

import java.util.List;

public final class FieldRules {
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

    private FieldRules(String fieldName, String fieldRuleDescription, String paddingPosition, String paddingChar, Integer paddingLength, String dataTypeIs, Integer objectsCharacterLengthIs, int[] objectTakesUpPositionInRecord, String followsASiblingRecordFieldSequencingRule, Integer isNumberOfRecordsDividedByTen, Integer isTheObjectsImmediateChildCount, Integer isSumOfCountOfTwoChildren, Integer isValueOfOtherField, Integer isValueOfOtherFieldInOtherRecord, Integer isSumOfAllSiblingOfOtherField, Integer is0dependentOnValueOfOtherField, Integer isTheCurrentObjectsIndexPlusOne, Integer isTheEndingNumberOfOtherField, int[] isOneOfASetOfValues) {
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

    

}
