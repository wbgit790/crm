package com.bj.crm.settings.service;

import com.bj.crm.exception.LoginException;
import com.bj.crm.settings.domain.User;

import java.util.List;

public interface UserService {
    User login(String loginAct, String loginPwd, String ip) throws LoginException;

    List<User> getUserList();


}
