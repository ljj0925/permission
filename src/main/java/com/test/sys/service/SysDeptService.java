package com.test.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.test.common.JsonData;
import com.test.dto.DeptLevelDto;
import com.test.sys.entity.SysDept;
import com.test.sys.param.DeptParam;
import com.test.util.JsonResult;

import java.util.List;

/**
 *
 * @Description:  部门——SERVICE
 * @Author:       
 * @CreateDate:   2019-11-22T13:55:39.466Z
 * @Version:      V1.0
 */
public interface SysDeptService extends IService<SysDept> {


	/**
	 *	查询部门信息
	 */
	JsonResult getSysDeptById(Long id);

	/**
	*	添加部门信息
	*/
	JsonData insertSelective(DeptParam sysDept);

	/**
	*	删除部门信息
	*/
	JsonResult deleteByPrimaryKey(Long id);

	/**
	*	修改部门信息
	*/
	JsonResult updateByPrimaryKeySelective(SysDept sysDept);

	/**
	*	分页条件查询部门信息
	*/
	JsonResult getSysDeptList(Integer pageNum, Integer pageSize, SysDept sysDept);

	/**
	 * 获取所有部门
	 * @return
	 */
	List<DeptLevelDto> getAllDept();

	/**
	 * 更新部门树
	 * @param param
	 */
	JsonData updateDept(DeptParam param);
}