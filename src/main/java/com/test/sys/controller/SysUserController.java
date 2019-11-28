package com.test.sys.controller;

import com.test.beans.PageQuery;
import com.test.common.JsonData;
import com.test.sys.entity.SysUser;
import com.test.sys.param.UserParam;
import com.test.sys.service.SysUserService;
import com.test.util.JsonResult;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 部门接口层
 * @Author:
 * @CreateDate: 2019-11-22T11:45:47.509Z
 * @Version: V1.0
 */
@RestController
@RequestMapping("/sys/user")
@Api(tags = {"用户"})
public class SysUserController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysUserService SysUserService;


    /**
     * @param sysDept 对象
     * @return JsonResult
     * @explain 添加用户信息
     * @author
     * @time 2019-11-22T13:55:39.466Z
     */
    @PostMapping("/save.json")
    @ApiOperation(value = "添加用户", notes = "添加用户[sysDept],Auth：")
    public JsonData insertSelective(UserParam param) {
        return SysUserService.save(param);
    }


    @RequestMapping("/page.json")
    @ResponseBody
    public JsonData page(@RequestParam("deptId") Integer deptId, PageQuery pageQuery){
        return JsonData.success(SysUserService.getPageByDeptId(deptId, pageQuery));
    }


    /**
     * 更新用户
     *
     * @return
     */
    @RequestMapping("/update.json")
    public JsonData updateUser(UserParam param) {
        return SysUserService.update(param);
    }


    /**
     * @param 部门id Integer
     * @return JsonResult[sysUser]
     * @explain 查询部门信息  <swagger GET请求>
     * @author
     * @time 2019-11-22T11:45:47.509Z
     */
    @GetMapping("/getSysUserById")
    @ApiOperation(value = "id查询部门", notes = "id查询部门[sysUser]，Auth：")
    @ApiImplicitParam(paramType = "path", name = "id", value = "部门id", required = true, dataType = "Integer")
    public JsonResult getSysUserById(Integer id) {
        System.out.println("getSysUserById:" + id);
        return SysUserService.getSysUserById(id);
    }


    /**
     * @param sysUser 对象
     * @return JsonResult
     * @explain 添加部门信息
     * @author
     * @time 2019-11-22T11:45:47.509Z
     */
    @PostMapping("/insertSelective")
    @ApiOperation(value = "添加部门", notes = "添加部门[sysUser],Auth：")
    public JsonResult insertSelective(SysUser sysUser) {
        System.out.println("insertSelective:" + sysUser);
        return SysUserService.insertSelective(sysUser);
    }


    /**
     * @param 部门id Integer
     * @return JsonResult
     * @explain 删除部门信息
     * @author
     * @time 2019-11-22T11:45:47.509Z
     */
    @GetMapping("/deleteByPrimaryKey")
    @ApiOperation(value = "删除部门", notes = "删除部门,Auth：")
    @ApiImplicitParam(paramType = "query", name = "id", value = "部门id", required = true, dataType = "Integer")
    public JsonResult deleteByPrimaryKey(Integer id) {
        System.out.println("deleteByPrimaryKey:" + id);
        return SysUserService.deleteByPrimaryKey(id);
    }


    /**
     * @param sysUser 对象
     * @return JsonResult
     * @explain 修改部门信息
     * @author
     * @time 2019-11-22T11:45:47.509Z
     */
    @ApiOperation(value = "修改部门", notes = "修改部门[sysUser],Auth：")
    @PostMapping("/updateByPrimaryKeySelective")
    public JsonResult updateByPrimaryKeySelective(SysUser sysUser) {
        System.out.println("updateByPrimaryKeySelective:" + sysUser);
        return SysUserService.updateByPrimaryKeySelective(sysUser);
    }


    /**
     * @param AppPage<SysUser>
     * @return JsonResult[PageInfo<SysUser>]
     * @explain 分页条件查询部门信息
     * @author
     * @time 2019-11-22T11:45:47.509Z
     */
    @PostMapping("/getPageSysUser")
    @ApiOperation(value = "分页条件查询", notes = "分页查询返回JsonResult[PageInfo<SysUser>],Auth：")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "当前页", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "页行数", required = true, dataType = "int")
    })
    public JsonResult getSysUserBySearch(Integer pageNum, Integer pageSize, SysUser sysUser) {
        System.out.println("getSysUserBySearch:pageNum:" + pageNum + ",pageSize:" + pageSize + ",SysUser:" + sysUser);
        return SysUserService.getSysUserList(pageNum, pageSize, sysUser);
    }
}