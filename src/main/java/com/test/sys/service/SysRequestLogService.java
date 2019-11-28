package com.test.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.test.sys.entity.SysRequestLog;
import com.test.util.JsonResult;

/**
 *
 * @Description:  系统访问日志——SERVICE
 * @Author:       
 * @CreateDate:   2019-11-26T11:57:06.602Z
 * @Version:      V1.0
 */
public interface SysRequestLogService extends IService<SysRequestLog> {


	/**
	 *	查询系统访问日志信息
	 */
	JsonResult getSysRequestLogById(Long id);

	/**
	*	添加系统访问日志信息
	*/
	JsonResult insertSelective(SysRequestLog sysRequestLog);

	/**
	*	删除系统访问日志信息
	*/
	JsonResult deleteByPrimaryKey(Long id);

	/**
	*	修改系统访问日志信息
	*/
	JsonResult updateByPrimaryKeySelective(SysRequestLog sysRequestLog);

	/**
	*	分页条件查询系统访问日志信息
	*/
	JsonResult getSysRequestLogList(Integer pageNum, Integer pageSize, SysRequestLog sysRequestLog);

}