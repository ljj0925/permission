package com.test.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.sys.entity.SysDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 部门——DAO
 * @Author:
 * @CreateDate: 2019-11-22T13:55:39.466Z
 * @Version: V1.0
 */

public interface SysDeptDao extends BaseMapper<SysDept> {

    List<SysDept> getChilDeptListByLevel(@Param("level")String level);

    void batchUpdateLevel(@Param("sysDeptList")List<SysDept> sysDepts);

}
