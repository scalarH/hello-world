package com.singsong.singsong.service.user;

import com.singsong.singsong.dto.user.User;

public interface userService {
    User getUser(String id);
    int joinUser(User user);
    User login(User user);
    int updateAccount(User user);
}
