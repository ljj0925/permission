package com.test.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.sys.dao.SysRequestLogDao;
import com.test.sys.entity.SysRequestLog;
import com.test.sys.service.SysRequestLogService;
import com.test.util.BeanValidator;
import com.test.util.JsonResult;
import com.test.util.retMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


/**   
 *  
 * @Description:  系统访问日志-serviceImpl
 * @Author:          
 * @CreateDate:   2019-11-26T11:57:06.602Z
 * @Version:      V1.0
 */
@Service
public class SysRequestLogServiceImpl extends ServiceImpl<SysRequestLogDao, SysRequestLog> implements SysRequestLogService {


	@Autowired
	private SysRequestLogDao sysRequestLogDao;


	/**
	*	查询系统访问日志信息
	*/
	@Override
	public JsonResult getSysRequestLogById(Long id) {

		if (id == null) {
			return JsonResult.error(retMsg.PARAM_ID_ERROR);
		}

		SysRequestLog sysRequestLog = sysRequestLogDao.selectById(id);
		if (sysRequestLog == null) {
			return JsonResult.success(1, retMsg.QUERY_NODATA);
		}
		return JsonResult.success(sysRequestLog);
	}


	/**
	*	添加系统访问日志信息
	*/
	@Override
	public JsonResult insertSelective(SysRequestLog sysRequestLog) {

		Map<String, String> map = BeanValidator.validate(sysRequestLog);
		if (!map.isEmpty()) {
			return JsonResult.error(retMsg.PARAM_ERROR, map);
		}

		//TODO:设置需要后端生成的参数，例如修改日期等

		int ret = sysRequestLogDao.insert(sysRequestLog);
		if (ret > 0) {
			return JsonResult.success();
		}
		return JsonResult.error(retMsg.INSERT_FAILED);
	}


	/**
	*	删除系统访问日志信息
	*/
	@Override
	public JsonResult deleteByPrimaryKey(Long id) {

		if (id == null) {
			return JsonResult.error(retMsg.PARAM_ID_ERROR);
		}

		int ret = sysRequestLogDao.deleteById(id);
		if (ret > 0) {
			return JsonResult.success();
		}
		return JsonResult.error();
	}


	/**
	*	修改系统访问日志信息
	*/
	@Override
	public JsonResult updateByPrimaryKeySelective(SysRequestLog sysRequestLog) {

		if (sysRequestLog.getId() == null){
			return JsonResult.error(retMsg.PARAM_ID_ERROR);
		}

		int ret = sysRequestLogDao.updateById(sysRequestLog);
		if(ret > 0){
			return JsonResult.success();
		}
		return JsonResult.error();
	}


	/**
	*	分页条件查询系统访问日志信息
	*/
	@Override
	public JsonResult getSysRequestLogList(Integer pageNum, Integer pageSize, SysRequestLog sysRequestLog) {

		QueryWrapper<SysRequestLog> wrapper = new QueryWrapper<>();
		wrapper.setEntity(sysRequestLog);

		Page<SysRequestLog> page = new Page<>(pageNum == null ? 1 : pageNum,pageSize == null ? 10 : pageSize);
		IPage<SysRequestLog> pageInfo = sysRequestLogDao.selectPage(page, wrapper);
		return JsonResult.success(pageInfo);
	}

}