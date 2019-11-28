package com.test.dto;

import com.google.common.collect.Lists;
import com.test.sys.entity.SysDept;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;


/** 树形结构，DeptLevelDto里面再包含DeptLevelDto
 * @author
 * @create 2019-11-18 12:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeptLevelDto extends SysDept {

    private List<DeptLevelDto> deptList = Lists.newArrayList();

    /**
     * SysDept属性拷贝到DeptLevelDto
     *
     * @param dept
     * @return
     */
    public static DeptLevelDto adapt(SysDept dept) {
        DeptLevelDto deptLevelDto = new DeptLevelDto();
        //拷贝两个对象中的属性
        BeanUtils.copyProperties(dept, deptLevelDto);
        return deptLevelDto;
    }

}
