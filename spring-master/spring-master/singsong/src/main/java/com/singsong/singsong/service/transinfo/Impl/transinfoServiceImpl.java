package com.singsong.singsong.service.transinfo.Impl;

import java.util.List;

import com.singsong.singsong.dao.transinfo.TransinfoDao;
import com.singsong.singsong.dto.Transinfo.Transinfo;
import com.singsong.singsong.service.transinfo.transinfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class transinfoServiceImpl implements transinfoService {

    @Autowired
    TransinfoDao transdao;

    @Override
    public int writeinfo(Transinfo ti) {
        return transdao.writeinfo(ti);
    }

    @Override
    public List<Transinfo> getList(String id) {
        return transdao.getList(id);
    }
    
}
