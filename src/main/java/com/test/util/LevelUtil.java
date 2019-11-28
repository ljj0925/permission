package com.test.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 本项目使用，计算部门层级关系
 *
 * @author
 * @create 2019-11-17 19:20
 */
public class LevelUtil {

    //层级分隔符
    public final static String SEPARATOR = ".";

    //首层ID
    public final static String ROOT = "0";

    //部门level计算规则
    /*0
     * 0.1
     * 0.1.1
     * */
    public static String calculateLevel(String parentLevel, Long parentId) {
        if (StringUtils.isBlank(parentLevel)) {
            return ROOT;
        } else {
            //字符串拼接
            return StringUtils.join(parentLevel, SEPARATOR, parentId);
        }
    }

}
