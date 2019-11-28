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
 * @Description:  角色管理
 * @Author:          
 * @CreateDate:   2019-11-25T07:44:34.554Z
 * @Version:      V1.0
 *    
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRole extends Model<SysRole> implements Serializable {

	private static final long serialVersionUID = 1574667874553L;
	
	@ApiModelProperty(name = "id" , value = "角色id")
	@TableField(value = "id")
	private Integer id;

	@ApiModelProperty(name = "name" , value = "角色名")
	@TableField(value = "name")
	private String name;

	@ApiModelProperty(name = "type" , value = "角色类型：1管理员2其他")
	@TableField(value = "type")
	private Integer type;

	@ApiModelProperty(name = "status" , value = "状态：0冻结1正常")
	@TableField(value = "status")
	private Integer status;

	@ApiModelProperty(name = "operator" , value = "最后一次操作人")
	@TableField(value = "operator")
	private String operator;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	@ApiModelProperty(name = "operatorTime" , value = "最后一次操作时间")
	@TableField(value = "operator_time")
	private Date operatorTime;

	@ApiModelProperty(name = "operatorIp" , value = "最后一次操作ip")
	@TableField(value = "operator_ip")
	private String operatorIp;

	@ApiModelProperty(name = "remark" , value = "备注")
	@TableField(value = "remark")
	private String remark;

}
