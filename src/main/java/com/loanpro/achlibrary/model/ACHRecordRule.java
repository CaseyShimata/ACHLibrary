package com.loanpro.achlibrary.model;

import com.loanpro.achlibrary.dictionary.ACHValidationTestSuite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

import static com.loanpro.achlibrary.dictionary.ACHValidationTestSuite.isExpectedRecordLength;

public class ACHRecordRule {

	private final int achPageTypeNumber;
	private final int achRecordTypeNumber;
	private final String achRecordType;
	private final int expectedNumberOfFields;
	private final int permittedPreviousRecordTypeNumber[];
	private final int permittedNextRecordTypeNumber[];
	private final boolean isPossiblePaddingLine;
	private final boolean required;
	private final int expectedNumberOfCharacters;
	private final HashMap<Integer, ACHFieldRule> achFieldRules;
	//Stores the tests for this rule. It might source from a test suite so that different rules can reuse that test
	private final Map<String, Consumer<ACHRecord>> achRecordRuleTests;

	private ACHRecordRule(int achPageTypeNumber, int achRecordTypeNumber, String achRecordType, int expectedNumberOfFields, int[] permittedPreviousRecordTypeNumber, int[] permittedNextRecordTypeNumber, boolean isPossiblePaddingLine, boolean required, int expectedNumberOfCharacters, HashMap<Integer, ACHFieldRule> achFieldRules, Map<String, Consumer<ACHRecord>> achRecordRuleTests) {
		this.achPageTypeNumber = achPageTypeNumber;
		this.achRecordTypeNumber = achRecordTypeNumber;
		this.achRecordType = achRecordType;
		this.expectedNumberOfFields = expectedNumberOfFields;
		this.permittedPreviousRecordTypeNumber = permittedPreviousRecordTypeNumber;
		this.permittedNextRecordTypeNumber = permittedNextRecordTypeNumber;
		this.isPossiblePaddingLine = isPossiblePaddingLine;
		this.required = required;
		this.expectedNumberOfCharacters = expectedNumberOfCharacters;
		this.achFieldRules = achFieldRules;
		this.achRecordRuleTests = achRecordRuleTests;
	}

	public static ACHRecordRule createNewInstance(int achPageTypeNumber,
	                                              int achRecordTypeNumber,
	                                              String achRecordType,
	                                              int expectedNumberOfFields,
	                                              int[] permittedPreviousRecordTypeNumber,
	                                              int[] permittedNextRecordTypeNumber,
	                                              boolean isPossiblePaddingLine,
	                                              boolean required,
	                                              int expectedNumberOfCharacters,
	                                              HashMap<Integer, ACHFieldRule> achFieldRules, HashMap<String, Consumer<ACHRecord>> achRecordRuleTests) {

		return new ACHRecordRule(achPageTypeNumber,
				achRecordTypeNumber,
				achRecordType,
				expectedNumberOfFields,
				permittedPreviousRecordTypeNumber,
				permittedNextRecordTypeNumber,
				isPossiblePaddingLine,
				required,
				expectedNumberOfCharacters,
				achFieldRules, achRecordRuleTests);
	}

	public int getAchPageTypeNumber() {
		return achPageTypeNumber;
	}

	public int getAchRecordTypeNumber() {
		return achRecordTypeNumber;
	}

	public String getAchRecordType() {
		return achRecordType;
	}

	public int getExpectedNumberOfFields() {
		return expectedNumberOfFields;
	}

	public int[] getPermittedPreviousRecordTypeNumber() {
		return permittedPreviousRecordTypeNumber;
	}

	public int[] getPermittedNextRecordTypeNumber() {
		return permittedNextRecordTypeNumber;
	}

	public boolean isPossiblePaddingLine() {
		return isPossiblePaddingLine;
	}

	public boolean isRequired() {
		return required;
	}

	public int getExpectedNumberOfCharacters() {
		return expectedNumberOfCharacters;
	}

	public HashMap<Integer, ACHFieldRule> getAchFieldRules() {
		return achFieldRules;
	}

	public ACHFieldRule getOneAchFieldRules(int achFieldNumber) {
		return this.achFieldRules.get(achFieldNumber);
	}

	public Map<String, Consumer<ACHRecord>> getAchRecordRuleTests() {
		return achRecordRuleTests;
	}
}
