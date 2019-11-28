package com.test.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
*
* @Description:  系统访问日志
* @Author:       
* @CreateDate:   2019-11-26T11:57:06.602Z
* @Version:      V1.0
*
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRequestLog extends Model<SysRequestLog> implements Serializable {

private static final long serialVersionUID = 1574769426600L;

    @TableField(value = "id")
    private Long id;


    private String requestMethod;

    @TableField(value = "request_ip")
    private String requestIp;

    @NotBlank(message = "请填写requestUser")
    @TableField(value = "request_user")
    private String requestUser;

    @TableField(value = "request_param")
    private String requestParam;

    @NotBlank(message = "请填写requestUrl")
    @TableField(value = "request_url")
    private String requestUrl;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @NotNull(message = "请填写requestTime")
    @TableField(value = "request_time")
    private Date requestTime;

    @TableField(value = "return_data")
    private String returnData;

}
