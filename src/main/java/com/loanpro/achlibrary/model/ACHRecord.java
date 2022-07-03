package com.loanpro.achlibrary.model;

import com.loanpro.achlibrary.dictionary.ACHRuleDictionary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Consumer;

public class ACHRecord {
	private int achPageNumber;
	private Integer achPageTypeNumber;
	private int achRecordNumber;
	private Integer achRecordTypeNumber;
	private ArrayList<Character> achRawRecord = new ArrayList<Character>();
	private ArrayList<ACHField> achFields = new ArrayList<ACHField>();
	private ACHRecordRule achRecordRule;
	private HashMap<String, ACHValidationTest> achValidationTests = new HashMap<String, ACHValidationTest>();
	Logger logger = LoggerFactory.getLogger(ACHRecord.class);

	public ACHRecord(int achPageNumber,
	                 Integer achPageTypeNumber,
	                 int achRecordNumber,
	                 Integer achRecordTypeNumber,
	                 ArrayList<Character> achRawRecord,
	                 ACHRecordRule achRecordRule) {
		this.achPageNumber = achPageNumber;
		this.achPageTypeNumber = achPageTypeNumber;
		this.achRecordNumber = achRecordNumber;
		this.achRecordTypeNumber = achRecordTypeNumber;
		this.achRawRecord = achRawRecord;
		this.achRecordRule = achRecordRule;
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

	public int getAchRecordNumber() {
		return achRecordNumber;
	}

	public void setAchRecordNumber(int achRecordNumber) {
		this.achRecordNumber = achRecordNumber;
	}

	public Integer getAchRecordTypeNumber() {
		return achRecordTypeNumber;
	}

	public void setAchRecordTypeNumber(Integer achRecordTypeNumber) {
		this.achRecordTypeNumber = achRecordTypeNumber;
	}

	public ArrayList<Character> getAchRawRecord() {
		return achRawRecord;
	}

	public void setAchRawRecord(ArrayList<Character> achRawRecord) {
		this.achRawRecord = achRawRecord;
	}

	public void appendToACHRawRecord(Character ch) {
		this.achRawRecord.add(ch);
	}


	public ArrayList<ACHField> getAchFields() {
		return achFields;
	}

	public void setAchFields(ArrayList<ACHField> achFields) {
		this.achFields = achFields;
	}

	/**
	 * Map rawRecord to its respective fields once raw record is fully populated.
	 */
	public void setAchFieldsFromRawRecord() {

		ACHRecordRule achRecordRule = ACHRuleDictionary.getAchRecordRule(
				this.achPageTypeNumber,
				this.achRecordTypeNumber);

		//Get the number of expected fields this record type should have.
		int numberOfRecordsFields = achRecordRule.getExpectedNumberOfFields();

		//Create a null placeholder for each of this ACHRecord's ACHFields because HashMap does not iterate in order.
		ArrayList<ACHField> achFields = new ArrayList<ACHField>(Collections.nCopies(numberOfRecordsFields, null));

		//Iterate each ACHFieldRule for this ACHFieldRecord achFieldRecordType, use the rules to split the raw record
		//into separate subLists and set a new ACHField in place of the null.
		for (Map.Entry<Integer, ACHFieldRule> index : achRecordRule.getAchFieldRules().entrySet()) {

			ACHFieldRule achFieldRule = index.getValue();
			Integer achCharacterPosition1 = achFieldRule.getExpectedPositionInRecord() - 1;
			Integer achCharacterPosition2 = achCharacterPosition1 + achFieldRule.getExpectedCharacterLength();

			//Avoid crashing on out of bounds if a rawRecord is too short.
			try {
				ArrayList<Character> newACHFieldCurrentValue = new ArrayList<Character>(this.getAchRawRecord()
						.subList(achCharacterPosition1, achCharacterPosition2));

				//TODO: Add the ACHFieldRule to this Field using the dictionary
				ACHField achField = new ACHField(this.achPageNumber,
						this.achPageTypeNumber,
						this.achRecordNumber,
						this.achRecordTypeNumber,
						index.getValue().getAchFieldNumber(),
						newACHFieldCurrentValue,
						achFieldRule.getExpectedPositionInRecord(),
						ACHRuleDictionary.getAchFieldRule(
								this.achPageTypeNumber,
								this.achRecordTypeNumber,
								index.getValue().getAchFieldNumber()));

				achField.runAchFieldValidationTests();
				this.achFields.add(index.getValue().getAchFieldNumber() - 1, achField);
			} catch (IndexOutOfBoundsException e) {
				logger.warn("This record is too short to map all the fields: " + e);
				continue;
			}
		}
	}

	public void appendToACHFields(ACHField achField) {
		this.achFields.add(achField);
	}

	public void insertToACHFields(ACHField achField, int index) {
		this.achFields.add(index, achField);

	}

	public ACHRecordRule getAchRecordRule() {
		return achRecordRule;
	}

	public void setAchRecordRule(ACHRecordRule achRecordRule) {
		this.achRecordRule = achRecordRule;
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

	/**
	 * Run the ACHRecord's ACHRecordRule's tests adding the resulting ACHValidationTests to this ACHRecord
	 */
	public void runACHRecordRuleACHValidationTests() {
		for (Map.Entry<String, Consumer<ACHRecord>> test : this.achRecordRule.getAchRecordRuleTests().entrySet()) {
//            Java Consumer is passed by reference since it only allows void return.
			Consumer<ACHRecord> achValidationTest = test.getValue();
			achValidationTest.accept(this);
		}
	}
}