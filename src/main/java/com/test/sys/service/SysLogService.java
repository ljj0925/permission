package com.test.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.test.sys.entity.SysLog;
import com.test.util.JsonResult;

/**
 * @Description: 权限日志——SERVICE
 * @Author:
 * @CreateDate: 2019-11-23T08:50:22.584Z
 * @Version: V1.0
 */
public interface SysLogService extends IService<SysLog> {


    /**
     * 查询权限日志信息
     */
    JsonResult getSysLogById(Integer id);

    /**
     * 添加权限日志信息
     */
    JsonResult insertSelective(SysLog sysLog);

    /**
     * 删除权限日志信息
     */
    JsonResult deleteByPrimaryKey(Integer id);

    /**
     * 修改权限日志信息
     */
    JsonResult updateByPrimaryKeySelective(SysLog sysLog);

    /**
     * 分页条件查询权限日志信息
     */
    JsonResult getSysLogList(Integer pageNum, Integer pageSize, SysLog sysLog);

	/**
	 *
	 * @param type	类型：1部门 2用户 3权限模块 4权限 5角色 6角色用户关系 7角色权限关系
	 * @param targetId	表的主键ID
	 * @param oldValue	修改前数据
	 * @param newValue	修改后的值
	 * @param operator	最后一次操作人
	 * @param operatorIp	最后一次操作ip
	 * @param status	当前是否恢复过：0未使用该记录复原过 1复原过
	 */
    void saveSysLog(Integer type, Integer targetId, String oldValue, String newValue, String operator, String operatorIp,
			   Integer status);

}