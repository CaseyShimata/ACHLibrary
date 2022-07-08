package com.loanpro.achlibrary.model;

import com.loanpro.achlibrary.dictionary.ACHRuleDictionary;
import com.loanpro.achlibrary.rule.ACHPageRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ACHPage {
	private int achPageNumber;
	private Integer achPageTypeNumber;
	private String achRawPage;
	private ArrayList<ACHRecord> achRecords = new ArrayList<ACHRecord>();
	private ACHPageRule achPageRule;
	private HashMap<String, ACHValidationTest> achValidationTests = new HashMap<String, ACHValidationTest>();
	Logger logger = LoggerFactory.getLogger(ACHRecord.class);

	public ACHPage(int achPageNumber, Character achPageTypeNumber, Reader reader) {
		this.achPageNumber = achPageNumber;
		this.achPageTypeNumber = Character.getNumericValue(achPageTypeNumber);
		this.achPageRule = ACHRuleDictionary.achRules.get(Character.getNumericValue(achPageTypeNumber));
		int r;
		int achRecordNumber = 1;
		int charCountInRecord = 1;
		int lineBreakCharTracker = 0;
		String achRawPage = "";
		ArrayList<Character> achRawRecord = new ArrayList<Character>();
		Character achRecordTypeNumber = '1';

		try {
			while ((r = reader.read()) != -1) {
				Character ch = (char) r;
				achRawPage += ch;

				if (charCountInRecord == 1) {
					achRecordTypeNumber = ch;

				} else if (ch == '\r') {
					continue;
				} else if (ch == '\n') {
					this.createACHRecords(achPageNumber,
							achPageTypeNumber,
							achRecordNumber,
							achRecordTypeNumber,
							achRawRecord);
//					TODO: Check if new pointer fully garbage collects the orphaned object.
//					Point to a new empty array list instead of clearing and resizing the current arraylist
					achRawRecord = new ArrayList<Character>();
					charCountInRecord = 1;
					achRecordNumber += 1;
//					Do not append more the record is now complete. Continue to make the next record.
					continue;

				}
				achRawRecord.add(ch);
				charCountInRecord += 1;

			}
			this.createACHRecords(achPageNumber,
					achPageTypeNumber,
					achRecordNumber,
					achRecordTypeNumber,
					achRawRecord);
			reader.close();
			this.achRawPage = achRawPage;
			this.runACHPageRuleACHValidationTests();

		} catch (IOException e) {
			logger.error("Error while trying to read a ACH buffer: " + e);
		}
	}

	private void createACHRecords(int achPageNumber, Character achPageTypeNumber, int achRecordNumber, Character achRecordTypeNumber, ArrayList<Character> achRawRecord) {
//		TODO: Check character type and convert to correct type.
		Integer pgTypNbr = Character.getNumericValue(achPageTypeNumber);
		Integer rcdTypNbr = Character.getNumericValue(achRecordTypeNumber);

		if (!achRawRecord.isEmpty() && achRawRecord.get(1) == '9' && achRawRecord.get(achRawRecord.size() - 1) == '9') {
			rcdTypNbr = 10;
		}

		ACHRecord achRecord = new ACHRecord(
				achPageNumber,
				pgTypNbr,
				achRecordNumber,
				rcdTypNbr,
				achRawRecord,
				ACHRuleDictionary.getAchRecordRule(pgTypNbr, rcdTypNbr));

		//Map all the fields now that the record has all of its characters
		//These can be called independently if an ACHRecord has already been instantiated with its achRawRecord property.
		achRecord.setAchFieldsFromRawRecord();
		achRecord.runACHRecordRuleACHValidationTests();

		this.appendToAchRecords(achRecord);
	}

	public int getAchPageNumber() {
		return achPageNumber;
	}

	public void setAchPageNumber(int achPageNumber) {
		this.achPageNumber = achPageNumber;
	}

	public Integer getAchPageTypeNumber() {
		return achPageTypeNumber;
	}

	public void setAchPageTypeNumber(Integer achPageTypeNumber) {
		this.achPageTypeNumber = achPageTypeNumber;
	}

	public String getAchRawPage() {
		return achRawPage;
	}

	public void setAchRawPage(String achRawPage) {
		this.achRawPage = achRawPage;
	}

	public ACHRecord getOneAchRecords(int index) {
		return this.achRecords.get(index);
	}

	public ArrayList<ACHRecord> getAchRecords() {
		return achRecords;
	}

	public void setAchRecords(ArrayList<ACHRecord> achRecords) {
		this.achRecords = achRecords;
	}

	public void appendToAchRecords(ACHRecord achRecord) {
		this.achRecords.add(achRecord);
	}

	public void insertToAchRecords(ACHRecord achRecord, int index) {
		this.achRecords.add(index, achRecord);
	}

	public ACHPageRule getAchPageRule() {
		return achPageRule;
	}

	public void setAchPageRule(ACHPageRule achPageRule) {
		this.achPageRule = achPageRule;
	}

	public HashMap<String, ACHValidationTest> getAchValidationTests() {
		return achValidationTests;
	}

	public void setAchValidationTests(HashMap<String, ACHValidationTest> achValidationTests) {
		this.achValidationTests = achValidationTests;
	}

	public void addToAchValidationTests(String achValidationTestName, ACHValidationTest achValidationTest) {
		this.achValidationTests.put(achValidationTestName, achValidationTest);
	}

	public void runACHPageRuleACHValidationTests() {
		for (Map.Entry<String, Consumer<ACHPage>> test : this.getAchPageRule().getAchPageRuleTests().entrySet()) {
//            Java Consumer is passed by reference since it only allows void return.
			Consumer<ACHPage> achValidationTest = test.getValue();
			achValidationTest.accept(this);
		}
	}

}