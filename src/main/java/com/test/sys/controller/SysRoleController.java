package com.test.sys.controller;

import com.test.common.JsonData;
import com.test.sys.entity.SysRole;
import com.test.sys.param.RoleParam;
import com.test.sys.service.SysRoleService;
import com.test.sys.service.impl.SysTreeService;
import com.test.util.JsonResult;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**   
 * 
 * @Description:  角色管理接口层
 * @Author:          
 * @CreateDate:   2019-11-25T07:44:34.554Z
 * @Version:      V1.0
 */
@RestController
@RequestMapping("/sys/role")
@Api(tags ={"角色管理"})
public class SysRoleController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SysRoleService SysRoleService;
	@Autowired
	private SysTreeService sysTreeService;



	@RequestMapping("/role.page")
	public ModelAndView page(RoleParam aclParam) {
		return  new ModelAndView("role");
	}


	@RequestMapping("/save.json")
	public JsonData insertSelective(RoleParam aclParam) {
		return  SysRoleService.save(aclParam);
	}


	@RequestMapping("/update.json")
	public JsonData updateDept(RoleParam aclParam) {
		return SysRoleService.update(aclParam);
	}


	@RequestMapping("/list.json")
	public JsonData list() {
		return SysRoleService.getAll();
	}


	@RequestMapping("/roleTree.json")
	public JsonData roleTree(@RequestParam("roleId")int roleId){
		return JsonData.success(sysTreeService.roleTree(roleId));
	}



	/**
	 * @explain 查询角色管理信息  <swagger GET请求>
	 * @param   角色管理id Integer
	 * @return  JsonResult[sysRole]
	 * @author  
	 * @time    2019-11-25T07:44:34.554Z
	 */
	@GetMapping("/getSysRoleById")
	@ApiOperation(value = "id查询角色管理", notes = "id查询角色管理[sysRole]，Auth：")
	@ApiImplicitParam(paramType="path", name = "id", value = "角色管理id", required = true, dataType = "Integer")
	public JsonResult getSysRoleById(Integer id){
		return SysRoleService.getSysRoleById(id);
	}


	/**
	 * @explain 添加角色管理信息
	 * @param   sysRole 对象
	 * @return  JsonResult
	 * @author  
	 * @time    2019-11-25T07:44:34.554Z
	 */
	@PostMapping("/insertSelective")
	@ApiOperation(value = "添加角色管理", notes = "添加角色管理[sysRole],Auth：")
	public JsonResult insertSelective(SysRole sysRole){
	return SysRoleService.insertSelective(sysRole);
	}


	/**
	 * @explain 删除角色管理信息
	 * @param   角色管理id Integer
	 * @return  JsonResult
	 * @author  
	 * @time    2019-11-25T07:44:34.554Z
	 */
	@GetMapping("/deleteByPrimaryKey")
	@ApiOperation(value = "删除角色管理", notes = "删除角色管理,Auth：")
	@ApiImplicitParam(paramType="query", name = "id", value = "角色管理id", required = true, dataType = "Integer")
	public JsonResult deleteByPrimaryKey(Integer id){
		return SysRoleService.deleteByPrimaryKey(id);
	}


	/**
	 * @explain 修改角色管理信息
	 * @param   sysRole 对象
	 * @return  JsonResult
	 * @author  
	 * @time    2019-11-25T07:44:34.554Z
	 */
	@ApiOperation(value = "修改角色管理", notes = "修改角色管理[sysRole],Auth：")
	@PostMapping("/updateByPrimaryKeySelective")
	public JsonResult updateByPrimaryKeySelective(SysRole sysRole){
		return SysRoleService.updateByPrimaryKeySelective(sysRole);
	}


	/**
	 * @explain 分页条件查询角色管理信息
	 * @param   AppPage<SysRole>
	 * @return  JsonResult[PageInfo<SysRole>]
	 * @author  
	 * @time    2019-11-25T07:44:34.554Z
	 */
	@GetMapping("/getPageSysRole")
	@ApiOperation(value = "分页条件查询", notes = "分页查询返回JsonResult[PageInfo<SysRole>],Auth：")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "pageNum", value = "当前页", required = true, dataType = "int"),
        @ApiImplicitParam(paramType="query", name = "pageSize", value = "页行数", required = true, dataType = "int")
    })
	public JsonResult getSysRoleBySearch(Integer pageNum,Integer pageSize,SysRole sysRole){
		return SysRoleService.getSysRoleList(pageNum,pageSize,sysRole);
	}
}