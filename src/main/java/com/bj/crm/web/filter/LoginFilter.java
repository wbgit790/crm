package com.bj.crm.web.filter;


import com.bj.crm.settings.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("进入到拦截器");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        HttpSession session = httpServletRequest.getSession();
        String path = httpServletRequest.getServletPath();

        if("/login.jsp".equals(path) || "/settings/user/login.do".equals(path)){
            System.out.println("getServletPath="+path);
            filterChain.doFilter(servletRequest,servletResponse);
        }
        else{

            User user = (User)session.getAttribute("user");
            if(user!=null){
                filterChain.doFilter(servletRequest,servletResponse);
            }else{
                System.out.println("getContextPath="+httpServletRequest.getContextPath());
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/login.jsp");

            }

        }

    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
