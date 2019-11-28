package com.test.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Preconditions;
import com.test.common.JsonData;
import com.test.common.RequestHolder;
import com.test.exception.ParamException;
import com.test.sys.dao.SysAclModuleDao;
import com.test.sys.entity.SysAclModule;
import com.test.sys.param.AclModelParam;
import com.test.sys.service.SysAclModuleService;
import com.test.util.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**   
 *  
 * @Description:  权限模块表-serviceImpl
 * @Author:          
 * @CreateDate:   2019-11-25T00:54:11.722Z
 * @Version:      V1.0
 */
@Service
public class SysAclModuleServiceImpl extends ServiceImpl<SysAclModuleDao, SysAclModule> implements SysAclModuleService {


	@Autowired
	private SysAclModuleDao sysAclModuleDao;


	@Override
	public JsonData saveAcl(AclModelParam param) {
		Map<String, String> validate = BeanValidator.validate(param);
		if (!validate.isEmpty()){
			throw new ParamException(validate.toString());
		}

		Map<String, Object> map = new HashMap<>(1);
		map.put("name",param.getName());
		List<SysAclModule> sysAclModules = sysAclModuleDao.selectByMap(map);
		if (sysAclModules.size() > 0){
			throw new ParamException("同一层级下存在相同名称的权限模块");
		}

		SysAclModule sysAclModule = new SysAclModule();
		BeanUtils.copyProperties(param,sysAclModule);
		sysAclModule.setLevel(LevelUtil.calculateLevel(getLevel(param.getParentId()), Long.parseLong(param.getParentId() + "")));
		sysAclModule.setOperator(RequestHolder.getCurrentUser().getUsername());
		sysAclModule.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
		sysAclModule.setOperatorTime(new Date());

		int ret = sysAclModuleDao.insert(sysAclModule);
		if (ret <= 0){
			return JsonData.fail();
		}

		return JsonData.success();
	}

	private String getLevel(Integer sysAclModuleId) {
		SysAclModule sysAclModule = sysAclModuleDao.selectById(sysAclModuleId);
		if (sysAclModule == null) {
			return null;
		}
		return sysAclModule.getLevel();
	}

	@Override
	public JsonData updateAcl(AclModelParam param) {
		Map<String, String> validate = BeanValidator.validate(param);
		if (!validate.isEmpty()){
			throw new ParamException(validate.toString());
		}

		Map<String, Object> map = new HashMap<>(1);
		map.put("name",param.getName());
		List<SysAclModule> sysAclModules = sysAclModuleDao.selectByMap(map);
		if (sysAclModules.size() > 0){
			throw new ParamException("同一层级下存在相同名称的权限模块");
		}

		//待更新数据是否存在，不存在抛出异常
		SysAclModule befor = sysAclModuleDao.selectById(param.getId());
		Preconditions.checkNotNull(befor,"待更新权限模块不存在");

		SysAclModule after = new SysAclModule();
		BeanUtils.copyProperties(param,after);
		after.setLevel(LevelUtil.calculateLevel(getLevel(param.getParentId()), Long.parseLong(param.getParentId() + "")));
		after.setOperator(RequestHolder.getCurrentUser().getUsername());
		after.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
		after.setOperatorTime(new Date());

		updateWithChild(befor,after);

		return JsonData.success();
	}

	//关联更新所有子模块，开启事务
	@Transactional
	public void updateWithChild(SysAclModule befor,SysAclModule after){
		String afterLevel = after.getLevel();
		String beforLevel = befor.getLevel();
		if (!afterLevel.equals(beforLevel)) {
			List<SysAclModule> aclMudleList = sysAclModuleDao.getChilAclModuleListByLevel(beforLevel);
			if (CollectionUtils.isNotEmpty(aclMudleList)) {
				for (SysAclModule aclmodel : aclMudleList) {
					String level = aclmodel.getLevel();

					if (level.indexOf(beforLevel) == 0) {
						level = afterLevel + level.substring(beforLevel.length());
						aclmodel.setLevel(level);
					}
				}
				sysAclModuleDao.batchUpdateLevel(aclMudleList);
			}
		}
		sysAclModuleDao.updateById(after);
	}


	/**
	*	查询权限模块表信息
	*/
	@Override
	public JsonResult getSysAclModuleById(Integer id) {

		if (id == null) {
			return JsonResult.error(retMsg.PARAM_ID_ERROR);
		}

		SysAclModule sysAclModule = sysAclModuleDao.selectById(id);
		if (sysAclModule == null) {
			return JsonResult.success(1, retMsg.QUERY_NODATA);
		}
		return JsonResult.success(sysAclModule);
	}


	/**
	*	添加权限模块表信息
	*/
	@Override
	public JsonResult insertSelective(SysAclModule sysAclModule) {

		Map<String, String> map = BeanValidator.validate(sysAclModule);
		if (!map.isEmpty()) {
			return JsonResult.error(retMsg.PARAM_ERROR, map);
		}

		//TODO:设置需要后端生成的参数，例如修改日期等

		int ret = sysAclModuleDao.insert(sysAclModule);
		if (ret > 0) {
			return JsonResult.success();
		}
		return JsonResult.error(retMsg.INSERT_FAILED);
	}


	/**
	*	删除权限模块表信息
	*/
	@Override
	public JsonResult deleteByPrimaryKey(Integer id) {

		if (id == null) {
			return JsonResult.error(retMsg.PARAM_ID_ERROR);
		}

		int ret = sysAclModuleDao.deleteById(id);
		if (ret > 0) {
			return JsonResult.success();
		}
		return JsonResult.error();
	}


	/**
	*	修改权限模块表信息
	*/
	@Override
	public JsonResult updateByPrimaryKeySelective(SysAclModule sysAclModule) {

		if (sysAclModule.getId() == null){
			return JsonResult.error(retMsg.PARAM_ID_ERROR);
		}

		int ret = sysAclModuleDao.updateById(sysAclModule);
		if(ret > 0){
			return JsonResult.success();
		}
		return JsonResult.error();
	}


	/**
	*	分页条件查询权限模块表信息
	*/
	@Override
	public JsonResult getSysAclModuleList(Integer pageNum, Integer pageSize, SysAclModule sysAclModule) {

		QueryWrapper<SysAclModule> wrapper = new QueryWrapper<>();
		wrapper.setEntity(sysAclModule);

		Page<SysAclModule> page = new Page<>(pageNum == null ? 1 : pageNum,pageSize == null ? 10 : pageSize);
		IPage<SysAclModule> pageInfo = sysAclModuleDao.selectPage(page, wrapper);
		return JsonResult.success(pageInfo);
	}

}