package com.yidao.datacenter.utils.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 在SpringBoot中通过注解注册的方式简单的使用Filter
 * @author chengxi
 */
@Component
@ServletComponentScan
@WebFilter(urlPatterns = "/*", filterName = "sessionCheckFilter")
public  class SessionCheckFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("开始进行过滤处理");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       /*HttpServletRequest httpReq =(HttpServletRequest)servletRequest;
        String url = httpReq.getRequestURI();
        if((url.contains("/api/"))&&!url.endsWith("/pwdLogin")&&
                !url.endsWith("/checkCodeLogin")&&
                !url.endsWith("/setNewPwd")&&
                !url.endsWith("/regist")&&
                !url.endsWith("/checkcode")){
            HttpSession session = httpReq.getSession();
            Object user=session.getAttribute(AppConst.session_key);
            if(user==null){
                JSONHelper.returnInfo(JSONHelper.returnJsonString(AppConst.NOSESSION_ERR_CODE,AppConst.NOSESSION_ERR_MSG));
                return;
            }
        }*/
        //调用该方法后，表示过滤器经过原来的url请求处理方法
        logger.info("---------------处理1x-------------");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("Filter销毁中");
    }
}