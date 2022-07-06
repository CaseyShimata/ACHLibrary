package com.loanpro.achlibrary.dictionary;

import com.loanpro.achlibrary.model.*;
import com.loanpro.achlibrary.rule.ACHDataTypeRule;
import com.loanpro.achlibrary.rule.ACHFieldRule;
import com.loanpro.achlibrary.rule.ACHPageRule;
import com.loanpro.achlibrary.rule.ACHRecordRule;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static com.loanpro.achlibrary.dictionary.ACHValidationTestSuite.isExpectedRecordLength;
import static com.loanpro.achlibrary.dictionary.ACHValidationTestSuite.isExpectedDataType;
import static com.loanpro.achlibrary.dictionary.ACHValidationTestSuite.isExpectedPageModulo;

public class ACHRuleDictionary {

	public static final HashMap<Integer, ACHPageRule> achRules = buildRules();

	public static ACHRecordRule getAchRecordRule(int achPageTypeNumber, int achRecordTypeNumber) {
		return achRules.get(achPageTypeNumber)
				.getOneAchRecordRules(achRecordTypeNumber);
	}

	public static ACHFieldRule getAchFieldRule(int achPageTypeNumber, int achRecordTypeNumber, int achFieldRuleNumber) {
		return achRules.get(achPageTypeNumber)
				.getOneAchRecordRules(achRecordTypeNumber)
				.getOneAchFieldRules(achFieldRuleNumber);
	}

	private static final HashMap<Integer, ACHPageRule> buildRules() {

		HashMap<Integer, ACHPageRule> achRules = new HashMap<Integer, ACHPageRule>();

		int pageTypes = 1;
		Integer achPageTypeNumber = 1;
		while (achPageTypeNumber <= pageTypes) {

			HashMap<Integer, ACHRecordRule> achRecordRules = new HashMap<Integer, ACHRecordRule>();

			Map<Integer, Integer> recordFieldGuide = Map.of(
					1, 13,
					5, 13,
					6, 11,
					7, 11,
					8, 11,
					9, 8
			);

			for (Map.Entry<Integer, Integer> entry : recordFieldGuide.entrySet()) {
				//Initialize outer array keys
				Integer achRecordTypeNumber = entry.getKey();
				Integer numberOfAchFieldsinRecord = entry.getValue();

				HashMap<Integer, ACHFieldRule> achFieldRules = new HashMap<Integer, ACHFieldRule>(numberOfAchFieldsinRecord);

				Integer achFieldRuleNumberBeingAdded = 1;
				while (achFieldRuleNumberBeingAdded <= numberOfAchFieldsinRecord) {

					ACHFieldRule fieldRule = mapFieldRule(achPageTypeNumber, achRecordTypeNumber, achFieldRuleNumberBeingAdded);
					achFieldRules.put(achFieldRuleNumberBeingAdded, fieldRule);
					achFieldRuleNumberBeingAdded += 1;
				}

				ACHRecordRule achRecordRule = mapRecordRule(achPageTypeNumber, achRecordTypeNumber, achFieldRules);

				achRecordRules.put(achRecordTypeNumber, achRecordRule);
			}
			ACHPageRule achPageRule = mapPageRule(achPageTypeNumber, achRecordRules);
			achRules.put(achPageTypeNumber, achPageRule);
			achPageTypeNumber += 1;
		}
		return achRules;
	}

	private static final ACHPageRule mapPageRule(Integer achPageTypeNumber, HashMap<Integer, ACHRecordRule> achRecordRules) {

		int expectedRecordModulo;

		HashMap<String, Consumer<ACHPage>> achPageRuleTests = new HashMap<String, Consumer<ACHPage>>();

		//TODO: Map other tests in ACHValidationTestSuite to each ACHRecordRule.
		achPageRuleTests.put("isExpectedPageModulo", (achPage) -> isExpectedPageModulo(achPage));

		switch (achPageTypeNumber) {
			case 1:
				expectedRecordModulo = 10;
				break;
			default:
				throw new IllegalStateException("Unexpected value while creating an ACHPageRule got: " + achPageTypeNumber.toString().concat(achPageTypeNumber.toString()));
		}

		return ACHPageRule.createNewInstance(expectedRecordModulo, achRecordRules, achPageRuleTests);
	}

