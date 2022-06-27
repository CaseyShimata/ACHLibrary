package com.loanpro.achlibrary.validatorstructure.router;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
public class ValidatorRouter {

    //todo: change to post request that accepts a string of ACH data
    @RequestMapping(value = "/ach-data-validate", method = RequestMethod.POST, consumes = {MediaType.TEXT_PLAIN_VALUE})
    public String achDataIn(@RequestBody String data) {
        if (!data.isEmpty()) {
            try {
                return mapACHDATA(new StringReader(data));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    @RequestMapping(path = "/ach-file-validate", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String achFileIn(@RequestPart MultipartFile file) throws Exception {
        if (!file.isEmpty()) {
            try {
               return mapACHDATA(new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    private String mapACHDATA(Reader chars) throws IOException {
        String outPut = "";
        int r;
        int charCount = 0;
        int lineCount = 0;
        while ((r = chars.read()) != -1) {
            charCount += 1;
            char ch = (char) r;
            outPut+=ch;
            //track char count
            //track line count
            //if char is 1st on line then decide what structure to map the next 94 chars to
            //if char is /n then save the record index, type code, and length to the record objects properties
            //
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
        return outPut;

    }
}