package com.loanpro.achlibrary.model;

public class ACHValidationTest {

	private int achPageNumber;
	private Integer achPageTypeNumber;
	private int achRecordNumber;
	private Integer achRecordTypeNumber;
	private int achFieldRuleNumber;
	private String testName;
	private String message;
	private boolean didPass;

	//todo: possibly extend the class instead of creating separate constructors.

	//page test constructor
	public ACHValidationTest(int achPageNumber, Integer achPageTypeNumber, String testName, String message, boolean didPass) {
		this.achPageNumber = achPageNumber;
		this.achPageTypeNumber = achPageTypeNumber;
		this.testName = testName;
		this.message = message;
		this.didPass = didPass;
	}

	//record test constructor

	public ACHValidationTest(int achPageNumber, Integer achPageTypeNumber, int achRecordNumber, Integer achRecordTypeNumber, String testName, String message, boolean didPass) {
		this.achPageNumber = achPageNumber;
		this.achPageTypeNumber = achPageTypeNumber;
		this.achRecordNumber = achRecordNumber;
		this.achRecordTypeNumber = achRecordTypeNumber;
		this.testName = testName;
		this.message = message;
		this.didPass = didPass;
	}

	//field test constructor
	public ACHValidationTest(int achPageNumber, Integer achPageTypeNumber, int achRecordNumber, Integer achRecordTypeNumber, int achFieldRuleNumber, String testName, String message, boolean didPass) {
		this.achPageNumber = achPageNumber;
		this.achPageTypeNumber = achPageTypeNumber;
		this.achRecordNumber = achRecordNumber;
		this.achRecordTypeNumber = achRecordTypeNumber;
		this.achFieldRuleNumber = achFieldRuleNumber;
		this.testName = testName;
		this.message = message;
		this.didPass = didPass;
	}

	public int getAchPageNumber() {
		return achPageNumber;
	}

	public Integer getAchPageTypeNumber() {
		return achPageTypeNumber;
	}

	public int getAchRecordNumber() {
		return achRecordNumber;
	}

	public Integer getAchRecordTypeNumber() {
		return achRecordTypeNumber;
	}

	public int getAchFieldRuleNumber() {
		return achFieldRuleNumber;
	}

	public String getTestName() {
		return testName;
	}

	public String getMessage() {
		return message;
	}

	public boolean isDidPass() {
		return didPass;
	}
}
