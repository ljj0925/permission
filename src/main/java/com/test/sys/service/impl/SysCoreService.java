package com.test.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.test.common.RequestHolder;
import com.test.sys.dao.SysAclDao;
import com.test.sys.dao.SysRoleAclDao;
import com.test.sys.dao.SysRoleUserDao;
import com.test.sys.entity.SysAcl;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 获取权限信息
 * @author
 * @create 2019-11-25 16:44
 */
@Service
public class SysCoreService {

    @Autowired
    private SysAclDao sysAclDao;
    @Autowired
    private SysRoleUserDao sysRoleUserDao;
    @Autowired
    private SysRoleAclDao sysRoleAclDao;


    /**
     * 获取当前请求的用户ID
     * @return
     */
    public List<SysAcl> getCurrentUserAcList() {
        Integer userId = RequestHolder.getCurrentUser().getId();
        return getUserAclList(userId);
    }


    /**
     *  根据角色ID roleId查询权限表ID集合
     * @param roleId
     * @return
     */
    public List<SysAcl> getRoleAclList(int roleId) {
        //根据角色ID list查询权限表
        List<Integer> aclIdList = sysRoleAclDao.getAclIdListByRoleIdList(Lists.<Integer>newArrayList(roleId));
        if (CollectionUtils.isEmpty(aclIdList)){
            return Lists.newArrayList();
        }
        //根据用户ID获取用户权限表信息
        return sysAclDao.getByIdList(aclIdList);
    }


    /**
     * 根据用户ID获取用户权限表ID集合
     */
    public List<SysAcl> getUserAclList(int userId) {
        //判断用户是否是超级管理员
        if (isSuperAdmin()){
            //查询所有权限表信息
            return sysAclDao.selectList(new QueryWrapper<SysAcl>());
        }
        //根据用户ID查询已分配的角色id 集合
        List<Integer> userRoleIdList = sysRoleUserDao.getRoleIdListByUserId(userId);
        if (CollectionUtils.isNotEmpty(userRoleIdList)){
            //如果没有分配任何角色，返回空
            return Lists.newArrayList();
        }

        //根据角色ID查询用户角色权限中间表信息
        List<Integer> userAclIdList = sysRoleAclDao.getAclIdListByRoleIdList(userRoleIdList);
        if (CollectionUtils.isEmpty(userAclIdList)){
            return Lists.newArrayList();
        }

        //根据角色权限中间表信息查询权限表
        return sysAclDao.getByIdList(userAclIdList);

    }


    /**
     * 判断用户时都是超级管理员
     */
    public boolean isSuperAdmin() {
        return true;
    }
}