	private static final ACHRecordRule mapRecordRule(Integer achPageTypeNumber, Integer achRecordTypeNumber, HashMap<Integer, ACHFieldRule> achFieldRules) {

		String achRecordType;
		int expectedNumberOfFields;
		int permittedPreviousRecordTypeNumber[];
		int permittedNextRecordTypeNumber[];
		boolean isPossiblePaddingLine = false;
		boolean required = true;
		int expectedNumberOfCharacters = 94;

		HashMap<String, Consumer<ACHRecord>> achRecordRuleTests = new HashMap<String, Consumer<ACHRecord>>();

		//TODO: Map other tests in ACHValidationTestSuite to each ACHRecordRule.
		achRecordRuleTests.put("isExpectedRecordLength", (achRecord) -> isExpectedRecordLength(achRecord));


		switch (achPageTypeNumber.toString().concat(achRecordTypeNumber.toString())) {
			case "11":
				achRecordType = "file header";
				expectedNumberOfFields = 13;
				permittedPreviousRecordTypeNumber = new int[]{};
				permittedNextRecordTypeNumber = new int[]{5};
				break;
			case "15":
				achRecordType = "file header";
				expectedNumberOfFields = 13;
				permittedPreviousRecordTypeNumber = new int[]{};
				permittedNextRecordTypeNumber = new int[]{6};
				break;
			case "16":
				achRecordType = "file header";
				expectedNumberOfFields = 13;
				permittedPreviousRecordTypeNumber = new int[]{};
				permittedNextRecordTypeNumber = new int[]{6, 7, 8};
				break;
			case "17":
				achRecordType = "file header";
				expectedNumberOfFields = 13;
				permittedPreviousRecordTypeNumber = new int[]{};
				permittedNextRecordTypeNumber = new int[]{6, 8};
				required = false;
				break;
			case "18":
				achRecordType = "file header";
				expectedNumberOfFields = 13;
				permittedPreviousRecordTypeNumber = new int[]{};
				permittedNextRecordTypeNumber = new int[]{5, 9};
				break;
			case "19":
				//can also be a padding record
				achRecordType = "file header";
				expectedNumberOfFields = 13;
				permittedPreviousRecordTypeNumber = new int[]{};
				permittedNextRecordTypeNumber = new int[]{9};
				isPossiblePaddingLine = true;
				break;
			default:
				throw new IllegalStateException("Unexpected value while creating an ACHRecordRule got: " + achPageTypeNumber.toString().concat(achRecordTypeNumber.toString()));
		}

		return ACHRecordRule.createNewInstance(achPageTypeNumber, achRecordTypeNumber, achRecordType, expectedNumberOfFields, permittedPreviousRecordTypeNumber, permittedNextRecordTypeNumber, isPossiblePaddingLine, required, expectedNumberOfCharacters, achFieldRules, achRecordRuleTests);
	}

