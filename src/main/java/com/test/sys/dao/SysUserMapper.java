package com.test.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.beans.PageQuery;
import com.test.sys.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 部门——DAO
 * @Author:
 * @CreateDate: 2019-11-22T11:45:47.509Z
 * @Version: V1.0
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<SysUser> getPageByDeptId(@Param("deptId") Integer depgId, @Param("page")PageQuery page);

}
