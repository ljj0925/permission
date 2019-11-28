package com.test.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/** 发送邮件数据封装类
 * @author
 * @create 2019-11-24 22:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mail {

    private String subject;

    private String message;

    private Set<String> receivers;


}
