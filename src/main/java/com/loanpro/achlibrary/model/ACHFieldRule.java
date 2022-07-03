package com.loanpro.achlibrary.model;

import java.util.Map;
import java.util.function.Consumer;

public final class ACHFieldRule {
    private final Integer achPageTypeNumber;
    private final Integer achRecordTypeNumber;
    private final Integer achFieldNumber;
    private String achFieldName;
    private String achFieldRuleDescription;
    private final Integer expectedCharacterLength;
    private final Integer expectedPositionInRecord;
    private final ACHDataTypeRule achFieldDataTypeRule;
    private final Map<String, Consumer<ACHField>> achFieldRuleTests;

//    private String followsASiblingRecordFieldSequencingRule;
//    private Integer isNumberOfRecordsDividedByTen;
//    private Integer isTheObjectsImmediateChildCount;
//    private Integer isSumOfCountOfTwoChildren;
//    private Integer isValueOfOtherField;
//    private Integer isValueOfOtherFieldInOtherRecord;
//    private Integer isSumOfAllSiblingOfOtherField;
//    private Integer is0dependentOnValueOfOtherField;
//    private Integer isTheCurrentObjectsIndexPlusOne;
//    private Integer isTheEndingNumberOfOtherField;

    private ACHFieldRule(Integer achPageTypeNumber,
                         Integer achRecordTypeNumber,
                         Integer achFieldNumber,
                         Integer expectedCharacterLength,
                         Integer expectedPositionInRecord,
                         ACHDataTypeRule achFieldDataTypeRule,
                         Map<String, Consumer<ACHField>> achFieldRuleTests) {
        this.achPageTypeNumber = achPageTypeNumber;
        this.achRecordTypeNumber = achRecordTypeNumber;
        this.achFieldNumber = achFieldNumber;
        this.expectedCharacterLength = expectedCharacterLength;
        this.expectedPositionInRecord = expectedPositionInRecord;
        this.achFieldDataTypeRule = achFieldDataTypeRule;
        this.achFieldRuleTests = achFieldRuleTests;
    }

    public static ACHFieldRule createNewInstance(Integer achPageTypeNumber,
                                                 Integer achRecordTypeNumber,
                                                 Integer fieldNumber,
                                                 Integer objectsCharacterLengthIs,
                                                 Integer objectTakesUpPositionInRecord,
                                                 ACHDataTypeRule achFieldDataTypeRule,
                                                 Map<String, Consumer<ACHField>> achFieldRuleTests) {
        return new ACHFieldRule(achPageTypeNumber,
                achRecordTypeNumber,
                fieldNumber,
                objectsCharacterLengthIs,
                objectTakesUpPositionInRecord,
                achFieldDataTypeRule,
                achFieldRuleTests);
    }

    public Integer getAchPageTypeNumber() {
        return achPageTypeNumber;
    }

    public Integer getAchRecordTypeNumber() {
        return achRecordTypeNumber;
    }

    public Integer getAchFieldNumber() {
        return achFieldNumber;
    }

    public String getAchFieldName() {
        return achFieldName;
    }

    public String getAchFieldRuleDescription() {
        return achFieldRuleDescription;
    }

    public Integer getExpectedCharacterLength() {
        return expectedCharacterLength;
    }

    public Integer getExpectedPositionInRecord() {
        return expectedPositionInRecord;
    }

    public ACHDataTypeRule getAchFieldDataTypeRule() {
        return achFieldDataTypeRule;
    }

    public Map<String, Consumer<ACHField>> getAchFieldRuleTests() {
        return achFieldRuleTests;
    }
}
