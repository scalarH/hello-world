package com.singsong.singsong.dao.transinfo.Impl;

import java.util.List;

import com.singsong.singsong.dao.transinfo.TransinfoDao;
import com.singsong.singsong.dto.Transinfo.Transinfo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TransinfoDaoImpl implements TransinfoDao {

    @Autowired
    SqlSession sqlsession;

    @Override
    public int writeinfo(Transinfo ti) {

        return sqlsession.insert("transinfomapper.writetransinfo", ti);
    }

    @Override
    public List<Transinfo> getList(String id) {
        return sqlsession.selectList("transinfomapper.getList",id);
    }

    
}
