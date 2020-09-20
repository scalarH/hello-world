package com.singsong.singsong.service.transinfo;

import java.util.List;

import com.singsong.singsong.dto.Transinfo.Transinfo;

public interface transinfoService {
    int writeinfo(Transinfo ti);
    List<Transinfo> getList(String id);
}
