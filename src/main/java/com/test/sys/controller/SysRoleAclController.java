package com.test.sys.controller;

import com.test.sys.entity.SysRoleAcl;
import com.test.sys.service.SysRoleAclService;
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
 * @Description:  角色-权限关系管理接口层
 * @Author:          
 * @CreateDate:   2019-11-25T09:04:30.746Z
 * @Version:      V1.0
 */
@RestController
@RequestMapping("/sysRoleAcl")
@Api(tags ={"角色-权限关系管理"})
public class SysRoleAclController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SysRoleAclService SysRoleAclService;





	/**
	 * @explain 查询角色-权限关系管理信息  <swagger GET请求>
	 * @param   角色-权限关系管理id Long
	 * @return  JsonResult[sysRoleAcl]
	 * @author  
	 * @time    2019-11-25T09:04:30.746Z
	 */
	@GetMapping("/getSysRoleAclById")
	@ApiOperation(value = "id查询角色-权限关系管理", notes = "id查询角色-权限关系管理[sysRoleAcl]，Auth：")
	@ApiImplicitParam(paramType="path", name = "id", value = "角色-权限关系管理id", required = true, dataType = "Long")
	public JsonResult getSysRoleAclById(Long id){
		return SysRoleAclService.getSysRoleAclById(id);
	}


	/**
	 * @explain 添加角色-权限关系管理信息
	 * @param   sysRoleAcl 对象
	 * @return  JsonResult
	 * @author  
	 * @time    2019-11-25T09:04:30.746Z
	 */
	@PostMapping("/insertSelective")
	@ApiOperation(value = "添加角色-权限关系管理", notes = "添加角色-权限关系管理[sysRoleAcl],Auth：")
	public JsonResult insertSelective(SysRoleAcl sysRoleAcl){
	return SysRoleAclService.insertSelective(sysRoleAcl);
	}


	/**
	 * @explain 删除角色-权限关系管理信息
	 * @param   角色-权限关系管理id Long
	 * @return  JsonResult
	 * @author  
	 * @time    2019-11-25T09:04:30.746Z
	 */
	@GetMapping("/deleteByPrimaryKey")
	@ApiOperation(value = "删除角色-权限关系管理", notes = "删除角色-权限关系管理,Auth：")
	@ApiImplicitParam(paramType="query", name = "id", value = "角色-权限关系管理id", required = true, dataType = "Long")
	public JsonResult deleteByPrimaryKey(Long id){
		return SysRoleAclService.deleteByPrimaryKey(id);
	}


	/**
	 * @explain 修改角色-权限关系管理信息
	 * @param   sysRoleAcl 对象
	 * @return  JsonResult
	 * @author  
	 * @time    2019-11-25T09:04:30.746Z
	 */
	@ApiOperation(value = "修改角色-权限关系管理", notes = "修改角色-权限关系管理[sysRoleAcl],Auth：")
	@PostMapping("/updateByPrimaryKeySelective")
	public JsonResult updateByPrimaryKeySelective(SysRoleAcl sysRoleAcl){
		return SysRoleAclService.updateByPrimaryKeySelective(sysRoleAcl);
	}


	/**
	 * @explain 分页条件查询角色-权限关系管理信息
	 * @param   AppPage<SysRoleAcl>
	 * @return  JsonResult[PageInfo<SysRoleAcl>]
	 * @author  
	 * @time    2019-11-25T09:04:30.746Z
	 */
	@GetMapping("/getPageSysRoleAcl")
	@ApiOperation(value = "分页条件查询", notes = "分页查询返回JsonResult[PageInfo<SysRoleAcl>],Auth：")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "pageNum", value = "当前页", required = true, dataType = "int"),
        @ApiImplicitParam(paramType="query", name = "pageSize", value = "页行数", required = true, dataType = "int")
    })
	public JsonResult getSysRoleAclBySearch(Integer pageNum,Integer pageSize,SysRoleAcl sysRoleAcl){
		return SysRoleAclService.getSysRoleAclList(pageNum,pageSize,sysRoleAcl);
	}
}