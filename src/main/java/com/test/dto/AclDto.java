package com.test.dto;

import com.test.sys.entity.SysAcl;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @author
 * @create 2019-11-25 16:35
 */
@Data
public class AclDto extends SysAcl {

    /**
     * 渲染到页面是否默认选中
     */
    private boolean checked = false;

    /**
     * 是否可以操作该权限
     */
    private boolean hasAcl = false;



    public static AclDto adapt(SysAcl acl) {
        AclDto aclDto = new AclDto();
        BeanUtils.copyProperties(acl,aclDto);
        return aclDto;
    }

}
