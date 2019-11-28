package com.test.dto;

import com.google.common.collect.Lists;
import com.test.sys.entity.SysAclModule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @author
 * @create 2019-11-25 9:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AclModuleLevelDto extends SysAclModule {

    private List<AclDto> aclList = Lists.newArrayList();

    private List<AclModuleLevelDto> aclModuleList = Lists.newArrayList();

    public static AclModuleLevelDto adapt(SysAclModule model){
        AclModuleLevelDto aclModuleLevelDto = new AclModuleLevelDto();
        BeanUtils.copyProperties(model,aclModuleLevelDto);
        return aclModuleLevelDto;
    }
}
