package com.test.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**   
 *  
 * @Description:  角色-用户管理
 * @Author:          
 * @CreateDate:   2019-11-25T08:55:00.747Z
 * @Version:      V1.0
 *    
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRoleUser extends Model<SysRoleUser> implements Serializable {

	private static final long serialVersionUID = 1574672100746L;
	
	@ApiModelProperty(name = "id" , value = "表id")
	@TableField(value = "id")
	private Long id;

	@ApiModelProperty(name = "roleId" , value = "角色表id")
	@TableField(value = "role_id")
	private Integer roleId;

	@ApiModelProperty(name = "userId" , value = "用户表id")
	@TableField(value = "user_id")
	private Integer userId;

	@ApiModelProperty(name = "operator" , value = "最后一次操作人")
	@TableField(value = "operator")
	private String operator;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	@ApiModelProperty(name = "operatorTime" , value = "最后一次操时间")
	@TableField(value = "operator_time")
	private Date operatorTime;

	@ApiModelProperty(name = "operatorIp" , value = "最后一次操ip")
	@TableField(value = "operator_ip")
	private String operatorIp;

}
