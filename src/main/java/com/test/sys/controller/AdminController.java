package com.test.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author
 * @create 2019-11-24 16:16
 */
@RequestMapping("/admin")
@Controller
public class AdminController {

    /**
     * 跳转到权限管理页面
     * @return
     */
    @RequestMapping("index.page")
    public ModelAndView index(){
        return new ModelAndView("admin");
    }
}
