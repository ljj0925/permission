package com.test.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.test.beans.PageQuery;
import com.test.beans.PageResult;
import com.test.common.JsonData;
import com.test.sys.entity.SysAcl;
import com.test.sys.param.AclParam;
import com.test.util.JsonResult;

/**
 *
 * @Description:  权限模块表——SERVICE
 * @Author:       
 * @CreateDate:   2019-11-25T05:42:24.716Z
 * @Version:      V1.0
 */
public interface SysAclService extends IService<SysAcl> {


	PageResult<SysAcl> getPageByAclModuleId(Integer aclModuleId, PageQuery pageQuery);

	JsonData save(AclParam aclParam);

	void update(AclParam aclParam);

	/**
	 *	查询权限模块表信息
	 */
	JsonResult getSysAclById(Long id);

	/**
	*	添加权限模块表信息
	*/
	JsonResult insertSelective(SysAcl sysAcl);

	/**
	*	删除权限模块表信息
	*/
	JsonResult deleteByPrimaryKey(Long id);

	/**
	*	修改权限模块表信息
	*/
	JsonResult updateByPrimaryKeySelective(SysAcl sysAcl);

	/**
	*	分页条件查询权限模块表信息
	*/
	JsonResult getSysAclList(Integer pageNum, Integer pageSize, SysAcl sysAcl);

}