package com.singsong.singsong.dto.record;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Record {

    int rid;
    String r_u_id;
    String r_url;
    int r_likes;
    String r_users;
    String r_date;

}
