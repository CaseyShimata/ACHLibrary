package com.loanpro.achlibrary.dictionary;

import com.loanpro.achlibrary.model.ACHField;
import com.loanpro.achlibrary.model.ACHPage;
import com.loanpro.achlibrary.model.ACHRecord;
import com.loanpro.achlibrary.model.ACHValidationTest;
import com.loanpro.achlibrary.rule.ACHDataTypeRule;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ACHValidationTestSuite {

	//Check if the pages record count is modulo of the expectedRecordCount. it returns a new ACHValidationTest with the results.
	public static void isExpectedPageModulo(ACHPage achPage) {
		String testName = "isExpectedPageModulo";
		String message = "Page" + achPage.getAchPageNumber() + " record count is " + achPage.getAchRecords().size() + " the expected count should be modulo " + achPage.getAchPageRule().getExpectedRecordModulo();
		boolean isPass = achPage.getAchRecords().size() % achPage.getAchPageRule().getExpectedRecordModulo() == 0 ? true : false;

		ACHValidationTest achValidationTest = new ACHValidationTest(achPage.getAchPageNumber(), achPage.getAchPageTypeNumber(), testName, message, isPass);
		achPage.addToAchValidationTests(testName, achValidationTest);

	}

	private static ACHValidationTest isEqualAmountOfRecords(Integer achPageNumber, Integer achPageTypeNumber, int[] first, int[] second) {

		String testName = first[0] + "And" + second[0] + "IsEqualAmountOfRecordsInPage";
		String message = "Record " + first[0] + " and " + second[0] + " have an equal amount of occurrences.";
		boolean isPass = true;


		if (first[1] != second[1]) {
			Map.of(
					"less", first[0],
					"less_val", first[1],
					"greater", second[0],
					"greater_val", second[1]);

			isPass = false;
			message = "There are " + first[1] + " records of type " + first[0] + " and " + second[1] + " records of type " + second[0] +
					" these record types should have the same amount.";
		}
		return new ACHValidationTest(achPageNumber, achPageTypeNumber, testName, message, isPass);
	}

	public static void hasExpectedRecordsOrderAndPairQuantities(ACHPage achPage) {
		ArrayList<ACHRecord> achRecords = achPage.getAchRecords();

		Map<Integer, Integer> recordsInPage = Stream.of(new Integer[][]{
				{1, 0},
				{5, 0},
				{6, 0},
				{8, 0},
				{9, 0}
		}).collect(Collectors.toMap(data -> data[0], data -> data[1]));

		int count = 0;
		for (int i = 0; i < achRecords.size(); i++) {
			ACHRecord achRecord = achRecords.get(i);
			Integer achRecordTypeNumber = achRecord.getAchRecordTypeNumber();
			int[] currentAchRecordsPermittedNextRecordTypes = achRecord.getAchRecordRule().getPermittedNextRecordTypeNumber();

			if (achRecordTypeNumber != 7 && achRecordTypeNumber != 10) {
				recordsInPage.put(achRecordTypeNumber, recordsInPage.get(achRecordTypeNumber) + 1);
			}

			if (i < achRecords.size() - 1) {
				Integer nextAchRecordTypeNumber = achRecords.get(i + 1).getAchRecordTypeNumber();
				Integer achRecordNumber = achRecords.get(i + 1).getAchRecordNumber();

				count += 1;
				if (!IntStream.of(currentAchRecordsPermittedNextRecordTypes).anyMatch(x -> x == nextAchRecordTypeNumber)) {
					//TODO: Change the hashmap for a page/record/file to hold the tests into an array.. it doesn't
					// make sense to be accessing the tests by name any time soon and hashmap may get overwritten tests
					// if not careful in naming of the keys.
					achPage.addToAchValidationTests(count + "record" + nextAchRecordTypeNumber +
									"DoesNotProceedRecord" + achRecordTypeNumber,
							new ACHValidationTest(achPage.getAchPageNumber(),
									achPage.getAchPageTypeNumber(),
									count + "record" + nextAchRecordTypeNumber +
											"DoesNotProceedRecord" + achRecordTypeNumber,
									"Record of type " + achRecordTypeNumber +
											" on line " + achRecordNumber +
											" can not be proceeded by record of type " + nextAchRecordTypeNumber,
									false));
				} else {
					achPage.addToAchValidationTests(count + "record" + nextAchRecordTypeNumber +
									"DoesProceedRecord" + achRecordTypeNumber,
							new ACHValidationTest(achPage.getAchPageNumber(),
									achPage.getAchPageTypeNumber(),
									count + "record" + nextAchRecordTypeNumber +
											"DoesProceedRecord" + achRecordTypeNumber,
									"Record of type " + achRecordTypeNumber +
											" on line " + achRecordNumber +
											" is correctly proceeded by record of type " + nextAchRecordTypeNumber,
									true));
				}

			}
		}

		for (Map.Entry<Integer, Integer> entry : recordsInPage.entrySet()) {
			if (entry.getValue() < 1) {
				achPage.addToAchValidationTests("requiredRecord" +
								entry.getKey() + "IsEmpty",
						new ACHValidationTest(achPage.getAchPageNumber(),
								achPage.getAchPageTypeNumber(),
								"requiredRecord" + entry.getKey() + "IsEmpty",
								"Required record" + entry.getKey() + "is empty",
								false));
			} else {
				achPage.addToAchValidationTests("requiredRecord" +
								entry.getKey() + "Exists",
						new ACHValidationTest(achPage.getAchPageNumber(),
								achPage.getAchPageTypeNumber(),
								"requiredRecord" + entry.getKey() + "Exists",
								"Required record " + entry.getKey() + " exists;",
								true));
			}
		}


		ACHValidationTest oneNine = isEqualAmountOfRecords(achPage.getAchPageNumber(), achPage.getAchPageNumber(), new int[]{1, recordsInPage.get(1)}, new int[]{9, recordsInPage.get(9)});
		achPage.addToAchValidationTests(oneNine.getTestName(), oneNine);

		ACHValidationTest fiveEight = isEqualAmountOfRecords(achPage.getAchPageNumber(), achPage.getAchPageNumber(), new int[]{5, recordsInPage.get(5)}, new int[]{8, recordsInPage.get(8)});
		achPage.addToAchValidationTests(fiveEight.getTestName(), fiveEight);
	}


	public static void isExpectedRecordLength(ACHRecord achRecord) {
		String testName = "isExpectedRecordLength";
		String message = "the record length is " + achRecord.getAchRawRecord().size() + " the expected length is " + achRecord.getAchRecordRule().getExpectedNumberOfCharacters();
		boolean isPass = achRecord.getAchRawRecord().size() == achRecord.getAchRecordRule().getExpectedNumberOfCharacters() ? true : false;

		ACHValidationTest achValidationTest = new ACHValidationTest(achRecord.getAchPageNumber(), achRecord.getAchPageTypeNumber(), achRecord.getAchRecordNumber(), achRecord.getAchRecordTypeNumber(), testName, message, isPass);
		achRecord.addToAchValidationTests(testName, achValidationTest);
	}

	public static void isExpectedDataType(ACHField achField) {
		ACHDataTypeRule achDataTypeRule = achField.getAchFieldRule().getAchFieldDataTypeRule();

		StringBuilder sb = new StringBuilder();
		for (Character ch : achField.getCurrentValue()) {
			sb.append(ch);
		}
		String achArrayFieldToString = sb.toString();

		String testName = "isExpectedDataType";
		String message = "The expected data type is: " + achDataTypeRule.getDataType() +
				" letter case: " + achDataTypeRule.getLetterCase() +
				" justification: " + achDataTypeRule.getJustified() +
				" padding: " + achDataTypeRule.getPaddingType() +
				". The data you submitted was: " + achArrayFieldToString;

		boolean isPass = Pattern.compile(achDataTypeRule.getRegex()).matcher(achArrayFieldToString).matches();

		ACHValidationTest achValidationTest = new ACHValidationTest(achField.getAchPageNumber(), achField.getAchPageTypeNumber(), achField.getAchRecordNumber(), achField.getAchRecordTypeNumber(), achField.getAchFieldRuleNumber(), testName, message, isPass);
		achField.addToAchValidationTests(testName, achValidationTest);
	}

	//page validations:
	//first validation modulo count of records
	//second validation check count of chars in each record
	//third validation check that records are in correct order
	//fourth validation check that all required records are present

}
