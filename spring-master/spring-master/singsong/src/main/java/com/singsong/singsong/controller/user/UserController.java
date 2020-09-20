package com.singsong.singsong.controller.user;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.singsong.singsong.controller.concon.CooconController;
import com.singsong.singsong.dto.Owner.Owner;
import com.singsong.singsong.dto.Transinfo.Transinfo;
import com.singsong.singsong.dto.profit.Profit;
import com.singsong.singsong.dto.room.Room;
import com.singsong.singsong.dto.user.User;
import com.singsong.singsong.service.owner.ownerService;
import com.singsong.singsong.service.profit.profitService;
import com.singsong.singsong.service.room.RoomService;
import com.singsong.singsong.service.transinfo.transinfoService;
import com.singsong.singsong.service.user.userService;
import com.singsong.singsong.util.URLparser;

import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	userService userservice;
	
	@Autowired
	transinfoService transinfoservice;
	
	@Autowired
	ownerService ownerservice;

	@Autowired
	profitService profitservice;

	@Autowired
	RoomService roomservice;


	
	URLparser parser = new URLparser();


	@PostMapping("/user/join")
	public ResponseEntity<Object> userjoin(@RequestBody String json) throws UnsupportedEncodingException,ParseException{

		org.json.simple.JSONObject ob = parser.parseurl(json);
		User user = new User();
		user.setU_id(ob.get("u_id").toString());
		user.setU_pw(ob.get("u_pw").toString());

		if(userservice.getUser(ob.get("u_id").toString()) == null ){
			int result = userservice.joinUser(user);
			
			CooconController con = new  CooconController();
			con.checkAccount(user.getU_id());


			Date date = new Date();
			SimpleDateFormat formatran = new SimpleDateFormat("ymdHms");
			String hashString = formatran.format(date).hashCode()+"";
			hashString = hashString.substring(hashString.length()-6,hashString.length());
			String TRAN_SEQ = "s"+hashString;
        	
			JSONObject obj = new JSONObject();
			obj.put("CUST_ID", user.getU_id());
			obj.put("TRAN_SEQ", TRAN_SEQ);
			obj.put("BNK_CD", "002");
			obj.put("ACCT_NO", "12312386");
			obj.put("TRAN_AMT", "10000000");

			con.sendmoney(obj.toString()+"=");


			if (result == 1) {
				return new ResponseEntity<>("success", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("fail", HttpStatus.OK);
			}
		}
		else{
			return new ResponseEntity<>("fail", HttpStatus.OK);
		}	

	}

	@PostMapping("/user/login")
	public ResponseEntity<Object> login(@RequestBody String json) throws UnsupportedEncodingException,ParseException {

		
		org.json.simple.JSONObject ob = parser.parseurl(json);
		
		User user = new User();
		user.setU_id(ob.get("u_id").toString());
		user.setU_pw(ob.get("u_pw").toString());
		User result = userservice.login(user);

		if (result != null) {
			return new ResponseEntity<>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("fail", HttpStatus.OK);
		}
	}

	@PostMapping("/user/getinfo")
	public ResponseEntity<Object> checkAccountApi(@RequestBody String json) throws UnsupportedEncodingException,ParseException {
		
		org.json.simple.JSONObject ob = parser.parseurl(json);;

		String id = "";
		id = ob.get("id").toString();
		
		User user = userservice.getUser(id);
		if(user != null){
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		else{
			return new ResponseEntity<>("fail", HttpStatus.OK);
		}
	}

	@PostMapping("/user/charge")
	public ResponseEntity<Object> chargeMoney(@RequestBody String json) throws UnsupportedEncodingException,ParseException {
		
		org.json.simple.JSONObject ob = parser.parseurl(json);;
		System.out.println(ob.toString());
		String CUST_ID = ob.get("CUST_ID").toString();
		String TRAN_AMT = ob.get("TRAN_AMT").toString();


		User user = userservice.getUser(CUST_ID);

		
		
		
		String TRAN_SEQ = getseq();
		String BNK_CD = user.getU_bank();
		String ACCT_NO = user.getU_account();

		JSONObject obj = new JSONObject();
		obj.put("CUST_ID", user.getU_id());
		obj.put("TRAN_SEQ", TRAN_SEQ);
		obj.put("BNK_CD", BNK_CD);
		obj.put("ACCT_NO", ACCT_NO);
		obj.put("TRAN_AMT", TRAN_AMT);

		CooconController con = new  CooconController();
		String result = con.receiveMoney(obj.toString()+"=");

		if(result.equals("0000")){
			//db에 업데이트
			Transinfo ti = new Transinfo();
			ti.setT_u_id(user.getU_id());
			ti.setT_money(Integer.parseInt(TRAN_AMT));
			ti.setT_type("0");

			Date date = new Date();
			SimpleDateFormat formatrans = new SimpleDateFormat("y/M/d/H/m");
			String dateee = formatrans.format(date);
			ti.setT_date(dateee);

			transinfoservice.writeinfo(ti);

			return new ResponseEntity<>("success", HttpStatus.OK);
		}
		else{
			return new ResponseEntity<>("fail", HttpStatus.OK);
		}
	}

	@PostMapping("/user/payment")
	public ResponseEntity<Object> payment(@RequestBody String json) throws UnsupportedEncodingException,ParseException {
		
		org.json.simple.JSONObject ob = parser.parseurl(json);
		final String CUST_ID = ob.get("CUST_ID").toString();//사용자 ID
		final String OWNER_ID = ob.get("OWNER_ID").toString();//노래방 주인 ID
		final String TRAN_AMT = ob.get("TRAN_AMT").toString();//결제 금액
		final String ROOM_NUM = ob.get("ROOM_NUM").toString();//방번호
		
		// 고객 출금
		User user = userservice.getUser(CUST_ID);
		System.out.println(user);

		JSONObject obj = new JSONObject();
		obj.put("CUST_ID", CUST_ID);
		obj.put("TRAN_SEQ", getseq());
		obj.put("BNK_CD", user.getU_bank());
		obj.put("ACCT_NO", user.getU_account());
		obj.put("TRAN_AMT", TRAN_AMT);

		CooconController con = new CooconController();
		String code = con.sendmoney(obj.toString()+"=");

		if(code.equals("0000")){

			//거래내역 고객 디비에 입력
			Transinfo ti = new Transinfo();
			ti.setT_u_id(user.getU_id());
			ti.setT_money(Integer.parseInt(TRAN_AMT));
			ti.setT_type("1");

			Date date = new Date();
			SimpleDateFormat formatrans = new SimpleDateFormat("y/M/d/H/m");
			String dateee = formatrans.format(date);
			ti.setT_date(dateee);

			transinfoservice.writeinfo(ti);


			


			//사장에게 송금
			Owner owner =  ownerservice.getOwner(OWNER_ID);
			System.out.println(owner);
			obj = new JSONObject();
			obj.put("CUST_ID", OWNER_ID);
			obj.put("TRAN_SEQ", getseq().substring(0,5)+"p");
			obj.put("BNK_CD", owner.getO_bank());
			obj.put("ACCT_NO", owner.getO_account());
			obj.put("TRAN_AMT", TRAN_AMT);

			code = con.receiveMoney(obj.toString()+"=");


			if(code.equals("0000")){


				//거래내역 사장 디비에 입력
				Profit profit = new Profit();
				profit.setP_o_id(owner.getOid());
				profit.setP_price(Integer.parseInt(TRAN_AMT));
				profit.setP_type("0");
				profit.setP_date(dateee);
				// System.out.println(profit);
				profitservice.writeProfit(profit);

				//방 사용
				Room room = new Room();
				room.setSr_u_id(user.getUid());
				room.setSr_o_id(owner.getOid());
				room.setSr_song(Integer.parseInt(TRAN_AMT)/1000 * owner.getO_songByMoney());
				room.setSr_date(dateee);
				roomservice.writeroomDeatil(room);
				
				return new ResponseEntity<>("success", HttpStatus.OK);
			}
			else{
				return new ResponseEntity<>("fail", HttpStatus.OK);
			}
		}
		else{
			return new ResponseEntity<>("fail", HttpStatus.OK);
		}
	}

	@PostMapping("/user/updateAccount")
	public ResponseEntity<Object> updateAccount(@RequestBody String json) throws UnsupportedEncodingException,ParseException {
		org.json.simple.JSONObject ob = parser.parseurl(json);
		User user = new User();
		user.setU_id(ob.get("u_id").toString());
		user.setU_bank(ob.get("u_bank").toString());
		user.setU_account(ob.get("u_account").toString());
		user.setU_account(ob.get("u_name").toString());

		int result = userservice.updateAccount(user);
		System.out.println(user);
		if(result > 0){
			return new ResponseEntity<>("success", HttpStatus.OK);
		}
		else{
			return new ResponseEntity<>("fail", HttpStatus.OK);
		}

		
	}










	public String getseq(){
		Date date = new Date();
		SimpleDateFormat formatran = new SimpleDateFormat("ymdHms");
		String hashString = formatran.format(date).hashCode()+"";
		hashString = hashString.substring(hashString.length()-6,hashString.length());


		return "s"+hashString;
	}



}
