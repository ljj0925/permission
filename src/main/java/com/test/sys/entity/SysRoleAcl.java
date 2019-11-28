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
 * @Description:  角色-权限关系管理
 * @Author:          
 * @CreateDate:   2019-11-25T09:04:30.746Z
 * @Version:      V1.0
 *    
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRoleAcl extends Model<SysRoleAcl> implements Serializable {

	private static final long serialVersionUID = 1574672670744L;
	
	@TableField(value = "id")
	private Long id;

	@ApiModelProperty(name = "roleId" , value = "角色表id")
	@TableField(value = "role_id")
	private Integer roleId;

	@ApiModelProperty(name = "aclId" , value = "权限表id")
	@TableField(value = "acl_id")
	private Integer aclId;

	@TableField(value = "operator")
	private String operator;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	@TableField(value = "operator_time")
	private Date operatorTime;

	@TableField(value = "operator_ip")
	private String operatorIp;

}
