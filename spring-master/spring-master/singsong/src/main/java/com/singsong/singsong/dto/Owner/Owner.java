package com.singsong.singsong.dto.Owner;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Owner {
    int oid;
    String o_id;
    String o_pw;
    String o_name;
    String o_bank;
    String o_account;
    String o_address;
    String o_lat;
    String o_lon;
    int o_songByMoney;
    String o_singingroomname;
}
