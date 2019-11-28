package com.test.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 分页查询工具类
 * @author
 * @create 2019-11-19 21:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppPage<T> {

    private Integer pageSize;

    private Integer pageNum;

    private T param;
}
