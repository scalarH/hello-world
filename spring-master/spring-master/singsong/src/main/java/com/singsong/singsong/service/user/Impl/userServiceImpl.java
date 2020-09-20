package com.singsong.singsong.service.user.Impl;

import com.singsong.singsong.dao.user.userDao;
import com.singsong.singsong.dto.user.User;
import com.singsong.singsong.service.user.userService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userServiceImpl implements userService {

    @Autowired
    userDao userdao;



    @Override
    public User getUser(String id) {
        return userdao.getUser(id);
    }

    @Override
    public int joinUser(User user) {
        return userdao.joinUser(user);
    }

    @Override
    public User login(User user) {
        return userdao.login(user);
    }

    @Override
    public int updateAccount(User user) {
        return userdao.updateAccount(user);
    }
    
    
}
