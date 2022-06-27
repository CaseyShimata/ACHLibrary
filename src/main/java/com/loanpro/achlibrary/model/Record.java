package com.loanpro.achlibrary.model;

import java.util.ArrayList;
import java.util.List;

public class Record {
    private int recordTypeNumber;
    private int nextRecordType;
    private String recordType;
    private int lineNumber;
    private ArrayList<Character> rawRecord = new ArrayList<Character>();
    private ArrayList<Field> fields = new ArrayList<Field>();

    public Record(int recordTypeNumber, int lineNumber) {
        this.recordTypeNumber = recordTypeNumber;
        this.lineNumber = lineNumber;
    }

    public int getRecordTypeNumber() {
        return recordTypeNumber;
    }

    public void setRecordTypeNumber(int recordTypeNumber) {
        this.recordTypeNumber = recordTypeNumber;
    }

    public int getNextRecordType() {
        return nextRecordType;
    }

    public void setNextRecordType(int nextRecordType) {
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

    public ArrayList<Field> getFields() {
        return fields;
    }

    public void setFields(ArrayList<Field> fields) {
        this.fields = fields;
    }
}