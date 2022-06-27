package com.loanpro.achlibrary.model;

public class FileRules {

    public String isRecordCountModuloTen;

    /**
     * Returns An array of allowed next record types for a given record type.
     * @param currentRecordType
     * @return
     */
    public int[] allowedNextRecordTypes(int currentRecordType) {
        int[] result;
        switch (currentRecordType) {
            case 1:
                result = new int[]{5};
                break;
            case 5:
                result = new int[]{6};
                break;
            case 6:
                result = new int[]{6,7,8};
                break;
            case 7:
                result = new int[]{6,8};
                break;
            case 8:
                result = new int[]{5,9};
                break;
            case 9:
            default:
                result = new int[]{};
                break;
        }
        return result;
    }
}
