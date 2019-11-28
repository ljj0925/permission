package com.test.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**   
 *  
 * @Description:  部门
 * @Author:          
 * @CreateDate:   2019-11-22T13:55:39.466Z
 * @Version:      V1.0
 *    
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysDept implements Serializable {

	private static final long serialVersionUID = 1574430939465L;
	
	@ApiModelProperty(name = "id" , value = "注释ID")
	@TableField(value = "id")
	private Long id;

	@TableField(value = "level")
	private String level;

	@TableField(value = "name")
	private String name;

	@TableField(value = "operator")
	private String operator;

	@TableField(value = "operator_ip")
	private String operatorIp;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@TableField(value = "operator_time")
	private Date operatorTime;

	@TableField(value = "parent_id")
	private Integer parentId;

	@ApiModelProperty(name = "remark" , value = "备注")
	@TableField(value = "remark")
	private String remark;

	@TableField(value = "seq")
	private Integer seq;

}
