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
 * @Description:  权限日志
 * @Author:          
 * @CreateDate:   2019-11-23T08:50:22.584Z
 * @Version:      V1.0
 *    
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysLog extends Model<SysLog> implements Serializable {

	private static final long serialVersionUID = 1574499022582L;
	
	@TableField(value = "id")
	private Long id;

	@TableField(value = "new_value")
	private String newValue;

	@TableField(value = "old_value")
	private String oldValue;

	@TableField(value = "operator")
	private String operator;

	@TableField(value = "operator_ip")
	private String operatorIp;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	@TableField(value = "operator_time")
	private Date operatorTime;

	@TableField(value = "status")
	private Integer status;

	@TableField(value = "target_id")
	private Integer targetId;

	@TableField(value = "type")
	private Integer type;

}
