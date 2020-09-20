package com.singsong.singsong.dao.transinfo;

import java.util.List;

import com.singsong.singsong.dto.Transinfo.Transinfo;

public interface TransinfoDao {
    int writeinfo(Transinfo ti);
    List<Transinfo> getList(String id);
}
