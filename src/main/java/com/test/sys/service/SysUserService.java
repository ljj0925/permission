package com.test.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.test.beans.PageQuery;
import com.test.beans.PageResult;
import com.test.common.JsonData;
import com.test.sys.entity.SysUser;
import com.test.sys.param.UserParam;
import com.test.util.JsonResult;

/**
 *
 * @Description:  部门——SERVICE
 * @Author:       
 * @CreateDate:   2019-11-22T11:45:47.509Z
 * @Version:      V1.0
 */
public interface SysUserService extends IService<SysUser> {

	//分页条件查询
	public PageResult<SysUser> getPageByDeptId(Integer deptId, PageQuery pageQuery);

	//用户手机号或邮箱查询用户是否存在
	SysUser findByKeyword(String keyword);

	//新增用户
	JsonData save(UserParam param);

	//更新用户
	JsonData update(UserParam param);

	/**
	 *	查询部门信息
	 */
	JsonResult getSysUserById(Integer id);

	/**
	*	添加部门信息
	*/
	JsonResult insertSelective(SysUser sysUser);

	/**
	*	删除部门信息
	*/
	JsonResult deleteByPrimaryKey(Integer id);

	/**
	*	修改部门信息
	*/
	JsonResult updateByPrimaryKeySelective(SysUser sysUser);

	/**
	*	分页条件查询部门信息
	*/
	JsonResult getSysUserList(Integer pageNum, Integer pageSize, SysUser sysUser);

}