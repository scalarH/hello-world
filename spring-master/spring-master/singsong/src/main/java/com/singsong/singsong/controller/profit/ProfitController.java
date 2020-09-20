package com.singsong.singsong.controller.profit;

import java.io.UnsupportedEncodingException;


import com.singsong.singsong.dto.Owner.Owner;
import com.singsong.singsong.service.owner.ownerService;
import com.singsong.singsong.service.profit.profitService;
import com.singsong.singsong.util.URLparser;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfitController {
    @Autowired
    profitService profitservice;

    @Autowired
    ownerService ownerservice;

    URLparser parser = new URLparser();
    //사장
    @PostMapping("/profit/getList")
    public ResponseEntity<Object> allowner(@RequestBody String json) throws UnsupportedEncodingException,ParseException{
        
        org.json.simple.JSONObject ob = parser.parseurl(json);
        String id = ob.get("CUST_ID").toString();
        
        Owner owner = ownerservice.getOwner(id);

    
        return new ResponseEntity<>(profitservice.getList(owner.getOid()), HttpStatus.OK);

    }
}
