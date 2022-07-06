package com.loanpro.achlibrary.dictionary;

import com.loanpro.achlibrary.model.ACHField;
import com.loanpro.achlibrary.model.ACHPage;
import com.loanpro.achlibrary.model.ACHRecord;
import com.loanpro.achlibrary.model.ACHValidationTest;
import com.loanpro.achlibrary.rule.ACHDataTypeRule;

import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ACHValidationTestSuite {

	//Check if the pages record count is modulo of the expectedRecordCount. it returns a new ACHValidationTest with the results.
	public static void isExpectedPageModulo(ACHPage achPage){
		String testName = "isExpectedPageModulo";
		String message = "Page" + achPage.getAchPageNumber() + " record count is " + achPage.getAchRecords().size() +  " the expected count should be modulo " + achPage.getAchPageRule().getExpectedRecordModulo();
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

	public static void isExpectedDataType(ACHField achField){
		ACHDataTypeRule achDataTypeRule = achField.getAchFieldRule().getAchFieldDataTypeRule();
		String achArrayFieldToString = achField.getCurrentValue().stream()
				.map(e->e.toString())
				.collect(Collectors.joining());

		String testName = "isExpectedDataType";
		String message = "The expected data type is: " + achDataTypeRule.getDataType() +
				" letter case: " + achDataTypeRule.getLetterCase() +
				" justification: " + achDataTypeRule.getJustified() +
				" padding: " + achDataTypeRule.getPaddingType() +
				". The data you submitted was: " + achArrayFieldToString;

		boolean isPass = Pattern.compile(achDataTypeRule.getRegex()).matcher(achArrayFieldToString).matches();

		ACHValidationTest achValidationTest = new ACHValidationTest(achField.getAchPageNumber(), achField.getAchPageTypeNumber(), achField.getAchRecordNumber(), achField.getAchRecordTypeNumber(), testName, message, isPass);
		achField.addToAchValidationTests(testName, achValidationTest);
	}

	//page validations:
	//first validation modulo count of records
	//second validation check count of chars in each record
	//third validation check that records are in correct order
	//fourth validation check that all required records are present

}
