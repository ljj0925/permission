package com.test.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.sys.entity.SysRoleAcl;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 角色-权限关系管理——DAO
 * @Author:
 * @CreateDate: 2019-11-25T09:04:30.746Z
 * @Version: V1.0
 */
public interface SysRoleAclDao extends BaseMapper<SysRoleAcl> {

    List<Integer> getAclIdListByRoleIdList(@Param("roleIdList") List<Integer> roleIdList);

}
