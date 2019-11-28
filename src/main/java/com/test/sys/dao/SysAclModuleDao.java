package com.test.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.sys.entity.SysAclModule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**   
 *  
 * @Description:  权限模块表——DAO
 * @Author:          
 * @CreateDate:   2019-11-25T00:54:11.722Z
 * @Version:      V1.0
 *    
 */
public interface SysAclModuleDao extends BaseMapper<SysAclModule> {

    List<SysAclModule> getChilAclModuleListByLevel(@Param("level") String beforLevel);

    void batchUpdateLevel(@Param("sysAclModuleList") List<SysAclModule> sysAclModuleList );

}
