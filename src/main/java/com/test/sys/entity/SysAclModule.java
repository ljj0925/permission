package com.test.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**   
 *  
 * @Description:  权限模块表
 * @Author:          
 * @CreateDate:   2019-11-25T00:54:11.722Z
 * @Version:      V1.0
 *    
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysAclModule extends Model<SysAclModule> implements Serializable {

	private static final long serialVersionUID = 1574643251720L;
	
	@TableField(value = "id")
	private Long id;

	@TableField(value = "name")
	private String name;

	@TableField(value = "parent_id")
	private Integer parentId;

	@TableField(value = "level")
	private String level;

	@TableField(value = "seq")
	private Integer seq;

	@TableField(value = "status")
	private Integer status;

	@TableField(value = "operator")
	private String operator;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	@TableField(value = "operator_time")
	private Date operatorTime;

	@TableField(value = "operator_ip")
	private String operatorIp;

	@TableField(value = "remark")
	private String remark;

}
