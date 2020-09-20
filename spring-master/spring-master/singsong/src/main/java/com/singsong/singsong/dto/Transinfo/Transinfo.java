package com.singsong.singsong.dto.Transinfo;

import lombok.Data;
import lombok.ToString;

//거래내역

@Data
@ToString
public class Transinfo {
    int tid;
    String t_u_id;
    int t_money;
    String t_date;
    String t_type;//0 : 충전 , 1 : 소비
}
