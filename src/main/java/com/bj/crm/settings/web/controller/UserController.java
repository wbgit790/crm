package com.bj.crm.settings.web.controller;

import com.bj.crm.settings.domain.User;
import com.bj.crm.settings.service.UserService;
import com.bj.crm.settings.service.impl.UserServiceImpl;
import com.bj.crm.utils.MD5Util;
import com.bj.crm.utils.PrintJson;
import com.bj.crm.utils.ServiceFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入到用户控制器");
        String path = request.getServletPath();
        System.out.println(path);
        if("/settings/user/login.do".equals(path)){
            login(request,response);
        }else{

        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {
        String loginAct = request.getParameter("loginAct");
        String loginPwd = request.getParameter("loginPwd");
        loginPwd = MD5Util.getMD5(loginPwd);
        String ip = request.getRemoteAddr();
        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());
        try{
            //通过代理执行方法
            User user = us.login(loginAct,loginPwd,ip);

            request.getSession().setAttribute("user",user);
            PrintJson.printJsonFlag(response,true);
        }catch (Exception e){
            e.printStackTrace();
            String msg = e.getMessage();
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("success",false);
            map.put("msg",msg);
            PrintJson.printJsonObj(response,map);//负责转换为json传给response
        }

    }
}

