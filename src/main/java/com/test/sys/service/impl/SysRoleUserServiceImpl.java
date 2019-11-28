package com.test.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.sys.dao.SysRoleUserDao;
import com.test.sys.entity.SysRoleUser;
import com.test.sys.service.SysRoleUserService;
import com.test.util.BeanValidator;
import com.test.util.JsonResult;
import com.test.util.retMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


/**   
 *  
 * @Description:  角色-用户管理-serviceImpl
 * @Author:          
 * @CreateDate:   2019-11-25T08:55:00.747Z
 * @Version:      V1.0
 */
@Service
public class SysRoleUserServiceImpl extends ServiceImpl<SysRoleUserDao, SysRoleUser> implements SysRoleUserService {


	@Autowired
	private SysRoleUserDao sysRoleUserDao;


	/**
	*	查询角色-用户管理信息
	*/
	@Override
	public JsonResult getSysRoleUserById(Long id) {

		if (id == null) {
			return JsonResult.error(retMsg.PARAM_ID_ERROR);
		}

		SysRoleUser sysRoleUser = sysRoleUserDao.selectById(id);
		if (sysRoleUser == null) {
			return JsonResult.success(1, retMsg.QUERY_NODATA);
		}
		return JsonResult.success(sysRoleUser);
	}


	/**
	*	添加角色-用户管理信息
	*/
	@Override
	public JsonResult insertSelective(SysRoleUser sysRoleUser) {

		Map<String, String> map = BeanValidator.validate(sysRoleUser);
		if (!map.isEmpty()) {
			return JsonResult.error(retMsg.PARAM_ERROR, map);
		}

		//TODO:设置需要后端生成的参数，例如修改日期等

		int ret = sysRoleUserDao.insert(sysRoleUser);
		if (ret > 0) {
			return JsonResult.success();
		}
		return JsonResult.error(retMsg.INSERT_FAILED);
	}


	/**
	*	删除角色-用户管理信息
	*/
	@Override
	public JsonResult deleteByPrimaryKey(Long id) {

		if (id == null) {
			return JsonResult.error(retMsg.PARAM_ID_ERROR);
		}

		int ret = sysRoleUserDao.deleteById(id);
		if (ret > 0) {
			return JsonResult.success();
		}
		return JsonResult.error();
	}


	/**
	*	修改角色-用户管理信息
	*/
	@Override
	public JsonResult updateByPrimaryKeySelective(SysRoleUser sysRoleUser) {

		if (sysRoleUser.getId() == null){
			return JsonResult.error(retMsg.PARAM_ID_ERROR);
		}

		int ret = sysRoleUserDao.updateById(sysRoleUser);
		if(ret > 0){
			return JsonResult.success();
		}
		return JsonResult.error();
	}


	/**
	*	分页条件查询角色-用户管理信息
	*/
	@Override
	public JsonResult getSysRoleUserList(Integer pageNum, Integer pageSize, SysRoleUser sysRoleUser) {

		QueryWrapper<SysRoleUser> wrapper = new QueryWrapper<>();
		wrapper.setEntity(sysRoleUser);

		Page<SysRoleUser> page = new Page<>(pageNum == null ? 1 : pageNum,pageSize == null ? 10 : pageSize);
		IPage<SysRoleUser> pageInfo = sysRoleUserDao.selectPage(page, wrapper);
		return JsonResult.success(pageInfo);
	}

}