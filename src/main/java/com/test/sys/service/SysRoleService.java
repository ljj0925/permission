package com.test.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.test.common.JsonData;
import com.test.sys.entity.SysRole;
import com.test.sys.param.RoleParam;
import com.test.util.JsonResult;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 *
 * @Description:  角色管理——SERVICE
 * @Author:       
 * @CreateDate:   2019-11-25T07:44:34.554Z
 * @Version:      V1.0
 */
public interface SysRoleService extends IService<SysRole> {


	List roleTree(@RequestParam("roleId")int roleId);

	JsonData save(RoleParam param);

	JsonData update(RoleParam param);

	JsonData getAll();
	/**
	 *	查询角色管理信息
	 */
	JsonResult getSysRoleById(Integer id);

	/**
	*	添加角色管理信息
	*/
	JsonResult insertSelective(SysRole sysRole);

	/**
	*	删除角色管理信息
	*/
	JsonResult deleteByPrimaryKey(Integer id);

	/**
	*	修改角色管理信息
	*/
	JsonResult updateByPrimaryKeySelective(SysRole sysRole);

	/**
	*	分页条件查询角色管理信息
	*/
	JsonResult getSysRoleList(Integer pageNum, Integer pageSize, SysRole sysRole);

}