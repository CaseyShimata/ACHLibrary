package com.loanpro.achlibrary.router;

import com.loanpro.achlibrary.model.ACHRecord;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.loanpro.achlibrary.model.ACHFile;

import java.io.*;

@RestController
public class ValidatorRouter {

    //todo: change to post request that accepts a string of ACH data
    @RequestMapping(value = "/ach-data-validate", method = RequestMethod.POST, consumes = {MediaType.TEXT_PLAIN_VALUE})
    public Object achDataIn(@RequestBody String data) {
        if (!data.isEmpty()) {
            try {
                return mapACHDATA(new StringReader(data));
            } catch (IOException e) {
                e.printStackTrace();
                return e;
            }
        }
        return "File is empty";
    }

    @RequestMapping(path = "/ach-file-validate", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Object achFileIn(@RequestPart MultipartFile file) throws Exception {
        if (!file.isEmpty()) {
            try {
               return mapACHDATA(new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8")));
            } catch (IOException e) {
                e.printStackTrace();
                return e;
            }
        }
        return "File is empty";
    }

    private ACHFile mapACHDATA(Reader chars) throws IOException {
        int r;
        int charCountInRecord = 1;
        int recordCount = 1;
        String rawRecord = "";
        ACHFile achFile = new ACHFile();
        while ((r = chars.read()) != -1) {
            char ch = (char) r;
            //if it is the first letter of the record
            if (charCountInRecord == 1) {
               //create a new record
                achFile.appendToRecords(new ACHRecord(ch,recordCount));
                //if it is not the first record of the page
                if (recordCount != 1){
                    //set the previous records next record type (so we can more efficiently check if a records next record type is valid.
                   achFile.getRecordAtIndex(recordCount - 2).setNextRecordType(ch);
               }
            }
            //if it is the last character of the record
            if (ch == '\n'){
                charCountInRecord = 1;
                recordCount+=1;
                //do not append to the record but continue on to make the next record
                continue;
            }
            rawRecord+=ch;
            achFile.getRecordAtIndex(recordCount - 1).appendToRawRecord(ch);
            charCountInRecord += 1;

            //if char is /n then save the record index, type code, and length to the record objects properties





            //store these validation
            //later when doing validation we will check that the current record matches a pattern property. We will return the line number that is out of order, its type code, and what type code should be there.
            //later when doing validation we iterate the array of records checking each one's length & returning its line number, and record type to the errors array if it fails.

            //todo: get mapping rules and instantiate classes to hold the files, records, and fields
            //file validations:
            //first validation modulo count of records
            //second validation check count of chars in each record
            //third validation check that records are in correct order
            //fourth validation check that all required records are present
            //(determine decently efficient way to reduce time complexity while iterating to check).
        }
        achFile.setRawFile(rawRecord);
        return achFile;

    }
}