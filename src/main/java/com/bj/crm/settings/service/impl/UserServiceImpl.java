package com.bj.crm.settings.service.impl;

import com.bj.crm.exception.LoginException;
import com.bj.crm.settings.dao.UserDao;
import com.bj.crm.settings.domain.User;
import com.bj.crm.settings.service.UserService;
import com.bj.crm.utils.DateTimeUtil;
import com.bj.crm.utils.SqlSessionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

 
    public User login(String loginAct, String loginPwd, String ip) throws LoginException {
        Map<String,String> map = new HashMap<String,String>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);
        User user = userDao.login(map);
        if(user==null){
            throw new LoginException("该用户不存在");
        }
/*        else{
            String expireTime = user.getExpireTime();
            String currentTime = DateTimeUtil.getSysTime();
            if(expireTime.compareTo(currentTime)<0){
                throw new LoginException("账号已过期");
            }

            String lockState = user.getLockState();
            if(lockState.equals("0")){
                throw new LoginException("账号已被锁定");
            }

            String allowIps = user.getAllowIps();
            if(!allowIps.contains(ip)){
                throw new LoginException("该IP被禁止登陆");
            }
        }*/
        return user;
    }

    @Override
    public List<User> getUserList() {
        List<User> ulist = userDao.getUserList();
        return ulist;
    }
}
