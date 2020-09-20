package com.singsong.singsong.controller.concon;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.singsong.singsong.util.URLparser;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@RestController
public class CooconController {


	
	URLparser parser = new URLparser();

	Date date = new Date();
	SimpleDateFormat formatDay = new SimpleDateFormat("yyyyMMdd");
	SimpleDateFormat formatTime = new SimpleDateFormat("HHmmss");

	final String TRAN_DT = formatDay.format(date);
	final String TRAN_TM = formatTime.format(date);
	final String OGN_CD = "HKT00004";
	

	@PostMapping("/coocon/checkAccount")
	public ResponseEntity<Object> checkAccountApi(@RequestBody String json) throws UnsupportedEncodingException,ParseException{
		System.out.println("111111111111");
		org.json.simple.JSONObject ob = parser.parseurl(json);

		HashMap<String,String> hm = checkAccount(ob.get("u_id").toString());
		return new ResponseEntity<>(hm, HttpStatus.OK);
	}

	//++
	@PostMapping("/coocon/reveiveMoney")
    public ResponseEntity<Object> receiveMoneyApi(@RequestBody String json) throws UnsupportedEncodingException,ParseException{
		return new ResponseEntity<>(receiveMoney(json), HttpStatus.OK);
	}

	//--
	@PostMapping("/coocon/sendMoney")
    public ResponseEntity<Object> sendmoneyApi(@RequestBody String json) throws UnsupportedEncodingException,ParseException{
		return new ResponseEntity<>(sendmoney(json), HttpStatus.OK);
	}


