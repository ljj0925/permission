package com.test.beans;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/** 封装分页查询返回数据
 * @author
 * @create 2019-11-24 18:41
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {

    private List<T> data = Lists.newArrayList();

    private Integer total = 0;

}
