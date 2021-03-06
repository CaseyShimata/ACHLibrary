package com.loanpro.achlibrary.router;

import com.loanpro.achlibrary.model.ACHField;
import com.loanpro.achlibrary.model.ACHRecord;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.loanpro.achlibrary.model.ACHPage;

import java.io.*;

import static org.springframework.http.MediaType.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ValidatorRouter {

    //todo: change to post request that accepts a string of ACH data
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/ach-data-validate", method = RequestMethod.POST, consumes = {MediaType.TEXT_PLAIN_VALUE})
    public Object achDataIn(@RequestBody String data) {
        if (!data.isEmpty()) {
            StringReader achDataReader = new StringReader(data);
            ACHPage achPage = new ACHPage(1, '1',achDataReader);
            return achPage;
        }
        return "page is empty";
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(path = "/ach-file-validate", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Object achFileIn(@RequestPart MultipartFile page) throws Exception {
        if (!page.isEmpty()) {
            BufferedReader achDataReader = new BufferedReader(new InputStreamReader(page.getInputStream(), "UTF-8"));
            return new ACHPage(1, '1',achDataReader);
        }
        return "Page is empty";
    }
}