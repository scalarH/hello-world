package com.singsong.singsong.dao.owner.Impl;

import java.util.List;

import com.singsong.singsong.dao.owner.ownerDao;
import com.singsong.singsong.dto.Owner.Owner;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ownerDaoImpl implements ownerDao {

    @Autowired
    SqlSession sqlsession;

    @Override
    public List<Owner> allOwner() {
        return sqlsession.selectList("ownermapper.allowner");
    }

    @Override
    public Owner getOwner(String id) {
        return sqlsession.selectOne("ownermapper.getowner",id);
    }


    
}
