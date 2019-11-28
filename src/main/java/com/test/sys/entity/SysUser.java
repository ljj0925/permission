package com.test.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**   
 *  
 * @Description:  部门
 * @Author:          
 * @CreateDate:   2019-11-22T11:45:47.509Z
 * @Version:      V1.0
 *    
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUser extends Model<SysUser> implements Serializable {

	private static final long serialVersionUID = 1574423147506L;
	
	@TableField(value = "dept_id")
	private Integer deptId;

	@TableField(value = "id")
	private Integer id;

	@TableField(value = "mail")
	private String mail;

	@TableField(value = "operator")
	private String operator;

	@TableField(value = "operator_ip")
	private String operatorIp;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@TableField(value = "operator_time")
	private Date operatorTime;

	@TableField(value = "password")
	private String password;

	@TableField(value = "remark")
	private String remark;

	@TableField(value = "status")
	private Integer status;

	@TableField(value = "telephone")
	private String telephone;

	@TableField(value = "username")
	private String username;

}
