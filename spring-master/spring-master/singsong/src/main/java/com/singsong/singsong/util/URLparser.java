package com.singsong.singsong.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class URLparser {

    JSONParser parser = new JSONParser();
    
    public JSONObject parseurl(String json) throws UnsupportedEncodingException,ParseException{

        json = URLDecoder.decode(json, "UTF-8");
		json = json.substring(0,json.length()-1);

        return (JSONObject)parser.parse(json);
    }
    
}
