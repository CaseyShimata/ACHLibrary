package com.loanpro.achlibrary.model;

import com.loanpro.achlibrary.dictionary.ACHRuleDictionary;

import java.util.*;

public class ACHRecord {
    private Character recordTypeNumber;
    private Character nextRecordType;
    private String recordType;
    private int lineNumber;
    private ArrayList<Character> rawRecord = new ArrayList<Character>();
    private ArrayList<ACHField> achFields = new ArrayList<ACHField>();

    public ACHRecord(Character recordTypeNumber, int lineNumber) {
        this.recordTypeNumber = recordTypeNumber;
        this.lineNumber = lineNumber;
    }

    /**
     * Map rawRecord to its respective fields once raw record is fully populated.
     *
     *
     */
    public void setACHFields(){
        HashMap<Integer, HashMap<Integer, ACHFieldRule>> achFieldRules = ACHRuleDictionary.getAchFieldRules();

        //make a new array by splitting the raw record from the fields start to the fields end

        //todo: get number of fields that are in this recordType
        int numberOfRecordsFields = achFieldRules.get(Character.getNumericValue(this.getRecordTypeNumber())).size();
        
        ArrayList<ACHField> achFieldsTemp = new ArrayList<ACHField>(Collections.nCopies(numberOfRecordsFields, null));

        for (Map.Entry<Integer, ACHFieldRule> index :
                achFieldRules.get(Character.getNumericValue(this.getRecordTypeNumber())).entrySet()) {

            ACHFieldRule achFieldRule = index.getValue();
            Integer achCharacterPosition1 = achFieldRule.getObjectTakesUpPositionInRecord() - 1;
            Integer achCharacterPosition2 = achCharacterPosition1 + achFieldRule.getObjectsCharacterLengthIs();


//            todo: Use this function to map the fields into strings to put into text fields for editing of the ACH file.
//            String str = this.getRawRecord().stream()
//                    .map(e->e.toString())
//                    .collect(Collectors.joining());

            ArrayList<Character> newACHFieldCurrentValue = new ArrayList<Character>(achCharacterPosition2 - achCharacterPosition1);
            newACHFieldCurrentValue = new ArrayList<Character>(this.getRawRecord()
                    .subList(achCharacterPosition1.intValue(), achCharacterPosition2.intValue()));


            ACHField achField = new ACHField(newACHFieldCurrentValue, achFieldRule.getObjectTakesUpPositionInRecord());

            //Insert the field into an array at the index it belongs in the raw data.
            achFieldsTemp.add(index.getValue().getFieldNumber(),achField);
        }
        this.achFields = achFieldsTemp;
    }

    public Character getRecordTypeNumber() {
        return recordTypeNumber;
    }

    public void setRecordTypeNumber(Character recordTypeNumber) {
        this.recordTypeNumber = recordTypeNumber;
    }

    public Character getNextRecordType() {
        return nextRecordType;
    }

    public void setNextRecordType(Character nextRecordType) {
        this.nextRecordType = nextRecordType;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public ArrayList<Character> getRawRecord() {
        return rawRecord;
    }

    public void setRawRecord(ArrayList<Character> rawRecord) {
        this.rawRecord = rawRecord;
    }

    public void appendToRawRecord(char ch) {
        this.rawRecord.add(ch);
    }

    public ArrayList<ACHField> getFields() {
        return achFields;
    }

    public void setACHFields(ArrayList<ACHField> achFields) {
        this.achFields = achFields;
    }

    public void appendToACHFields(ACHField achField){
        this.achFields.add(achField);
    }

    /**
     * inserts achField at a given index and shifts the fields indexes.
     *
     * @param achField
     * @param index
     */
    public void insertToACHFields(ACHField achField, int index){
        this.achFields.add(index, achField);

    }
}