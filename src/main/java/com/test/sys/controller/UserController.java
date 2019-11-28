package com.test.sys.controller;

import com.test.sys.entity.SysUser;
import com.test.sys.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/** 用户登录
 * @author
 * @create 2019-11-24 15:53
 */
@Controller
public class UserController {

    private Logger log = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private SysUserService sysUserService;


    @RequestMapping("/login.page")
    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.warn("==================login-begin");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //用户名匹配数据库中电话号或邮箱号
        SysUser sysUser = sysUserService.findByKeyword(username);
        String errorMsg = "";
        String ret = request.getParameter("ret");

        if (StringUtils.isBlank(username)) {
            errorMsg = "用户名不能为空";
        } else if (StringUtils.isBlank(password)) {
            errorMsg = "密码不能为空";
        } else if (sysUser == null) {
            errorMsg = "查询不到指定用户";
        } else if (!sysUser.getPassword().equals(password)) {
            errorMsg = "用户名或密码错误";
        } else if (sysUser.getStatus() != 1) {
            errorMsg = "用户已被冻结，请联系管理员";
        } else {
            //login success，将用户信息放入session
            request.getSession().setAttribute("user", sysUser);
            if (StringUtils.isNotBlank(ret)) {
                //重定向
                response.sendRedirect(ret);
            } else {
                //重定向
                response.sendRedirect("/admin/index.page");
            }
            return;
        }

        request.setAttribute("error", errorMsg);
        request.setAttribute("username", username);
        if (StringUtils.isNotBlank(ret)) {
            request.setAttribute("ret", ret);
        }
        //登录页面
        String path = "/signin.jsp";
        request.getRequestDispatcher(path).forward(request, response);
        log.warn("==================login-end");
    }


    @RequestMapping("/logout.page")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //移除session
        request.getSession().invalidate();
        String path = "/signin.jsp";
        //重定向到登录
        response.sendRedirect(path);
    }
}
