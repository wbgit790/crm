package com.bj.crm.workbench.web.controller;


import com.bj.crm.settings.domain.User;
import com.bj.crm.settings.service.UserService;
import com.bj.crm.settings.service.impl.UserServiceImpl;
import com.bj.crm.utils.PrintJson;
import com.bj.crm.utils.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class ActicityController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        if("/workbench/activity/getUserList.do".equals(path)){
            getUserList(req,resp);
        }
    }

    private void getUserList(HttpServletRequest req, HttpServletResponse resp){
        System.out.println("取得用户信息列表");

        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());

        List<User> ulist = us.getUserList();

        PrintJson.printJsonObj(resp,ulist);

    }
}
