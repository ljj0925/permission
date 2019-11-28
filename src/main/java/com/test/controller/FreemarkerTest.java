package com.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Freemarker演示
 *
 * @author
 * @create 2019-11-18 19:56
 */
@Controller
public class FreemarkerTest {

    private static Logger LOG = LoggerFactory.getLogger(FreemarkerTest.class);



    @RequestMapping("/freemarker")
    public String sayHello(Model model) {
        LOG.error("fdsfdfdf");
        throw new RuntimeException();
        //model.addAttribute("name", "hello");
        //return "hello";
    }


    @RequestMapping("/hi")
    public String sayHi(Model model) {
        int i = 0;
        int j = 89 / i;
        model.addAttribute("name", "hi");
        return "hi";
    }


}