	private static final ACHFieldRule mapFieldRule(Integer achPageTypeNumber, Integer achRecordTypeNumber, Integer achFieldNumber) {
		Integer achFieldLength;
		Integer achFieldPosition;
		ACHDataTypeRule achDataTypeRule;
		HashMap<String, Consumer<ACHField>> achFieldRuleTests = new HashMap<String, Consumer<ACHField>>();

		//Add the ACHValidationTestSuite isExpectedDataType test to check that the field matches its correct regex.
		achFieldRuleTests.put("isExpectedDataType", (achField) -> isExpectedDataType(achField));

		//TODO: Add the correct ACHDataTypeRule to each ACHFieldRule.
		switch (achPageTypeNumber.toString().concat(achRecordTypeNumber.toString().concat(achFieldNumber.toString()))) {
			case "111":
				achFieldLength = 1;
				achFieldPosition = 1;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.SPECIFICVALUES, new String[]{"1"});
				break;
			case "112":
				achFieldLength = 2;
				achFieldPosition = 2;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.SPECIFICVALUES, new String[]{"01"});
				break;
			case "113":
				achFieldLength = 10;
				achFieldPosition = 4;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.NUMERIC, new String[]{});
				break;
			case "114":
				achFieldLength = 10;
				achFieldPosition = 14;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.NUMERIC, new String[]{});
				break;
			case "115":
				achFieldLength = 6;
				achFieldPosition = 24;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.YYMMDD, new String[]{});
				break;
			case "116":
				achFieldLength = 4;
				achFieldPosition = 30;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.HHMM, new String[]{});
				break;
			case "117":
				achFieldLength = 1;
				achFieldPosition = 34;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.CODES, new String[]{});
				break;
			case "118":
				achFieldLength = 3;
				achFieldPosition = 35;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.SPECIFICVALUES, new String[]{"094"});
				break;
			case "119":
				achFieldLength = 2;
				achFieldPosition = 38;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.SPECIFICVALUES, new String[]{"10"});
				break;
			case "1110":
				achFieldLength = 1;
				achFieldPosition = 40;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.SPECIFICVALUES, new String[]{"1"});
				break;
			case "1111":
				achFieldLength = 23;
				achFieldPosition = 41;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.ASCII, new String[]{});
				break;
			case "1112":
				achFieldLength = 23;
				achFieldPosition = 64;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.ASCII, new String[]{});
				break;
			case "1113":
				achFieldLength = 8;
				achFieldPosition = 87;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.CODES, new String[]{});
				break;
			case "151":
				achFieldLength = 1;
				achFieldPosition = 1;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.SPECIFICVALUES, new String[]{"5"});
				break;
			case "152":
				achFieldLength = 3;
				achFieldPosition = 2;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.SPECIFICVALUES, new String[]{"100", "220", "225"});
				break;
			case "153":
				achFieldLength = 16;
				achFieldPosition = 5;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.ASCII, new String[]{});
				break;
			case "154":
				achFieldLength = 20;
				achFieldPosition = 21;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.ASCII, new String[]{});
				break;
			case "155":
				achFieldLength = 10;
				achFieldPosition = 41;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.NUMERIC, new String[]{});
				break;
			case "156":
				achFieldLength = 3;
				achFieldPosition = 51;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.SPECIFICVALUES, new String[]{"PPD","CCD","CTX","TEL","WEB"});
				break;
			case "157":
				achFieldLength = 10;
				achFieldPosition = 54;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.ASCII, new String[]{});
				break;
			case "158":
				achFieldLength = 6;
				achFieldPosition = 64;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.ASCII, new String[]{});
				break;
			case "159":
				achFieldLength = 6;
				achFieldPosition = 70;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.YYMMDD, new String[]{});
				break;
			case "1510":
				achFieldLength = 3;
				achFieldPosition = 76;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.SPECIFICVALUES, new String[]{"   "});
				break;
			case "1511":
				achFieldLength = 1;
				achFieldPosition = 79;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.SPECIFICVALUES, new String[]{"1"});
				break;
			case "1512":
				achFieldLength = 8;
				achFieldPosition = 80;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.NUMERIC, new String[]{});
				break;
			case "1513":
				achFieldLength = 7;
				achFieldPosition = 88;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.NUMERIC, new String[]{});
				break;
			case "161":
				achFieldLength = 1;
				achFieldPosition = 1;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.SPECIFICVALUES, new String[]{"6"});
				break;
			case "162":
				achFieldLength = 2;
				achFieldPosition = 2;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.SPECIFICVALUES, new String[]{"22","23","24","27","28","29","32","33","34","37","38","39"});
				break;
			case "163":
				achFieldLength = 8;
				achFieldPosition = 4;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.NUMERIC, new String[]{});
				break;
			case "164":
				achFieldLength = 1;
				achFieldPosition = 12;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.NUMERIC, new String[]{});
				break;
			case "165":
				achFieldLength = 17;
				achFieldPosition = 13;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.ASCII, new String[]{});
				break;
			case "166":
				achFieldLength = 10;
				achFieldPosition = 30;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.NUMERIC, new String[]{});
				break;
			case "167":
				achFieldLength = 15;
				achFieldPosition = 40;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.ASCII, new String[]{});
				break;
			case "168":
				achFieldLength = 22;
				achFieldPosition = 55;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.ASCII, new String[]{});
				break;
			case "169":
				achFieldLength = 2;
				achFieldPosition = 77;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.SPECIFICVALUES, new String[]{"R","S"});
				break;
			case "1610":
				achFieldLength = 1;
				achFieldPosition = 79;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.SPECIFICVALUES, new String[]{"0","1"});
				break;
			case "1611":
				achFieldLength = 15;
				achFieldPosition = 80;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.NUMERIC, new String[]{});
				break;
			case "171":
				achFieldLength = 1;
				achFieldPosition = 1;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.SPECIFICVALUES, new String[]{});
				break;
			case "172":
				achFieldLength = 2;
				achFieldPosition = 2;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.SPECIFICVALUES, new String[]{});
				break;
			case "173":
				achFieldLength = 8;
				achFieldPosition = 4;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.SPECIFICVALUES, new String[]{});
				break;
			case "174":
				achFieldLength = 1;
				achFieldPosition = 12;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.SPECIFICVALUES, new String[]{});
				break;
			case "175":
				achFieldLength = 17;
				achFieldPosition = 13;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.SPECIFICVALUES, new String[]{});
				break;
			case "176":
				achFieldLength = 10;
				achFieldPosition = 30;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.SPECIFICVALUES, new String[]{});
				break;
			case "177":
				achFieldLength = 15;
				achFieldPosition = 40;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.SPECIFICVALUES, new String[]{});
				break;
			case "178":
				achFieldLength = 22;
				achFieldPosition = 55;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.SPECIFICVALUES, new String[]{});
				break;
			case "179":
				achFieldLength = 2;
				achFieldPosition = 77;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.SPECIFICVALUES, new String[]{});
				break;
			case "1710":
				achFieldLength = 1;
				achFieldPosition = 79;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.SPECIFICVALUES, new String[]{});
				break;
			case "1711":
				achFieldLength = 15;
				achFieldPosition = 80;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.SPECIFICVALUES, new String[]{});
				break;
			case "181":
				achFieldLength = 1;
				achFieldPosition = 1;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.SPECIFICVALUES, new String[]{"8"});
				break;
			case "182":
				achFieldLength = 3;
				achFieldPosition = 2;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.SPECIFICVALUES, new String[]{"200","220","225"});
				break;
			case "183":
				achFieldLength = 6;
				achFieldPosition = 5;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.NUMERIC, new String[]{});
				break;
			case "184":
				achFieldLength = 10;
				achFieldPosition = 11;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.NUMERIC, new String[]{});
				break;
			case "185":
				achFieldLength = 12;
				achFieldPosition = 21;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.NUMERIC, new String[]{});
				break;
			case "186":
				achFieldLength = 12;
				achFieldPosition = 33;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.NUMERIC, new String[]{});
				break;
			case "187":
				achFieldLength = 10;
				achFieldPosition = 45;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.NUMERIC, new String[]{});
				break;
			case "188":
				achFieldLength = 19;
				achFieldPosition = 55;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.SPECIFICVALUES, new String[]{"                   "});
				break;
			case "189":
				achFieldLength = 6;
				achFieldPosition = 74;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.SPECIFICVALUES, new String[]{"      "});
				break;
			case "1810":
				achFieldLength = 8;
				achFieldPosition = 80;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.NUMERIC, new String[]{});
				break;
			case "1811":
				achFieldLength = 7;
				achFieldPosition = 88;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.NUMERIC, new String[]{});
				break;
			case "191":
				achFieldLength = 1;
				achFieldPosition = 1;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.SPECIFICVALUES, new String[]{"9"});
				break;
			case "192":
				achFieldLength = 6;
				achFieldPosition = 2;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.NUMERIC, new String[]{});
				break;
			case "193":
				achFieldLength = 6;
				achFieldPosition = 8;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.NUMERIC, new String[]{});
				break;
			case "194":
				achFieldLength = 8;
				achFieldPosition = 14;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.NUMERIC, new String[]{});
				break;
			case "195":
				achFieldLength = 10;
				achFieldPosition = 22;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.NUMERIC, new String[]{});
				break;
			case "196":
				achFieldLength = 12;
				achFieldPosition = 32;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.NUMERIC, new String[]{});
				break;
			case "197":
				achFieldLength = 12;
				achFieldPosition = 44;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.NUMERIC, new String[]{});
				break;
			case "198":
				achFieldLength = 39;
				achFieldPosition = 56;
				achDataTypeRule = ACHDataTypeRule.createNewInstance(DataType.SPECIFICVALUES, new String[]{"                                       "});
				break;
			default:
				throw new IllegalStateException("Unexpected value while creating an ACHFieldRule got: " + achPageTypeNumber.toString().concat(achRecordTypeNumber.toString().concat(achFieldNumber.toString())));
		}

		return ACHFieldRule.createNewInstance(achPageTypeNumber, achRecordTypeNumber, achFieldNumber, achFieldLength, achFieldPosition, achDataTypeRule, achFieldRuleTests);
	}

}
