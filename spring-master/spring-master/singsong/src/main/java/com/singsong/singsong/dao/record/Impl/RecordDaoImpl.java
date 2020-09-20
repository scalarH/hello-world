package com.singsong.singsong.dao.record.Impl;

import java.util.List;

import com.singsong.singsong.dao.record.RecordDao;
import com.singsong.singsong.dto.record.Record;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RecordDaoImpl implements RecordDao {

    @Autowired
    SqlSession sqlsession;


    @Override
    public List<Record> getRecordList() {
        return sqlsession.selectList("recordmapper.getlist");
    }


    
}
