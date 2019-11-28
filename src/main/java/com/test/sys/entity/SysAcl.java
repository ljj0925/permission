package com.test.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 权限模块表
 * @Author:
 * @CreateDate: 2019-11-25T05:42:24.716Z
 * @Version: V1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")	//两个类作比较使用id字段，如果id相同就是同一个类
public class SysAcl extends Model<SysAcl> implements Serializable {

    private static final long serialVersionUID = 1574660544712L;

    @ApiModelProperty(name = "id", value = "权限表id")
    @TableField(value = "id")
    private Long id;

    @ApiModelProperty(name = "code", value = "权限码")
    @TableField(value = "code")
    private String code;

    @ApiModelProperty(name = "name", value = "权限名称")
    @TableField(value = "name")
    private String name;

    @ApiModelProperty(name = "aclModuleId", value = "权限模块id")
    @TableField(value = "acl_module_id")
    private Integer aclModuleId;

    @ApiModelProperty(name = "url", value = "url")
    @TableField(value = "url")
    private String url;

    @ApiModelProperty(name = "type", value = "类型：1菜单2按钮3其他")
    @TableField(value = "type")
    private Integer type;

    @ApiModelProperty(name = "status", value = "状态：0冻结1正常")
    @TableField(value = "status")
    private Integer status;

    @ApiModelProperty(name = "seq", value = "当前层级排序")
    @TableField(value = "seq")
    private Integer seq;

    @ApiModelProperty(name = "operator", value = "最后一次操作人")
    @TableField(value = "operator")
    private String operator;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(name = "operatorTime", value = "最后一次操作时间")
    @TableField(value = "operator_time")
    private Date operatorTime;

    @ApiModelProperty(name = "operatorIp", value = "最后一次操作ip")
    @TableField(value = "operator_ip")
    private String operatorIp;

    @ApiModelProperty(name = "remark", value = "备注")
    @TableField(value = "remark")
    private String remark;

}
