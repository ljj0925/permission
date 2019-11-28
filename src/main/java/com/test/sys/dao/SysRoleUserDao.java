package com.test.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.sys.entity.SysRoleUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**   
 *  
 * @Description:  角色-用户管理——DAO
 * @Author:          
 * @CreateDate:   2019-11-25T08:55:00.747Z
 * @Version:      V1.0
 *    
 */
public interface SysRoleUserDao extends BaseMapper<SysRoleUser> {

    List<Integer> getRoleIdListByUserId(@Param("userId") int usrId);
}
