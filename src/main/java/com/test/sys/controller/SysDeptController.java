package com.test.sys.controller;

import com.test.common.JsonData;
import com.test.dto.DeptLevelDto;
import com.test.sys.entity.SysDept;
import com.test.sys.param.DeptParam;
import com.test.sys.service.SysDeptService;
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

import java.util.List;

/**
 *
 * @Description:  部门接口层
 * @Author:
 * @CreateDate:   2019-11-22T13:55:39.466Z
 * @Version:      V1.0
 */
@RestController
@RequestMapping("/sys/dept")
@Api(tags ={"部门"})
public class SysDeptController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SysDeptService SysDeptService;
	@Autowired
	private SysTreeService sysTreeService;


	/**
	 * 请求页面
	 * @return
	 */
	@RequestMapping("/dept.page")
	public ModelAndView page(){
		return new ModelAndView("dept");
	}


	/**
	 * 获取部门，封装为树结构
	 * @return
	 */
	@RequestMapping("/tree.json")
	public JsonData tree(){
		List<DeptLevelDto> dtoList = sysTreeService.deptTree();
		return JsonData.success(dtoList);
	}


	/**
	 * @explain 添加部门信息
	 * @param   sysDept 对象
	 * @return  JsonResult
	 * @author
	 * @time    2019-11-22T13:55:39.466Z
	 */
	@PostMapping("/save.json")
	@ApiOperation(value = "添加部门", notes = "添加部门[sysDept],Auth：")
	public JsonData insertSelective(DeptParam sysDept){
	    return SysDeptService.insertSelective(sysDept);
	}


	/**
	 * 更新部门树
	 * @return
	 */
	@RequestMapping("/update.json")
	public JsonData updateDept(DeptParam param){
		return SysDeptService.updateDept(param);
	}

	/**
	 * @explain 查询部门信息  <swagger GET请求>
	 * @param   部门id Long
	 * @return  JsonResult[sysDept]
	 * @author
	 * @time    2019-11-22T13:55:39.466Z
	 */
	@GetMapping("/getSysDeptById")
	@ApiOperation(value = "id查询部门", notes = "id查询部门[sysDept]，Auth：")
	@ApiImplicitParam(paramType="path", name = "id", value = "部门id", required = true, dataType = "Long")
	public JsonResult getSysDeptById(Long id){
		return SysDeptService.getSysDeptById(id);
	}




	/**
	 * @explain 删除部门信息
	 * @param   部门id Long
	 * @return  JsonResult
	 * @author  
	 * @time    2019-11-22T13:55:39.466Z
	 */
	@GetMapping("/deleteByPrimaryKey")
	@ApiOperation(value = "删除部门", notes = "删除部门,Auth：")
	@ApiImplicitParam(paramType="query", name = "id", value = "部门id", required = true, dataType = "Long")
	public JsonResult deleteByPrimaryKey(Long id){
		return SysDeptService.deleteByPrimaryKey(id);
	}


	/**
	 * @explain 修改部门信息
	 * @param   sysDept 对象
	 * @return  JsonResult
	 * @author  
	 * @time    2019-11-22T13:55:39.466Z
	 */
	@ApiOperation(value = "修改部门", notes = "修改部门[sysDept],Auth：")
	@PostMapping("/updateByPrimaryKeySelective")
	public JsonResult updateByPrimaryKeySelective(SysDept sysDept){
		return SysDeptService.updateByPrimaryKeySelective(sysDept);
	}


	/**
	 * @explain 分页条件查询部门信息
	 * @param   AppPage<SysDept>
	 * @return  JsonResult[PageInfo<SysDept>]
	 * @author  
	 * @time    2019-11-22T13:55:39.466Z
	 */
	@GetMapping("/getPageSysDept")
	@ApiOperation(value = "分页条件查询", notes = "分页查询返回JsonResult[PageInfo<SysDept>],Auth：")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "pageNum", value = "当前页", required = true, dataType = "int"),
        @ApiImplicitParam(paramType="query", name = "pageSize", value = "页行数", required = true, dataType = "int")
    })
	public JsonResult getSysDeptBySearch(Integer pageNum,Integer pageSize,SysDept sysDept){
		return SysDeptService.getSysDeptList(pageNum,pageSize,sysDept);
	}
}