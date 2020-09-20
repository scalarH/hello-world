package com.singsong.singsong.service.record.Impl;

import java.util.List;

import com.singsong.singsong.dao.record.RecordDao;
import com.singsong.singsong.dto.record.Record;
import com.singsong.singsong.service.record.RecordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RecordServiceImple implements RecordService {

    @Autowired
    RecordDao recorddao;

    @Override
    public List<Record> getRecordList() {
        return recorddao.getRecordList();
    }
    
}
