package com.test.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.test.sys.entity.SysRoleAcl;
import com.test.util.JsonResult;

/**
 *
 * @Description:  角色-权限关系管理——SERVICE
 * @Author:       
 * @CreateDate:   2019-11-25T09:04:30.746Z
 * @Version:      V1.0
 */
public interface SysRoleAclService extends IService<SysRoleAcl> {


	/**
	 *	查询角色-权限关系管理信息
	 */
	JsonResult getSysRoleAclById(Long id);

	/**
	*	添加角色-权限关系管理信息
	*/
	JsonResult insertSelective(SysRoleAcl sysRoleAcl);

	/**
	*	删除角色-权限关系管理信息
	*/
	JsonResult deleteByPrimaryKey(Long id);

	/**
	*	修改角色-权限关系管理信息
	*/
	JsonResult updateByPrimaryKeySelective(SysRoleAcl sysRoleAcl);

	/**
	*	分页条件查询角色-权限关系管理信息
	*/
	JsonResult getSysRoleAclList(Integer pageNum, Integer pageSize, SysRoleAcl sysRoleAcl);

}