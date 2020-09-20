package com.singsong.singsong.dto.profit;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Profit {
    int p_id;
    int p_o_id;
    int p_price;
    String p_date;
    String p_type; //0 입금 , 1 환전
}

