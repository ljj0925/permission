package com.test.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 要使用本拦截器，需要在spring配置文件中加入（springmvc.xml）
 *
 * @author
 * @create 2019-11-17 18:26
 */
public class HttpInterceptor extends HandlerInterceptorAdapter {

    Logger log = LoggerFactory.getLogger(HttpInterceptor.class);



    /**
     * 请求进入controller之前调用
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.warn("===============preHandle");
        //请求url
        String url = request.getRequestURL().toString();
        log.warn("url:{}", url);

        //请求参数
        Map parameterMap = request.getParameterMap();
        for (Object o : parameterMap.keySet()) {
            log.warn("parameterMap:{}", o);
        }

        Map<String, String[]> parameterMap1 = request.getParameterMap();
        for (String s : parameterMap1.keySet()) {
            log.warn("parameterMap1:{}", s);
        }

        String parameter = request.getParameter("");
        log.warn("parameter:{}", parameter);

        //请求方式
        String method = request.getMethod();
        log.warn("method:{}", method);




        return true;
    }

    /**
     * 请求正常返回前调用
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.warn("===============postHandle");
        super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 请求正常或抛出异常都会调用
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.warn("===============afterCompletion");
        //移除ThreadLocal
        removeThreadLocal();
    }

    //当进程结束时，移除ThreadLocal数据
    public void removeThreadLocal(){
        RequestHolder.remove();
    }

}
