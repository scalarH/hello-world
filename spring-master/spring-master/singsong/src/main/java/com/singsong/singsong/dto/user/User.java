package com.singsong.singsong.dto.user;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {
    
    int uid;
    String u_id;
    String u_pw;
    String u_name;
    String u_bank;
    String u_account;
    String u_profile;
    String u_recorde;
    int u_chargeMoney;

}
