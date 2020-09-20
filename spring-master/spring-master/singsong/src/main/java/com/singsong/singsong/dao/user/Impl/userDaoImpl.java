package com.singsong.singsong.dao.user.Impl;

import com.singsong.singsong.dao.user.userDao;
import com.singsong.singsong.dto.user.User;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class userDaoImpl implements userDao {

    @Autowired
    SqlSession sqlsession;

    @Override
    public User getUser(String id) {
        return sqlsession.selectOne("usermapper.getuserinfo", id);
    }

    @Override
    public int joinUser(User user) {
        return sqlsession.insert("usermapper.join",user);
    }

    @Override
    public User login(User user) {
        return sqlsession.selectOne("usermapper.login",user);
    }

    @Override
    public int updateAccount(User user) {
        return sqlsession.update("usermapper.updateAccount",user);
    }
    
}
