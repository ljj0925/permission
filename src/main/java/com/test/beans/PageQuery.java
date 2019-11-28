package com.test.beans;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

/** 分页查询封装请求参数
 * @author
 * @create 2019-11-24 18:41
 */
public class PageQuery {

    @Getter
    @Setter
    @Min(value = 1, message = "当前页码不合法")
    private Integer pageNo = 1;

    @Getter
    @Setter
    @Min(value = 1, message = "每页展示数量不合法")
    private Integer pageSize = 10;

    @Setter
    private Integer offset;

    public int getOffset() {
        return (pageNo - 1) * pageSize;
    }
}
