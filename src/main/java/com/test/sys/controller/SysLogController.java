package com.test.sys.controller;

import com.test.sys.entity.SysLog;
import com.test.sys.service.SysLogService;
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
 * @Description: 权限日志接口层
 * @Author:
 * @CreateDate: 2019-11-23T08:50:22.584Z
 * @Version: V1.0
 */
@RestController
@RequestMapping("/sysLog")
@Api(tags = {"权限日志"})
public class SysLogController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysLogService SysLogService;


    /**
     * @param 权限日志id Integer
     * @return JsonResult[sysLog]
     * @explain 查询权限日志信息  <swagger GET请求>
     * @author
     * @time 2019-11-23T08:50:22.584Z
     */
    @GetMapping("/getSysLogById")
    @ApiOperation(value = "id查询权限日志", notes = "id查询权限日志[sysLog]，Auth：")
    @ApiImplicitParam(paramType = "path", name = "id", value = "权限日志id", required = true, dataType = "Integer")
    public JsonResult getSysLogById(Integer id) {
        return SysLogService.getSysLogById(id);
    }


    /**
     * @param sysLog 对象
     * @return JsonResult
     * @explain 添加权限日志信息
     * @author
     * @time 2019-11-23T08:50:22.584Z
     */
    @PostMapping("/insertSelective")
    @ApiOperation(value = "添加权限日志", notes = "添加权限日志[sysLog],Auth：")
    public JsonResult insertSelective(SysLog sysLog) {
        return SysLogService.insertSelective(sysLog);
    }


    /**
     * @param 权限日志id Integer
     * @return JsonResult
     * @explain 删除权限日志信息
     * @author
     * @time 2019-11-23T08:50:22.584Z
     */
    @GetMapping("/deleteByPrimaryKey")
    @ApiOperation(value = "删除权限日志", notes = "删除权限日志,Auth：")
    @ApiImplicitParam(paramType = "query", name = "id", value = "权限日志id", required = true, dataType = "Integer")
    public JsonResult deleteByPrimaryKey(Integer id) {
        return SysLogService.deleteByPrimaryKey(id);
    }


    /**
     * @param sysLog 对象
     * @return JsonResult
     * @explain 修改权限日志信息
     * @author
     * @time 2019-11-23T08:50:22.584Z
     */
    @ApiOperation(value = "修改权限日志", notes = "修改权限日志[sysLog],Auth：")
    @PostMapping("/updateByPrimaryKeySelective")
    public JsonResult updateByPrimaryKeySelective(SysLog sysLog) {
        return SysLogService.updateByPrimaryKeySelective(sysLog);
    }


    /**
     * @param AppPage<SysLog>
     * @return JsonResult[PageInfo<SysLog>]
     * @explain 分页条件查询权限日志信息
     * @author
     * @time 2019-11-23T08:50:22.584Z
     */
    @GetMapping("/getPageSysLog")
    @ApiOperation(value = "分页条件查询", notes = "分页查询返回JsonResult[PageInfo<SysLog>],Auth：")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "当前页", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "页行数", required = true, dataType = "int")
    })
    public JsonResult getSysLogBySearch(Integer pageNum, Integer pageSize, SysLog sysLog) {
        return SysLogService.getSysLogList(pageNum, pageSize, sysLog);
    }
}