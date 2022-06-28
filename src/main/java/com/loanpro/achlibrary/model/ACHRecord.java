package com.loanpro.achlibrary.model;

import java.util.ArrayList;

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

    public void rawRecordToFields(Character recordTypeNumber, ArrayList<Character> rawRecord){
         switch (recordTypeNumber){
             case 1:
                 break;
             case 5:
                 break;
             case 6:
                 break;
             case 7:
                 break;
             case 8:
                 break;
             case 9:
             default:
                 break;
         }
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
}