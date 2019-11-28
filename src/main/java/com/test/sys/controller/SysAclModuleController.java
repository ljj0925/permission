package com.test.sys.controller;

import com.test.common.JsonData;
import com.test.sys.entity.SysAclModule;
import com.test.sys.param.AclModelParam;
import com.test.sys.service.SysAclModuleService;
import com.test.sys.service.impl.SysTreeService;
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
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description: 权限模块表接口层
 * @Author:
 * @CreateDate: 2019-11-25T00:54:11.722Z
 * @Version: V1.0
 */
@RestController
@RequestMapping("/sys/aclModule")
@Api(tags = {"权限模块表"})
public class SysAclModuleController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysAclModuleService SysAclModuleService;
    @Autowired
	private SysTreeService sysTreeService;


    /**
     * 请求页面
     *
     * @return
     */
    @RequestMapping("/acl.page")
    public ModelAndView page() {
        return new ModelAndView("acl");
    }



	@RequestMapping("/tree.json")
	public JsonData tree(){
		return JsonData.success(sysTreeService.aclModuleTree());
	}


	@PostMapping("/save.json")
    @ApiOperation(value = "添加部门", notes = "添加部门[sysDept],Auth：")
    public JsonData saveAcl(AclModelParam param) {
        return SysAclModuleService.saveAcl(param);
    }


    /**
     * 更新权限模块
     *
     * @return
     */
    @RequestMapping("/update.json")
    public JsonData updateAcl(AclModelParam param) {
        return SysAclModuleService.updateAcl(param);
    }


    /**
     * @param 权限模块表id Integer
     * @return JsonResult[sysAclModule]
     * @explain 查询权限模块表信息  <swagger GET请求>
     * @author
     * @time 2019-11-25T00:54:11.722Z
     */
    @GetMapping("/getSysAclModuleById")
    @ApiOperation(value = "id查询权限模块表", notes = "id查询权限模块表[sysAclModule]，Auth：")
    @ApiImplicitParam(paramType = "path", name = "id", value = "权限模块表id", required = true, dataType = "Integer")
    public JsonResult getSysAclModuleById(Integer id) {
        return SysAclModuleService.getSysAclModuleById(id);
    }


    /**
     * @param sysAclModule 对象
     * @return JsonResult
     * @explain 添加权限模块表信息
     * @author
     * @time 2019-11-25T00:54:11.722Z
     */
    @PostMapping("/insertSelective")
    @ApiOperation(value = "添加权限模块表", notes = "添加权限模块表[sysAclModule],Auth：")
    public JsonResult insertSelective(SysAclModule sysAclModule) {
        return SysAclModuleService.insertSelective(sysAclModule);
    }


    /**
     * @param 权限模块表id Integer
     * @return JsonResult
     * @explain 删除权限模块表信息
     * @author
     * @time 2019-11-25T00:54:11.722Z
     */
    @GetMapping("/deleteByPrimaryKey")
    @ApiOperation(value = "删除权限模块表", notes = "删除权限模块表,Auth：")
    @ApiImplicitParam(paramType = "query", name = "id", value = "权限模块表id", required = true, dataType = "Integer")
    public JsonResult deleteByPrimaryKey(Integer id) {
        return SysAclModuleService.deleteByPrimaryKey(id);
    }


    /**
     * @param sysAclModule 对象
     * @return JsonResult
     * @explain 修改权限模块表信息
     * @author
     * @time 2019-11-25T00:54:11.722Z
     */
    @ApiOperation(value = "修改权限模块表", notes = "修改权限模块表[sysAclModule],Auth：")
    @PostMapping("/updateByPrimaryKeySelective")
    public JsonResult updateByPrimaryKeySelective(SysAclModule sysAclModule) {
        return SysAclModuleService.updateByPrimaryKeySelective(sysAclModule);
    }


    /**
     * @param AppPage<SysAclModule>
     * @return JsonResult[PageInfo<SysAclModule>]
     * @explain 分页条件查询权限模块表信息
     * @author
     * @time 2019-11-25T00:54:11.722Z
     */
    @GetMapping("/getPageSysAclModule")
    @ApiOperation(value = "分页条件查询", notes = "分页查询返回JsonResult[PageInfo<SysAclModule>],Auth：")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "当前页", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "页行数", required = true, dataType = "int")
    })
    public JsonResult getSysAclModuleBySearch(Integer pageNum, Integer pageSize, SysAclModule sysAclModule) {
        return SysAclModuleService.getSysAclModuleList(pageNum, pageSize, sysAclModule);
    }
}