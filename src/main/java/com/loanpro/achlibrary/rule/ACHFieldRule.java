package com.loanpro.achlibrary.rule;

import com.loanpro.achlibrary.model.ACHField;

import java.util.Map;
import java.util.function.Consumer;

public final class ACHFieldRule {
    private final Integer achPageTypeNumber;
    private final Integer achRecordTypeNumber;
    private final Integer achFieldRuleNumber;
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
                         Integer achFieldRuleNumber,
                         Integer expectedCharacterLength,
                         Integer expectedPositionInRecord,
                         ACHDataTypeRule achFieldDataTypeRule,
                         Map<String, Consumer<ACHField>> achFieldRuleTests) {
        this.achPageTypeNumber = achPageTypeNumber;
        this.achRecordTypeNumber = achRecordTypeNumber;
        this.achFieldRuleNumber = achFieldRuleNumber;
        this.expectedCharacterLength = expectedCharacterLength;
        this.expectedPositionInRecord = expectedPositionInRecord;
        this.achFieldDataTypeRule = achFieldDataTypeRule;
        this.achFieldRuleTests = achFieldRuleTests;
    }

    public static ACHFieldRule createNewInstance(Integer achPageTypeNumber,
                                                 Integer achRecordTypeNumber,
                                                 Integer achFieldRuleNumber,
                                                 Integer objectsCharacterLengthIs,
                                                 Integer objectTakesUpPositionInRecord,
                                                 ACHDataTypeRule achFieldDataTypeRule,
                                                 Map<String, Consumer<ACHField>> achFieldRuleTests) {
        return new ACHFieldRule(achPageTypeNumber,
                achRecordTypeNumber,
                achFieldRuleNumber,
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

    public Integer getAchFieldRuleNumber() {
        return achFieldRuleNumber;
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
