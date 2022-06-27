package com.loanpro.achlibrary.model;

import java.util.ArrayList;

public class File {
    private String rawFile;
    private ArrayList<Record> records = new ArrayList<Record>();

    public File() {

    }

    public String getRawFile() {
        return rawFile;
    }

    public void setRawFile(String rawFile) {
        this.rawFile = rawFile;
    }

    public ArrayList<Record> getRecords() {
        return records;
    }

    public Record getRecordAtIndex(int index){
        return this.records.get(index);
    }

    public void setRecords(ArrayList<Record> records) {
        this.records = records;
    }

    public void appendToRecords(Record record){
        this.records.add(record);
    }
}