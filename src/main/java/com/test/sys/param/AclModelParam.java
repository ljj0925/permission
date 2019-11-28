package com.test.sys.param;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author
 * @create 2019-11-24 22:27
 */
@Data
public class AclModelParam {

    private Long id;

    @NotBlank(message = "权限模块名称不能为空")
    @Length(min = 2, max = 64, message = "权限名称长度2-64字符之间")
    private String name;

    private Integer parentId = 0;

    private String level;

    @NotNull(message = "权限模块展示顺序不能为空")
    private Integer seq;

    @NotNull(message = "权限模块状态不能为空")
    @Min(value = 0, message = "权限模块状态不合法")
    @Max(value = 1, message = "权限模块状态不合法")
    private Integer status;

    @Length(message = "权限模块备注在200个字符内")
    private String remark;
}
