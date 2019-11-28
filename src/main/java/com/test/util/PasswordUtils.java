package com.test.util;

import java.util.Date;
import java.util.Random;

/** 生成随机密码
 * @author
 * @create 2019-11-24 14:37
 */
public class PasswordUtils {

    //生成密码范围
    public final static String[] word = {
            "a", "b", "c", "d", "e", "f", "g",
            "h", "j", "k", "m", "n",
            "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z",
            "A", "B", "C", "D", "E", "F", "G",
            "H", "J", "K", "M", "N",
            "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"
    };

    public final static String[] num = {
            "2", "3", "4", "5", "6", "7", "8", "9"
    };

    public static String randomPassword() {
        StringBuffer buffer = new StringBuffer();
        Random random = new Random(new Date().getTime());
        boolean flag = false;
        //生成密码长度
        int length = random.nextInt(3) + 8;
        for (int i = 0; i < length; i++) {
            if (flag) {
                buffer.append(num[random.nextInt(num.length)]);
            } else {
                buffer.append(word[random.nextInt(word.length)]);
            }
            //flag取反，保证密码是字符数字混合
            flag = !flag;
        }
        return buffer.toString();
    }

}
