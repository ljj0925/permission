package com.test.sys.param;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 封装用户参数校验
 *
 * @author
 * @create 2019-11-24 13:35
 */
@Data
public class UserParam {


    private Integer id;

    @NotBlank(message = "用户名不能为空")
    @Length(min = 5, max = 20, message = "用户名长度在5-20之间")
    private String username;

    /*@NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 20, message = "密码长度在6-20之间")*/
    private String password;

    @NotBlank(message = "电话号码不能为空")
    @Length(min = 5, max = 13, message = "电话号码长度在13字符内")
    private String telephone;

    @NotBlank(message = "邮箱不能为空")
    @Length(min = 5, max = 50, message = "邮箱长度在5-20之间")
    private String mail;

    @NotNull(message = "用户所在部门不能为空")
    private Integer deptId;

    @NotNull(message = "用户状态不能为空")
    @Min(value = 0, message = "用户状态在0-2之间")
    @Max(value = 2, message = "用户状态在0-2之间")
    private Integer status;

    @Length(min = 0, max = 200, message = "用户备注信息需要在200字符内")
    private String remark;

}
