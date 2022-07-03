package com.loanpro.achlibrary.router;

import com.loanpro.achlibrary.model.ACHRecord;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.loanpro.achlibrary.model.ACHPage;

import java.io.*;

@RestController
public class ValidatorRouter {

    //todo: change to post request that accepts a string of ACH data
    @RequestMapping(value = "/ach-data-validate", method = RequestMethod.POST, consumes = {MediaType.TEXT_PLAIN_VALUE})
    public Object achDataIn(@RequestBody String data) {
        if (!data.isEmpty()) {
            StringReader achDataReader = new StringReader(data);
            ACHPage achPage = new ACHPage(1, '1',achDataReader);
            return achPage;
        }
        return "page is empty";
    }

    @RequestMapping(path = "/ach-file-validate", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Object achPageIn(@RequestPart MultipartFile page) throws Exception {
        if (!page.isEmpty()) {
            BufferedReader achDataReader = new BufferedReader(new InputStreamReader(page.getInputStream(), "UTF-8"));
            return new ACHPage(1, '1',achDataReader);
        }
        return "Page is empty";
    }
}