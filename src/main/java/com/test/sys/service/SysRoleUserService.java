package com.test.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.test.sys.entity.SysRoleUser;
import com.test.util.JsonResult;

/**
 *
 * @Description:  角色-用户管理——SERVICE
 * @Author:       
 * @CreateDate:   2019-11-25T08:55:00.747Z
 * @Version:      V1.0
 */
public interface SysRoleUserService extends IService<SysRoleUser> {


	/**
	 *	查询角色-用户管理信息
	 */
	JsonResult getSysRoleUserById(Long id);

	/**
	*	添加角色-用户管理信息
	*/
	JsonResult insertSelective(SysRoleUser sysRoleUser);

	/**
	*	删除角色-用户管理信息
	*/
	JsonResult deleteByPrimaryKey(Long id);

	/**
	*	修改角色-用户管理信息
	*/
	JsonResult updateByPrimaryKeySelective(SysRoleUser sysRoleUser);

	/**
	*	分页条件查询角色-用户管理信息
	*/
	JsonResult getSysRoleUserList(Integer pageNum, Integer pageSize, SysRoleUser sysRoleUser);

}