package com.test.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.beans.PageQuery;
import com.test.sys.entity.SysAcl;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**   
 *  
 * @Description:  权限模块表——DAO
 * @Author:          
 * @CreateDate:   2019-11-25T05:42:24.716Z
 * @Version:      V1.0
 *    1
 */
public interface SysAclDao extends BaseMapper<SysAcl> {

    List<SysAcl> getPageByAclModuleId(@Param("aclModuleId") int aclModuleId, @Param("page") PageQuery pageQuery);

    List<SysAcl> getByIdList(@Param("idList") List<Integer> idList);

    //getAll（）；不用写
}
