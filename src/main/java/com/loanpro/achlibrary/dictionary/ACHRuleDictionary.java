package com.loanpro.achlibrary.dictionary;

import com.loanpro.achlibrary.model.ACHFieldRule;

import java.util.HashMap;
import java.util.Map;

public class ACHRuleDictionary {

    //exa use achFieldRules.get(1).get(2)
    private static final HashMap<Integer, HashMap<Integer, ACHFieldRule>> achFieldRules = buildFieldRules();

    //Create a nested key value pair referencing the record type and the field rules it holds
    private static final HashMap<Integer, HashMap<Integer, ACHFieldRule>> buildFieldRules() {

        HashMap<Integer, HashMap<Integer, ACHFieldRule>> achFieldRules = new HashMap<Integer, HashMap<Integer, ACHFieldRule>>();

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
            Integer recordNumber = entry.getKey();
            Integer fieldNumber = entry.getValue();

            if (!achFieldRules.containsKey(recordNumber)) {
                achFieldRules.put(recordNumber, new HashMap<Integer, ACHFieldRule>());
            }


            int i = 0;
            while (i < fieldNumber) {

                HashMap<String, Integer> fieldProperties = mapFieldRules(recordNumber, i+1);

                achFieldRules.get(recordNumber).put(i+1, ACHFieldRule.createNewInstance(0000, recordNumber, i+1, fieldProperties.get("objectsCharacterLengthIs"), fieldProperties.get("objectTakesUpPositionInRecord")));
                i += 1;
            }
        }
        return achFieldRules;
    }

    private static HashMap<String, Integer> mapFieldRules(Integer recordNumber, Integer fieldNumber) {

        HashMap<String, Integer> fieldRules = new HashMap<String, Integer>();

        Integer fieldLength;
        Integer fieldPosition;

        //todo: Combine cases for fields that have the same rules. IDE detects duplicate values.
        //todo: Possibly use this switch to map the fields more complex rules as well.
        switch(recordNumber.toString().concat(fieldNumber.toString())){
            case "11":
                fieldLength = 1;
                fieldPosition = 1;
                break;
            case "12":
                fieldLength = 2;
                fieldPosition = 2;
                break;
            case "13":
                fieldLength = 10;
                fieldPosition = 4;
                break;
            case "14":
                fieldLength = 10;
                fieldPosition = 14;
                break;
            case "15":
                fieldLength = 6;
                fieldPosition = 24;
                break;
            case "16":
                fieldLength = 4;
                fieldPosition = 30;
                break;
            case "17":
                fieldLength = 1;
                fieldPosition = 34;
                break;
            case "18":
                fieldLength = 3;
                fieldPosition = 35;
                break;
            case "19":
                fieldLength = 2;
                fieldPosition = 38;
                break;
            case "110":
                fieldLength = 1;
                fieldPosition = 40;
                break;
            case "111":
                fieldLength = 23;
                fieldPosition = 41;
                break;
            case "112":
                fieldLength = 23;
                fieldPosition = 64;
                break;
            case "113":
                fieldLength = 8;
                fieldPosition = 87;
                break;
            case "51":
                fieldLength = 1;
                fieldPosition = 1;
                break;
            case "52":
                fieldLength = 3;
                fieldPosition = 2;
                break;
            case "53":
                fieldLength = 16;
                fieldPosition = 5;
                break;
            case "54":
                fieldLength = 20;
                fieldPosition = 21;
                break;
            case "55":
                fieldLength = 10;
                fieldPosition = 41;
                break;
            case "56":
                fieldLength = 3;
                fieldPosition = 51;
                break;
            case "57":
                fieldLength = 10;
                fieldPosition = 54;
                break;
            case "58":
                fieldLength = 6;
                fieldPosition = 64;
                break;
            case "59":
                fieldLength = 6;
                fieldPosition = 70;
                break;
            case "510":
                fieldLength = 3;
                fieldPosition = 76;
                break;
            case "511":
                fieldLength = 1;
                fieldPosition = 79;
                break;
            case "512":
                fieldLength = 8;
                fieldPosition = 80;
                break;
            case "513":
                fieldLength = 7;
                fieldPosition = 88;
                break;
            case "61":
                fieldLength = 1;
                fieldPosition = 1;
                break;
            case "62":
                fieldLength = 2;
                fieldPosition = 2;
                break;
            case "63":
                fieldLength = 8;
                fieldPosition = 4;
                break;
            case "64":
                fieldLength = 1;
                fieldPosition = 12;
                break;
            case "65":
                fieldLength = 17;
                fieldPosition = 13;
                break;
            case "66":
                fieldLength = 10;
                fieldPosition = 30;
                break;
            case "67":
                fieldLength = 15;
                fieldPosition = 40;
                break;
            case "68":
                fieldLength = 22;
                fieldPosition = 55;
                break;
            case "69":
                fieldLength = 2;
                fieldPosition = 77;
                break;
            case "610":
                fieldLength = 1;
                fieldPosition = 79;
                break;
            case "611":
                fieldLength = 15;
                fieldPosition = 80;
                break;
            case "71":
                fieldLength = 1;
                fieldPosition = 1;
                break;
            case "72":
                fieldLength = 2;
                fieldPosition = 2;
                break;
            case "73":
                fieldLength = 8;
                fieldPosition = 4;
                break;
            case "74":
                fieldLength = 1;
                fieldPosition = 12;
                break;
            case "75":
                fieldLength = 17;
                fieldPosition = 13;
                break;
            case "76":
                fieldLength = 10;
                fieldPosition = 30;
                break;
            case "77":
                fieldLength = 15;
                fieldPosition = 40;
                break;
            case "78":
                fieldLength = 22;
                fieldPosition = 55;
                break;
            case "79":
                fieldLength = 2;
                fieldPosition = 77;
                break;
            case "710":
                fieldLength = 1;
                fieldPosition = 79;
                break;
            case "711":
                fieldLength = 15;
                fieldPosition = 80;
                break;
            case "81":
                fieldLength = 1;
                fieldPosition = 1;
                break;
            case "82":
                fieldLength = 3;
                fieldPosition = 2;
                break;
            case "83":
                fieldLength = 6;
                fieldPosition = 5;
                break;
            case "84":
                fieldLength = 10;
                fieldPosition = 11;
                break;
            case "85":
                fieldLength = 12;
                fieldPosition = 21;
                break;
            case "86":
                fieldLength = 12;
                fieldPosition = 33;
                break;
            case "87":
                fieldLength = 10;
                fieldPosition = 45;
                break;
            case "88":
                fieldLength = 19;
                fieldPosition = 55;
                break;
            case "89":
                fieldLength = 6;
                fieldPosition = 74;
                break;
            case "810":
                fieldLength = 8;
                fieldPosition = 80;
                break;
            case "811":
                fieldLength = 7;
                fieldPosition = 88;
                break;
            case "91":
                fieldLength = 1;
                fieldPosition = 1;
                break;
            case "92":
                fieldLength = 6;
                fieldPosition = 2;
                break;
            case "93":
                fieldLength = 6;
                fieldPosition = 8;
                break;
            case "94":
                fieldLength = 8;
                fieldPosition = 14;
                break;
            case "95":
                fieldLength = 10;
                fieldPosition = 22;
                break;
            case "96":
                fieldLength = 12;
                fieldPosition = 32;
                break;
            case "97":
                fieldLength = 12;
                fieldPosition = 44;
                break;
            case "98":
                fieldLength = 39;
                fieldPosition = 56;
                break;
            default:
                fieldLength = 22;
                fieldPosition = 120;
                break;
        }

        fieldRules.put("objectsCharacterLengthIs", fieldLength);
        fieldRules.put("objectTakesUpPositionInRecord", fieldPosition);

        return fieldRules;
    }

    /**
     * Set the field length and positions for every possible record and field.
     *
     * @return
     */
    public static HashMap<Integer, HashMap<Integer, ACHFieldRule>> getAchFieldRules() {
        return achFieldRules;
    }
}
