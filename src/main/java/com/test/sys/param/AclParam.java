package com.test.sys.param;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author
 * @create 2019-11-25 13:56
 */
@Data
public class AclParam {

    private Long id;

    @NotBlank(message = "权限点名称不能为空")
    @Length(min = 2, max = 20, message = "权限点名称长度2-64字符之间")
    private String name;

    @NotNull(message = "必须制定权限模块")
    private Integer aclModuleId;

    @Length(min = 6, max = 100, message = "权限点url长度在6-256字符之间")
    private String url;

    @NotNull(message = "权限点类型不能为空")
    @Min(value = 1,message = "权限点类型不合法")
    @Max(value = 3,message = "权限点类型不合法")
    private Integer type;

    @NotNull(message = "权限模块展示顺序不能为空")
    private Integer seq;

    @NotNull(message = "权限点状态不能为空")
    @Min(value = 0, message = "权限点状态不合法")
    @Max(value = 1, message = "权限点状态不合法")
    private Integer status;

    @Length(max = 200,message = "权限点备注在200个字符内")
    private String remark;
}
