package com.test.sys.controller;

import com.test.sys.entity.SysRoleUser;
import com.test.sys.service.SysRoleUserService;
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
 * @Description:  角色-用户管理接口层
 * @Author:          
 * @CreateDate:   2019-11-25T08:55:00.747Z
 * @Version:      V1.0
 */
@RestController
@RequestMapping("/sysRoleUser")
@Api(tags ={"角色-用户管理"})
public class SysRoleUserController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SysRoleUserService SysRoleUserService;





	/**
	 * @explain 查询角色-用户管理信息  <swagger GET请求>
	 * @param   角色-用户管理id Long
	 * @return  JsonResult[sysRoleUser]
	 * @author  
	 * @time    2019-11-25T08:55:00.747Z
	 */
	@GetMapping("/getSysRoleUserById")
	@ApiOperation(value = "id查询角色-用户管理", notes = "id查询角色-用户管理[sysRoleUser]，Auth：")
	@ApiImplicitParam(paramType="path", name = "id", value = "角色-用户管理id", required = true, dataType = "Long")
	public JsonResult getSysRoleUserById(Long id){
		return SysRoleUserService.getSysRoleUserById(id);
	}


	/**
	 * @explain 添加角色-用户管理信息
	 * @param   sysRoleUser 对象
	 * @return  JsonResult
	 * @author  
	 * @time    2019-11-25T08:55:00.747Z
	 */
	@PostMapping("/insertSelective")
	@ApiOperation(value = "添加角色-用户管理", notes = "添加角色-用户管理[sysRoleUser],Auth：")
	public JsonResult insertSelective(SysRoleUser sysRoleUser){
	return SysRoleUserService.insertSelective(sysRoleUser);
	}


	/**
	 * @explain 删除角色-用户管理信息
	 * @param   角色-用户管理id Long
	 * @return  JsonResult
	 * @author  
	 * @time    2019-11-25T08:55:00.747Z
	 */
	@GetMapping("/deleteByPrimaryKey")
	@ApiOperation(value = "删除角色-用户管理", notes = "删除角色-用户管理,Auth：")
	@ApiImplicitParam(paramType="query", name = "id", value = "角色-用户管理id", required = true, dataType = "Long")
	public JsonResult deleteByPrimaryKey(Long id){
		return SysRoleUserService.deleteByPrimaryKey(id);
	}


	/**
	 * @explain 修改角色-用户管理信息
	 * @param   sysRoleUser 对象
	 * @return  JsonResult
	 * @author  
	 * @time    2019-11-25T08:55:00.747Z
	 */
	@ApiOperation(value = "修改角色-用户管理", notes = "修改角色-用户管理[sysRoleUser],Auth：")
	@PostMapping("/updateByPrimaryKeySelective")
	public JsonResult updateByPrimaryKeySelective(SysRoleUser sysRoleUser){
		return SysRoleUserService.updateByPrimaryKeySelective(sysRoleUser);
	}


	/**
	 * @explain 分页条件查询角色-用户管理信息
	 * @param   AppPage<SysRoleUser>
	 * @return  JsonResult[PageInfo<SysRoleUser>]
	 * @author  
	 * @time    2019-11-25T08:55:00.747Z
	 */
	@GetMapping("/getPageSysRoleUser")
	@ApiOperation(value = "分页条件查询", notes = "分页查询返回JsonResult[PageInfo<SysRoleUser>],Auth：")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "pageNum", value = "当前页", required = true, dataType = "int"),
        @ApiImplicitParam(paramType="query", name = "pageSize", value = "页行数", required = true, dataType = "int")
    })
	public JsonResult getSysRoleUserBySearch(Integer pageNum,Integer pageSize,SysRoleUser sysRoleUser){
		return SysRoleUserService.getSysRoleUserList(pageNum,pageSize,sysRoleUser);
	}
}