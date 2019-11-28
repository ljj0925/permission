package com.test.common;

import com.test.sys.entity.SysUser;

import javax.servlet.http.HttpServletRequest;

/** ThreadLocal类
 * @author
 * @create 2019-11-24 21:12
 */
public class RequestHolder {

    //存放用户信息
    private static ThreadLocal<SysUser> userHolder = new ThreadLocal<>();

    private static ThreadLocal<HttpServletRequest> RequestHolder = new ThreadLocal<>();



    //添加
    public static void add(SysUser sysUser){
        userHolder.set(sysUser);
    }

    public static void add(HttpServletRequest request){
        RequestHolder.set(request);
    }

    //获取
    public static SysUser getCurrentUser(){
        return userHolder.get();
    }

    public static HttpServletRequest getCurrentRequest(){
        return RequestHolder.get();
    }

    //进程结束的时候移除，防止内存溢出
    public static void remove(){
        userHolder.remove();
        RequestHolder.remove();
    }
}
