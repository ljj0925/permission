package com.test.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 从spring容器中获取Bean
 * 需要配置到spring ioc容器中（ApplicationContextUtils.java），启动项目加载本类
 *
 * @author
 * @create 2019-11-17 17:22
 */
@Component("applicationContextUtils")
public class ApplicationContextUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    //加入spring容器后，启动会自动注入ApplicationContext到本方法
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    public static <T> T popBean(Class<T> clazz) {
        if (applicationContext == null) {
            return null;
        }
        return applicationContext.getBean(clazz);
    }

    public static <T> T popBean(String name, Class<T> clazz) {
        if (applicationContext == null) {
            return null;
        }
        return applicationContext.getBean(name, clazz);
    }
}
