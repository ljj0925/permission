package com.test.Filter;

import com.test.common.RequestHolder;
import com.test.sys.entity.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** 自定义filter，需在web.xml中配置
 *      拦截指定请求，将用户信息放入ThreadLocal
 * @author
 * @create 2019-11-24 21:21
 */
public class LoginFilter implements Filter {

    private Logger log = LoggerFactory.getLogger(LoginFilter.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //log.warn("=============================LoginFilter==begin");
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        //用户登录成功后将用户信息放入session，这里直接获取
        SysUser sysUser = (SysUser)request.getSession().getAttribute("user");
        if (sysUser == null){
            //用户未登录，强制跳转到登录页面
            String path = "/signin.jsp";
            response.sendRedirect(path);
            return;
        }
        //用户已登录，用户信息存入ThreadLocal
        RequestHolder.add(sysUser);
        RequestHolder.add(request);

        //放行
        filterChain.doFilter(servletRequest,servletResponse);
        //log.warn("=============================LoginFilter==end");
    }
}
