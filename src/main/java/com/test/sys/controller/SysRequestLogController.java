package com.test.sys.controller;

import com.test.sys.entity.SysRequestLog;
import com.test.sys.service.SysRequestLogService;
import com.test.util.JsonResult;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**   
 * 
 * @Description:  系统访问日志接口层
 * @Author:          
 * @CreateDate:   2019-11-26T11:57:06.602Z
 * @Version:      V1.0
 */
@RestController
@RequestMapping("/sysRequestLog")
@Api(tags ={"系统访问日志"})
public class SysRequestLogController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SysRequestLogService SysRequestLogService;





	/**
	 * @explain 查询系统访问日志信息  <swagger GET请求>
	 * @param   系统访问日志id Long
	 * @return  JsonResult[sysRequestLog]
	 * @author  
	 * @time    2019-11-26T11:57:06.602Z
	 */
	@GetMapping("/getSysRequestLogById")
	@ApiOperation(value = "id查询系统访问日志", notes = "id查询系统访问日志[sysRequestLog]，Auth：")
	@ApiImplicitParam(paramType="path", name = "id", value = "系统访问日志id", required = true, dataType = "Long")
	public JsonResult getSysRequestLogById(Long id){
		return SysRequestLogService.getSysRequestLogById(id);
	}


	/**
	 * @explain 添加系统访问日志信息
	 * @param   sysRequestLog 对象
	 * @return  JsonResult
	 * @author  
	 * @time    2019-11-26T11:57:06.602Z
	 */
	@PostMapping("/insertSelective")
	@ApiOperation(value = "添加系统访问日志", notes = "添加系统访问日志[sysRequestLog],Auth：")
	public JsonResult insertSelective(SysRequestLog sysRequestLog){
	return SysRequestLogService.insertSelective(sysRequestLog);
	}


	/**
	 * @explain 删除系统访问日志信息
	 * @param   系统访问日志id Long
	 * @return  JsonResult
	 * @author  
	 * @time    2019-11-26T11:57:06.602Z
	 */
	@GetMapping("/deleteByPrimaryKey")
	@ApiOperation(value = "删除系统访问日志", notes = "删除系统访问日志,Auth：")
	@ApiImplicitParam(paramType="query", name = "id", value = "系统访问日志id", required = true, dataType = "Long")
	public JsonResult deleteByPrimaryKey(Long id){
		return SysRequestLogService.deleteByPrimaryKey(id);
	}


	/**
	 * @explain 修改系统访问日志信息
	 * @param   sysRequestLog 对象
	 * @return  JsonResult
	 * @author  
	 * @time    2019-11-26T11:57:06.602Z
	 */
	@ApiOperation(value = "修改系统访问日志", notes = "修改系统访问日志[sysRequestLog],Auth：")
	@PostMapping("/updateByPrimaryKeySelective")
	public JsonResult updateByPrimaryKeySelective(SysRequestLog sysRequestLog){
		return SysRequestLogService.updateByPrimaryKeySelective(sysRequestLog);
	}


	/**
	 * @explain 分页条件查询系统访问日志信息
	 * @param   AppPage<SysRequestLog>
	 * @return  JsonResult[PageInfo<SysRequestLog>]
	 * @author  
	 * @time    2019-11-26T11:57:06.602Z
	 */
	@GetMapping("/getPageSysRequestLog")
	@ApiOperation(value = "分页条件查询", notes = "分页查询返回JsonResult[PageInfo<SysRequestLog>],Auth：")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "pageNum", value = "当前页", required = true, dataType = "int"),
        @ApiImplicitParam(paramType="query", name = "pageSize", value = "页行数", required = true, dataType = "int")
    })
	public JsonResult getSysRequestLogBySearch(Integer pageNum,Integer pageSize,SysRequestLog sysRequestLog){
		return SysRequestLogService.getSysRequestLogList(pageNum,pageSize,sysRequestLog);
	}
}