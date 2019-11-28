package com.test.common;

import com.test.exception.ParamException;
import com.test.exception.PermissionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理类
 * 需要配置到spring IOC中（springmvc.xml）
 *
 * @author
 * @create 2019-11-17 13:29
 */
@Slf4j
public class GolableExceptionResolver implements HandlerExceptionResolver {


    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        String url = request.getRequestURL().toString();

        ModelAndView mv;
        //未知异常返回信息
        String defaultMsg = "System error";
        //请求规范：如果发送json请求，则请求url.json结尾，如果是请求页面则.page结尾
        if (url.endsWith(".json")) {
            //json请求
            //PermissionException自定义异常或者请求参数校验未通过的处理方式
            if (ex instanceof PermissionException || ex instanceof ParamException) {
                //属于自定义异常
                JsonData result = JsonData.fail(ex.getMessage());
                //封装异常信息，返回json数据
                mv = new ModelAndView("jsonView", result.toMap());

                //异常记录日志
                log.error(" Permission、Param exception,url:" + url, ex);

            } else {
                //未知异常，返回json类型默认msg
                JsonData result = JsonData.fail(defaultMsg);
                mv = new ModelAndView("jsonView", result.toMap());

                //异常记录日志
                log.error("unknow exception,url:" + url, ex);
            }
        } else if (url.endsWith(".page")) {
            //页面请求出现异常，返回exception.jsp页面
            JsonData result = JsonData.fail(defaultMsg);
            mv = new ModelAndView("exception", result.toMap());

            //异常记录日志
            log.error("unknow exception,url:" + url, ex);
        } else {
            //既不是json请求，也不是page请求
            JsonData result = JsonData.fail(defaultMsg);
            mv = new ModelAndView("jsonView", result.toMap());

            //异常记录日志
            log.error("unknow exception,url:" + url, ex);
        }
        return mv;
    }
}
