package com.singsong.singsong.dao.user;

import com.singsong.singsong.dto.user.User;

public interface userDao {
    int joinUser(User user);
    User getUser(String id);
    User login(User user);
    int updateAccount(User user);
}
