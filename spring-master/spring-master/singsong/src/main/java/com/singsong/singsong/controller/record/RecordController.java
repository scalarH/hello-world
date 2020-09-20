package com.singsong.singsong.controller.record;

import java.io.UnsupportedEncodingException;


import com.singsong.singsong.service.record.RecordService;
import com.singsong.singsong.util.URLparser;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class RecordController {
    
    @Autowired
    RecordService recordservice;

    URLparser parser = new URLparser();

    @PostMapping("/record/getList")
    public ResponseEntity<Object> getRecordlist() throws UnsupportedEncodingException,ParseException{
        return new ResponseEntity<>(recordservice.getRecordList(), HttpStatus.OK);

    }

}
