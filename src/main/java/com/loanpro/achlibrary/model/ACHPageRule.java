package com.loanpro.achlibrary.model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ACHPageRule {

    private final int expectedRecordModulo;
    private final HashMap<Integer, ACHRecordRule> achRecordRules;
    private final Map<String, Consumer<ACHPage>> achPageRuleTests;


    private ACHPageRule(int expectedRecordModulo, HashMap<Integer, ACHRecordRule> achRecordRules, Map<String, Consumer<ACHPage>> achPageRuleTests) {
        this.expectedRecordModulo = expectedRecordModulo;
        this.achRecordRules = achRecordRules;
        this.achPageRuleTests = achPageRuleTests;
    }

    public static ACHPageRule createNewInstance(int expectedRecordCountModulo, HashMap<Integer, ACHRecordRule> achRecordRules, Map<String, Consumer<ACHPage>> achPageRuleTests){
        return new ACHPageRule(expectedRecordCountModulo, achRecordRules, achPageRuleTests);
    }

    public int getExpectedRecordModulo() {
        return expectedRecordModulo;
    }

    public ACHRecordRule getOneAchRecordRules(int achRecordTypeNumber) {
        return this.achRecordRules.get(achRecordTypeNumber);
    }
}
