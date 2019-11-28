package com.test.sys.controller;

import com.test.beans.PageQuery;
import com.test.common.JsonData;
import com.test.sys.entity.SysAcl;
import com.test.sys.param.AclParam;
import com.test.sys.service.SysAclService;
import com.test.util.JsonResult;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 权限模块表接口层
 * @Author:
 * @CreateDate: 2019-11-25T05:42:24.716Z
 * @Version: V1.0
 */
@RestController
@RequestMapping("/sys/acl")
@Api(tags = {"权限模块表"})
public class SysAclController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysAclService SysAclService;



    @RequestMapping("/save.json")
    public JsonData insertSelective(AclParam aclParam) {
        return  SysAclService.save(aclParam);
    }



    @RequestMapping("/update.json")
    public JsonData updateDept(AclParam aclParam) {
        SysAclService.update(aclParam);
        return JsonData.success();
    }


    @RequestMapping("/page.json")
    public JsonData list(@Param("aclModuleId") Integer aclModuleId, PageQuery pageQuery) {
        return JsonData.success(SysAclService.getPageByAclModuleId(aclModuleId,pageQuery));
    }


    /**
     * @param 权限模块表id Long
     * @return JsonResult[sysAcl]
     * @explain 查询权限模块表信息  <swagger GET请求>
     * @author
     * @time 2019-11-25T05:42:24.716Z
     */
    @GetMapping("/getSysAclById")
    @ApiOperation(value = "id查询权限模块表", notes = "id查询权限模块表[sysAcl]，Auth：")
    @ApiImplicitParam(paramType = "path", name = "id", value = "权限模块表id", required = true, dataType = "Long")
    public JsonResult getSysAclById(Long id) {
        return SysAclService.getSysAclById(id);
    }


    /**
     * @param sysAcl 对象
     * @return JsonResult
     * @explain 添加权限模块表信息
     * @author
     * @time 2019-11-25T05:42:24.716Z
     */
    @PostMapping("/insertSelective")
    @ApiOperation(value = "添加权限模块表", notes = "添加权限模块表[sysAcl],Auth：")
    public JsonResult insertSelective(SysAcl sysAcl) {
        return SysAclService.insertSelective(sysAcl);
    }


    /**
     * @param 权限模块表id Long
     * @return JsonResult
     * @explain 删除权限模块表信息
     * @author
     * @time 2019-11-25T05:42:24.716Z
     */
    @GetMapping("/deleteByPrimaryKey")
    @ApiOperation(value = "删除权限模块表", notes = "删除权限模块表,Auth：")
    @ApiImplicitParam(paramType = "query", name = "id", value = "权限模块表id", required = true, dataType = "Long")
    public JsonResult deleteByPrimaryKey(Long id) {
        return SysAclService.deleteByPrimaryKey(id);
    }


    /**
     * @param sysAcl 对象
     * @return JsonResult
     * @explain 修改权限模块表信息
     * @author
     * @time 2019-11-25T05:42:24.716Z
     */
    @ApiOperation(value = "修改权限模块表", notes = "修改权限模块表[sysAcl],Auth：")
    @PostMapping("/updateByPrimaryKeySelective")
    public JsonResult updateByPrimaryKeySelective(SysAcl sysAcl) {
        return SysAclService.updateByPrimaryKeySelective(sysAcl);
    }


    /**
     * @param AppPage<SysAcl>
     * @return JsonResult[PageInfo<SysAcl>]
     * @explain 分页条件查询权限模块表信息
     * @author
     * @time 2019-11-25T05:42:24.716Z
     */
    @GetMapping("/getPageSysAcl")
    @ApiOperation(value = "分页条件查询", notes = "分页查询返回JsonResult[PageInfo<SysAcl>],Auth：")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "当前页", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "页行数", required = true, dataType = "int")
    })
    public JsonResult getSysAclBySearch(Integer pageNum, Integer pageSize, SysAcl sysAcl) {
        return SysAclService.getSysAclList(pageNum, pageSize, sysAcl);
    }
}