package com.test.common;

import com.test.sys.dao.SysRequestLogDao;
import com.test.sys.entity.SysRequestLog;
import com.test.sys.entity.SysUser;
import com.test.util.IpUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * @author
 * @create 2019-11-26 18:04
 */
@Aspect
@Component
public class LogsAspect {

    Logger log = LoggerFactory.getLogger(LogsAspect.class);


    @Autowired
    private SysRequestLogDao sysRequestLogDao;

    /**
     * Pointcut
     * 定义Pointcut，Pointcut名称为aspectjMethod,必须无参，无返回值
     * 只是一个标识，并不进行调用
     * 切点我这儿定义的是为controller包下的所有类，所有方法都加，
     * 你可以指定具体的类或具体的方法
     * !execution(* com.test.sys.controller.*.do*(..))
     */
    @Pointcut("execution(* com.test.sys.controller.*.*(..)) && !execution(* com.test.sys.controller.*.log*(..))")
    private void aspectJMethod() {
    }


    @Before("aspectJMethod()")
    public void doBefore(JoinPoint joinPoint) {
        //log.warn("----dobefore()开始----");
        //log.warn("dobefore()执行业务逻辑前做一些工作");
        //log.warn("dobefore()通过jointPoint获得所需内容");
        //log.warn("----dobefore()结束----");
    }


    @Around("aspectJMethod()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {

        SysRequestLog log = new SysRequestLog();

        StringBuilder paramBuilder = new StringBuilder();

        SysUser user = RequestHolder.getCurrentUser();
        HttpServletRequest request = RequestHolder.getCurrentRequest();

        //请求方法
        String method = request.getMethod();

        Map<String, String[]> parameterMap1 = request.getParameterMap();
        for (String s : parameterMap1.keySet()) {
            String parameter = request.getParameter(s);
            paramBuilder.append(s + "=" + parameter + ",");
        }

        //请求ip
        String ip = IpUtil.getRemoteIp(request);
        //请求路径
        String url = request.getRequestURL().toString();

        if (user != null) {
            //请求用户
            log.setRequestUser(user.getUsername());
        }
        log.setRequestMethod(method);
        log.setRequestUrl(url);
        log.setRequestIp(ip);
        log.setRequestParam(paramBuilder.toString());

        //核心逻辑
        Object retval = pjp.proceed();
        log.setReturnData(retval.toString());

        log.setRequestTime(new Date());

        sysRequestLogDao.insert(log);

        System.out.println("----doAround()结束----");

        return retval;

    }


    @After(value = "aspectJMethod()")
    public void doAfter(JoinPoint joinPoint) {
        //log.warn("doAfter()开始----");
    }


    @AfterReturning(value = "aspectJMethod()", returning = "retval")
    public void doReturn(JoinPoint joinPoint, String retval) {
        //log.warn("doReturn()开始");

    }


    @AfterThrowing(value = "aspectJMethod()", throwing = "e")
    public void doThrowing(JoinPoint joinPoint, Exception e) {
        //log.warn(" doThrowing：" + e.getMessage());
    }
}