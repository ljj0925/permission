package com.test.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.sys.dao.SysRoleAclDao;
import com.test.sys.entity.SysRoleAcl;
import com.test.sys.service.SysRoleAclService;
import com.test.util.BeanValidator;
import com.test.util.JsonResult;
import com.test.util.retMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


/**   
 *  
 * @Description:  角色-权限关系管理-serviceImpl
 * @Author:          
 * @CreateDate:   2019-11-25T09:04:30.746Z
 * @Version:      V1.0
 */
@Service
public class SysRoleAclServiceImpl extends ServiceImpl<SysRoleAclDao, SysRoleAcl> implements SysRoleAclService {


	@Autowired
	private SysRoleAclDao sysRoleAclDao;


	/**
	*	查询角色-权限关系管理信息
	*/
	@Override
	public JsonResult getSysRoleAclById(Long id) {

		if (id == null) {
			return JsonResult.error(retMsg.PARAM_ID_ERROR);
		}

		SysRoleAcl sysRoleAcl = sysRoleAclDao.selectById(id);
		if (sysRoleAcl == null) {
			return JsonResult.success(1, retMsg.QUERY_NODATA);
		}
		return JsonResult.success(sysRoleAcl);
	}


	/**
	*	添加角色-权限关系管理信息
	*/
	@Override
	public JsonResult insertSelective(SysRoleAcl sysRoleAcl) {

		Map<String, String> map = BeanValidator.validate(sysRoleAcl);
		if (!map.isEmpty()) {
			return JsonResult.error(retMsg.PARAM_ERROR, map);
		}

		//TODO:设置需要后端生成的参数，例如修改日期等

		int ret = sysRoleAclDao.insert(sysRoleAcl);
		if (ret > 0) {
			return JsonResult.success();
		}
		return JsonResult.error(retMsg.INSERT_FAILED);
	}


	/**
	*	删除角色-权限关系管理信息
	*/
	@Override
	public JsonResult deleteByPrimaryKey(Long id) {

		if (id == null) {
			return JsonResult.error(retMsg.PARAM_ID_ERROR);
		}

		int ret = sysRoleAclDao.deleteById(id);
		if (ret > 0) {
			return JsonResult.success();
		}
		return JsonResult.error();
	}


	/**
	*	修改角色-权限关系管理信息
	*/
	@Override
	public JsonResult updateByPrimaryKeySelective(SysRoleAcl sysRoleAcl) {

		if (sysRoleAcl.getId() == null){
			return JsonResult.error(retMsg.PARAM_ID_ERROR);
		}

		int ret = sysRoleAclDao.updateById(sysRoleAcl);
		if(ret > 0){
			return JsonResult.success();
		}
		return JsonResult.error();
	}


	/**
	*	分页条件查询角色-权限关系管理信息
	*/
	@Override
	public JsonResult getSysRoleAclList(Integer pageNum, Integer pageSize, SysRoleAcl sysRoleAcl) {

		QueryWrapper<SysRoleAcl> wrapper = new QueryWrapper<>();
		wrapper.setEntity(sysRoleAcl);

		Page<SysRoleAcl> page = new Page<>(pageNum == null ? 1 : pageNum,pageSize == null ? 10 : pageSize);
		IPage<SysRoleAcl> pageInfo = sysRoleAclDao.selectPage(page, wrapper);
		return JsonResult.success(pageInfo);
	}

}