package com.loanpro.achlibrary.model;

import java.util.ArrayList;

public class ACHFile {
    private String rawFile;
    private ArrayList<ACHRecord> achRecords = new ArrayList<ACHRecord>();

    public ACHFile() {

    }

    public String getRawFile() {
        return rawFile;
    }

    public void setRawFile(String rawFile) {
        this.rawFile = rawFile;
    }

    public ArrayList<ACHRecord> getRecords() {
        return achRecords;
    }

    public ACHRecord getRecordAtIndex(int index){
        return this.achRecords.get(index);
    }

    public void setRecords(ArrayList<ACHRecord> achRecords) {
        this.achRecords = achRecords;
    }

    public void appendToRecords(ACHRecord achRecord){
        this.achRecords.add(achRecord);
    }
}