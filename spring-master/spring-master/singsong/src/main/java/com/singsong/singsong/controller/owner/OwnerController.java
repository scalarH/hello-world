package com.singsong.singsong.controller.owner;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;

import com.singsong.singsong.service.owner.ownerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OwnerController {
    
    @Autowired
    ownerService ownerservice;

    @PostMapping("/owner/allowner")
    public ResponseEntity<Object> allowner() throws UnsupportedEncodingException,ParseException{
    
    return new ResponseEntity<>(ownerservice.allOwner(), HttpStatus.OK);

    }

}
