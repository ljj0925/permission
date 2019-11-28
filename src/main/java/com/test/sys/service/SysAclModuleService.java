package com.test.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.test.common.JsonData;
import com.test.sys.entity.SysAclModule;
import com.test.sys.param.AclModelParam;
import com.test.util.JsonResult;

/**
 *
 * @Description:  权限模块表——SERVICE
 * @Author:       
 * @CreateDate:   2019-11-25T00:54:11.722Z
 * @Version:      V1.0
 */
public interface SysAclModuleService extends IService<SysAclModule> {


	JsonData saveAcl(AclModelParam param);

	JsonData updateAcl(AclModelParam param);

	/**
	 *	查询权限模块表信息
	 */
	JsonResult getSysAclModuleById(Integer id);

	/**
	*	添加权限模块表信息
	*/
	JsonResult insertSelective(SysAclModule sysAclModule);

	/**
	*	删除权限模块表信息
	*/
	JsonResult deleteByPrimaryKey(Integer id);

	/**
	*	修改权限模块表信息
	*/
	JsonResult updateByPrimaryKeySelective(SysAclModule sysAclModule);

	/**
	*	分页条件查询权限模块表信息
	*/
	JsonResult getSysAclModuleList(Integer pageNum, Integer pageSize, SysAclModule sysAclModule);

}