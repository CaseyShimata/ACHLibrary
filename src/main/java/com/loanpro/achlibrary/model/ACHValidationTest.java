package com.loanpro.achlibrary.model;

public class ACHValidationTest {

	private int achPageNumber;
	private int achLineNumber;
	private int achRecordNumber;
	private int achFieldNumber;
	private String testName;
	private String message;
	private boolean didPass;

	//todo: possibly extend the class instead of creating separate constructors.

	public ACHValidationTest(int achPageNumber, String testName, String message, boolean didPass) {
		this.achPageNumber = achPageNumber;
		this.testName = testName;
		this.message = message;
		this.didPass = didPass;
	}

	public ACHValidationTest(int achPageNumber, int achLineNumber, int achRecordNumber, String testName, String message, boolean didPass) {
		this.achPageNumber = achPageNumber;
		this.achLineNumber = achLineNumber;
		this.achRecordNumber = achRecordNumber;
		this.testName = testName;
		this.message = message;
		this.didPass = didPass;
	}

	public ACHValidationTest(int achPageNumber, int achLineNumber, int achRecordNumber, int achFieldNumber, String testName, String message, boolean didPass) {
		this.achPageNumber = achPageNumber;
		this.achLineNumber = achLineNumber;
		this.achRecordNumber = achRecordNumber;
		this.achFieldNumber = achFieldNumber;
		this.testName = testName;
		this.message = message;
		this.didPass = didPass;
	}

	public int getAchPageNumber() {
		return achPageNumber;
	}

	public int getAchLineNumber() {
		return achLineNumber;
	}

	public int getAchRecordNumber() {
		return achRecordNumber;
	}

	public int getAchFieldNumber() {
		return achFieldNumber;
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
