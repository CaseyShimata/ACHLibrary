package com.loanpro.achlibrary.model;

import com.loanpro.achlibrary.dictionary.ACHRuleDictionary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class ACHRecord {
    private int achPageNumber;
    private int achRecordNumber;
    private Character achRecordTypeNumber;
    private Character nextRecordTypeNumber;
    private ArrayList<Character> achRawRecord = new ArrayList<Character>();
    private ArrayList<ACHField> achFields = new ArrayList<ACHField>();
    private ACHRecordRule achRecordRule;
    private ArrayList<ACHValidationTest> achValidationTests;
    Logger logger = LoggerFactory.getLogger(ACHRecord.class);

    //todo: step through and determine where the page number and use this constructor instead
    public ACHRecord(int achPageNumber, int achRecordNumber, Character achRecordTypeNumber) {
        this.achPageNumber = achPageNumber;
        this.achRecordNumber = achRecordNumber;
        this.achRecordTypeNumber = achRecordTypeNumber;
    }

    public ACHRecord(Character achRecordTypeNumber, int achRecordNumber) {
        this.achRecordTypeNumber = achRecordTypeNumber;
        this.achRecordNumber = achRecordNumber;
    }

    public int getAchPageNumber() {
        return achPageNumber;
    }

    public void setAchPageNumber(int achPageNumber) {
        this.achPageNumber = achPageNumber;
    }

    public int getAchRecordNumber() {
        return achRecordNumber;
    }

    public void setAchRecordNumber(int achRecordNumber) {
        this.achRecordNumber = achRecordNumber;
    }

    public Character getAchRecordTypeNumber() {
        return achRecordTypeNumber;
    }

    public void setAchRecordTypeNumber(Character achRecordTypeNumber) {
        this.achRecordTypeNumber = achRecordTypeNumber;
    }

    public Character getNextRecordTypeNumber() {
        return nextRecordTypeNumber;
    }

    public void setNextRecordTypeNumber(Character nextRecordTypeNumber) {
        this.nextRecordTypeNumber = nextRecordTypeNumber;
    }

    public ArrayList<Character> getAchRawRecord() {
        return achRawRecord;
    }

    public void setAchRawRecord(ArrayList<Character> achRawRecord) {
        this.achRawRecord = achRawRecord;
    }

    public void appendToACHRawRecord(char ch) {
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
     *
     */
    public void setACHFields(){
        HashMap<Integer, HashMap<Integer, ACHFieldRule>> achFieldRules = ACHRuleDictionary.getAchFieldRules();

        //make a new array by splitting the raw record from the fields start to the fields end

        //todo: get number of fields that are in this recordType
        int numberOfRecordsFields = achFieldRules.get(Character.getNumericValue(this.getAchRecordTypeNumber())).size();

        ArrayList<ACHField> achFieldsTemp = new ArrayList<ACHField>(Collections.nCopies(numberOfRecordsFields, null));

        for (Map.Entry<Integer, ACHFieldRule> index :
                achFieldRules.get(Character.getNumericValue(this.getAchRecordTypeNumber())).entrySet()) {

            ACHFieldRule achFieldRule = index.getValue();
            Integer achCharacterPosition1 = achFieldRule.getObjectTakesUpPositionInRecord() - 1;
            Integer achCharacterPosition2 = achCharacterPosition1 + achFieldRule.getObjectsCharacterLengthIs();


//            todo: Use this function to map the fields into strings to put into text fields for editing of the ACH page.
//            String str = this.getRawRecord().stream()
//                    .map(e->e.toString())
//                    .collect(Collectors.joining());

            //Check if a rawRecord is too short. If it is too long it will have null values in the achField class.
            try {
                ArrayList<Character> newACHFieldCurrentValue = new ArrayList<Character>(this.getAchRawRecord()
                        .subList(achCharacterPosition1, achCharacterPosition2));

                ACHField achFieldTemp = new ACHField(newACHFieldCurrentValue, achFieldRule.getObjectTakesUpPositionInRecord());

                //Insert the field into an array at the index it belongs in the raw data.
                achFieldsTemp.add(index.getValue().getAchFieldNumber(),achFieldTemp);
            } catch (IndexOutOfBoundsException e){
                //todo: add to the validationError object for this record.
                logger.info("add to the validationError object for this record." );
                continue;
            }
        }
        this.achFields = achFieldsTemp;
    }

    public void appendToACHFields(ACHField achField){
        this.achFields.add(achField);
    }

    public void insertToACHFields(ACHField achField, int index){
        this.achFields.add(index, achField);

    }

    public ACHRecordRule getAchRecordRule() {
        return achRecordRule;
    }

    public void setAchRecordRule(ACHRecordRule achRecordRule) {
        this.achRecordRule = achRecordRule;
    }

    public ArrayList<ACHValidationTest> getAchValidationTests() {
        return achValidationTests;
    }

    public void setAchValidationTests(ArrayList<ACHValidationTest> achValidationTests) {
        this.achValidationTests = achValidationTests;
    }
}