    public HashMap<String,String> checkAccount(String id){

        String url = "https://dev.checkpay.co.kr/HKT_API_101.jct?";

		HttpURLConnection con = null; //java.net.HttpURLConnection; BufferedWriter
		BufferedWriter bwriter = null; //java.io.BufferedWriter;
		DataInputStream in = null; //java.io.DataInputStream;
		ByteArrayOutputStream bout = null; //java.io.ByteArrayOutputStream
		String respData = "";
		HashMap<String,String> hm = new HashMap<>();
        
		try {
			JSONObject param = new JSONObject(); //json_simple-1.1.jar 사용
			param.put("OGN_CD", OGN_CD); //쿠콘에서 발급한 기관코드
			param.put("CUST_ID", id); //사용자별 유니크한 ID
			param.put("TRAN_DT", TRAN_DT); //거래일자
			param.put("TRAN_TM", TRAN_TM); //거래시간
            param.put("TRAN_DIV", "101"); //거래구분
            
            for(String key : param.keySet()){
                url+=key+"=";
                url+=param.getString(key)+"&";
			}

			//연결설정
			URL req = new URL(url); //java.net.URL;
            con = (HttpURLConnection)req.openConnection();
			con.setConnectTimeout(5*1000); // 5초
			con.setReadTimeout(10*1000); // 10초
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			////////////////////////////////////////////
			//데이터 송신 java.io.OutputStreamWriter; java.net.URLEncoder;
			bwriter = new BufferedWriter(new OutputStreamWriter(con.getOutputStream(), "UTF-8"));
		
			////////////////////////////////////////////
			//데이터 수신
			//정상일경우
			if(HttpURLConnection.HTTP_OK == con.getResponseCode()){
				in = new DataInputStream(con.getInputStream());
			}
			else{
				//에러일경우
				in = new DataInputStream(con.getErrorStream());
			}
			bout = new ByteArrayOutputStream();
			while (true) {
				byte[] buf = new byte[2048];
				int n = in.read(buf);
				if (n == -1) break;
				bout.write(buf, 0, n);
			}
			bout.flush();
			respData = new String(bout.toByteArray()); //응답데이터


			
			}
			catch (MalformedURLException e) {
			e.printStackTrace();
			} 
			catch (IOException e) {
			e.printStackTrace();
			} 
			finally{
			try {
				if ( bwriter != null ) bwriter.close();
				if ( in != null ) in.close();
				if ( bout != null ) bout.close();
				if ( con != null ) con.disconnect();
			} 
			catch(Exception se) {}
			}
			try{
				// 결과 파싱
				JSONParser parser = new JSONParser();
				org.json.simple.JSONObject json = (org.json.simple.JSONObject)parser.parse(respData);
				System.out.println(json.get("REPY_CD"));
				System.out.println(json.get("BAL_AMT"));

				hm.put("REPY_CD", json.get("REPY_CD").toString());
				hm.put("BAL_AMT", json.get("BAL_AMT").toString());

				System.out.println(json.toString());
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
        return hm;
	}
	

	



	//고객기준 돈 ++
    public String receiveMoney(String json) throws UnsupportedEncodingException,ParseException{

		String url = "https://dev.checkpay.co.kr/HKT_API_201.jct?";

		HttpURLConnection con = null; //java.net.HttpURLConnection; BufferedWriter
		BufferedWriter bwriter = null; //java.io.BufferedWriter;
		DataInputStream in = null; //java.io.DataInputStream;
		ByteArrayOutputStream bout = null; //java.io.ByteArrayOutputStream
		String respData = "";
		
		org.json.simple.JSONObject ob = parser.parseurl(json);
		String CUST_ID = ob.get("CUST_ID").toString();
		String TRAN_SEQ = ob.get("TRAN_SEQ").toString();
		String BNK_CD = ob.get("BNK_CD").toString();
		String ACCT_NO = ob.get("ACCT_NO").toString();
		String TRAN_AMT = ob.get("TRAN_AMT").toString();
		
		
		
		


		try {
			JSONObject param = new JSONObject(); //json_simple-1.1.jar 사용
			param.put("OGN_CD", OGN_CD); //쿠콘에서 발급한 기관코드
			param.put("CUST_ID", CUST_ID); //사용자별 유니크한 ID
			param.put("TRAN_DT", TRAN_DT); //거래일자
			param.put("TRAN_TM", TRAN_TM); //거래시간
			param.put("TRAN_DIV", "201"); //거래구분
			param.put("TRAN_SEQ", TRAN_SEQ); //거래구분
			param.put("BNK_CD", BNK_CD); //거래구분
			param.put("ACCT_NO", ACCT_NO); //거래구분
			param.put("TRAN_AMT", TRAN_AMT); //거래구분
            
            for(String key : param.keySet()){
                url+=key+"=";
                url+=param.getString(key)+"&";
			}

			//연결설정
			URL req = new URL(url); //java.net.URL;
            con = (HttpURLConnection)req.openConnection();
			con.setConnectTimeout(5*1000); // 5초
			con.setReadTimeout(10*1000); // 10초
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			////////////////////////////////////////////
			//데이터 송신 java.io.OutputStreamWriter; java.net.URLEncoder;
			bwriter = new BufferedWriter(new OutputStreamWriter(con.getOutputStream(), "UTF-8"));
		
			////////////////////////////////////////////
			//데이터 수신
			//정상일경우
			if(HttpURLConnection.HTTP_OK == con.getResponseCode()){
				in = new DataInputStream(con.getInputStream());
			}
			else{
				//에러일경우
				in = new DataInputStream(con.getErrorStream());
			}
			bout = new ByteArrayOutputStream();
			while (true) {
				byte[] buf = new byte[2048];
				int n = in.read(buf);
				if (n == -1) break;
				bout.write(buf, 0, n);
			}
			bout.flush();
			respData = new String(bout.toByteArray()); //응답데이터

			}
			catch (MalformedURLException e) {
			e.printStackTrace();
			} 
			catch (IOException e) {
			e.printStackTrace();
			} 
			finally{
			try {
				if ( bwriter != null ) bwriter.close();
				if ( in != null ) in.close();
				if ( bout != null ) bout.close();
				if ( con != null ) con.disconnect();
			} 
			catch(Exception se) {}
			}

			String result = "";
			try{
				JSONParser parser = new JSONParser();
				org.json.simple.JSONObject js = (org.json.simple.JSONObject)parser.parse(respData);
				result = js.get("REPY_CD").toString();
				
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}




		return result;
	}

	//고객기준 돈 -- 
    public String sendmoney(String json) throws UnsupportedEncodingException,ParseException{

		
		org.json.simple.JSONObject ob = parser.parseurl(json);
		String CUST_ID = ob.get("CUST_ID").toString();
		String TRAN_SEQ = ob.get("TRAN_SEQ").toString();
		String BNK_CD = ob.get("BNK_CD").toString();
		String ACCT_NO = ob.get("ACCT_NO").toString();
		String TRAN_AMT = ob.get("TRAN_AMT").toString();


		String url = "https://dev.checkpay.co.kr/HKT_API_301.jct?";

		HttpURLConnection con = null; //java.net.HttpURLConnection; BufferedWriter
		BufferedWriter bwriter = null; //java.io.BufferedWriter;
		DataInputStream in = null; //java.io.DataInputStream;
		ByteArrayOutputStream bout = null; //java.io.ByteArrayOutputStream
        String respData = "";
        
		try {
			JSONObject param = new JSONObject(); //json_simple-1.1.jar 사용
			param.put("OGN_CD", OGN_CD); //쿠콘에서 발급한 기관코드
			param.put("CUST_ID", CUST_ID); //사용자별 유니크한 ID
			param.put("TRAN_DT", TRAN_DT); //거래일자
			param.put("TRAN_TM", TRAN_TM); //거래시간
			param.put("TRAN_DIV", "301"); //거래구분
			param.put("TRAN_SEQ", TRAN_SEQ); //거래구분
			param.put("BNK_CD", BNK_CD); // 은행
			param.put("ACCT_NO", ACCT_NO); // 계좌번호
			param.put("TRAN_AMT", TRAN_AMT); // 거래구분

            
            for(String key : param.keySet()){
                url+=key+"=";
                url+=param.getString(key)+"&";
			}

			//연결설정
			URL req = new URL(url); //java.net.URL;
            con = (HttpURLConnection)req.openConnection();
			con.setConnectTimeout(5*1000); // 5초
			con.setReadTimeout(10*1000); // 10초
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			////////////////////////////////////////////
			//데이터 송신 java.io.OutputStreamWriter; java.net.URLEncoder;
			bwriter = new BufferedWriter(new OutputStreamWriter(con.getOutputStream(), "UTF-8"));
		
			////////////////////////////////////////////
			//데이터 수신
			//정상일경우
			if(HttpURLConnection.HTTP_OK == con.getResponseCode()){
				in = new DataInputStream(con.getInputStream());
			}
			else{
				//에러일경우
				in = new DataInputStream(con.getErrorStream());
			}
			bout = new ByteArrayOutputStream();
			while (true) {
				byte[] buf = new byte[2048];
				int n = in.read(buf);
				if (n == -1) break;
				bout.write(buf, 0, n);
			}
			bout.flush();
			respData = new String(bout.toByteArray()); //응답데이터

			}
			catch (MalformedURLException e) {
			e.printStackTrace();
			} 
			catch (IOException e) {
			e.printStackTrace();
			} 
			finally{
			try {
				if ( bwriter != null ) bwriter.close();
				if ( in != null ) in.close();
				if ( bout != null ) bout.close();
				if ( con != null ) con.disconnect();
			} 
			catch(Exception se) {}
			}



			String result = "";
			try{
				JSONParser parser = new JSONParser();
				org.json.simple.JSONObject js = (org.json.simple.JSONObject)parser.parse(respData);
				result = js.get("REPY_CD").toString();
				
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}


		return result;
	}
    
}
