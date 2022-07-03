package com.loanpro.achlibrary.dictionary;

import com.loanpro.achlibrary.model.ACHPage;
import com.loanpro.achlibrary.model.ACHRecord;
import com.loanpro.achlibrary.model.ACHValidationTest;

public class ACHValidationTestSuite {

	//Check if the pages record count is modulo of the expectedRecordCount. it returns a new ACHValidationTest with the results.
	public static void isExpectedRecordModulo(ACHPage achPage){
		String testName = "isExpectedRecordModulo";
		String message = "the record count is " + achPage.getAchRecords().size() +  " the expected count should be modulo " + achPage.getAchPageRule().getExpectedRecordModulo();
		boolean isPass = achPage.getAchRecords().size() % achPage.getAchPageRule().getExpectedRecordModulo()  == 0 ? true : false;

		ACHValidationTest achValidationTest = new ACHValidationTest(achPage.getAchPageNumber(), achPage.getAchPageTypeNumber(), testName, message, isPass);
		achPage.addToAchValidationTests(testName, achValidationTest);

	}

	public static void isExpectedRecordLength(ACHRecord achRecord){
		String testName = "isExpectedRecordLength";
		String message = "the record length is " + achRecord.getAchRawRecord().size() +  " the expected length is " + achRecord.getAchRecordRule().getExpectedNumberOfCharacters();
		boolean isPass = achRecord.getAchRawRecord().size() == achRecord.getAchRecordRule().getExpectedNumberOfCharacters() ? true : false;

		ACHValidationTest achValidationTest = new ACHValidationTest(achRecord.getAchPageNumber(), achRecord.getAchPageTypeNumber(), achRecord.getAchRecordNumber(), achRecord.getAchRecordTypeNumber(), testName, message, isPass);
		achRecord.addToAchValidationTests(testName, achValidationTest);
	}

	//page validations:
	//first validation modulo count of records
	//second validation check count of chars in each record
	//third validation check that records are in correct order
	//fourth validation check that all required records are present

}
