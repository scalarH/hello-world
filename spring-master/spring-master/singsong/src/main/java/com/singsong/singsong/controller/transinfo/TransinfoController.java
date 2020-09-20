package com.singsong.singsong.controller.transinfo;

import java.io.UnsupportedEncodingException;


import com.singsong.singsong.service.transinfo.transinfoService;
import com.singsong.singsong.util.URLparser;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransinfoController {
    @Autowired
    transinfoService transinfoservice;

    URLparser parser = new URLparser();

    @PostMapping("/transinfo/getList")
    public ResponseEntity<Object> allowner(@RequestBody String json) throws UnsupportedEncodingException,ParseException{
        
        org.json.simple.JSONObject ob = parser.parseurl(json);
        String id = ob.get("CUST_ID").toString();



        return new ResponseEntity<>(transinfoservice.getList(id), HttpStatus.OK);

    }
}
