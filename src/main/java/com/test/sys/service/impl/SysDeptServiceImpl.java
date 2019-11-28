package com.test.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Preconditions;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.test.common.JsonData;
import com.test.common.RequestHolder;
import com.test.dto.DeptLevelDto;
import com.test.exception.ParamException;
import com.test.sys.dao.SysDeptDao;
import com.test.sys.entity.SysDept;
import com.test.sys.param.DeptParam;
import com.test.sys.service.SysDeptService;
import com.test.sys.service.SysLogService;
import com.test.util.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
 * @Description: 部门-serviceImpl
 * @Author:
 * @CreateDate: 2019-11-22T13:55:39.466Z
 * @Version: V1.0
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptDao, SysDept> implements SysDeptService {


    @Autowired
    private SysDeptDao sysDeptDao;
    @Autowired
    private SysLogService sysLogService;


    /**
     * 查询部门信息
     */
    @Override
    public JsonResult getSysDeptById(Long id) {
        JsonResult result = new JsonResult();

        if (id == null) {
            return result.error(retMsg.PARAM_ID_ERROR);
        }

        SysDept sysDept = sysDeptDao.selectById(id);
        if (sysDept == null) {
            return result.success(1, retMsg.QUERY_NODATA);
        }
        return result.success(sysDept);
    }


    /**
     * 添加部门信息
     */
    @Override
    public JsonData insertSelective(DeptParam sysDept) {

        Map<String, String> map = BeanValidator.validate(sysDept);
        if (!map.isEmpty()) {
            throw new ParamException(map.toString());
        }

        if (checkExist(sysDept.getParentId(), sysDept.getName(), sysDept.getId())) {
            throw new ParamException("同一层级下存在相同名称的部门");
        }
        SysDept dept = SysDept.builder().name(sysDept.getName()).parentId(sysDept.getParentId())
                .seq(sysDept.getSeq()).remark(sysDept.getRemark()).build();

        dept.setLevel(LevelUtil.calculateLevel(getLevel(sysDept.getParentId()), Long.parseLong(sysDept.getParentId() + "")));
        //获取登录成功后存入ThreadLocal中的用户信息
        dept.setOperator(RequestHolder.getCurrentUser().getUsername());
        dept.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        dept.setOperatorTime(new Date());
        int ret = sysDeptDao.insert(dept);


        //记录操作日志
        sysLogService.saveSysLog(1, null, null, JsonMapper.objectToString(dept),
                "操作人","ip" , 1);//IpUtil.getRemoteIp(RequestHolder.getCurrentRequest())

        if (ret > 0) {
            return JsonData.success();
        }
        return JsonData.fail(retMsg.INSERT_FAILED);
    }

    private boolean checkExist(Integer parentId, String deptName, Integer deptId) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("name", deptName);
        if (parentId != null){
            map.put("parent_id", parentId);
        }
        if (deptId != null) {
            map.put("id", deptId);
        }

        return sysDeptDao.selectByMap(map).size() > 0;
    }

    private String getLevel(Integer deptId) {
        SysDept dept = sysDeptDao.selectById(deptId);
        if (dept == null) {
            return null;
        }
        return dept.getLevel();
    }


    /**
     * 更新部门树
     *
     * @param param
     * @return
     */
    @Override
    public JsonData updateDept(DeptParam param) {
        Map<String, String> map = BeanValidator.validate(param);
        if (!map.isEmpty()) {
            return JsonData.fail(retMsg.PARAM_ERROR, map.toString());
        }

/*        if (checkExist(param.getParentId(), param.getName(), param.getId())) {
            throw new ParamException("同一层级下存在相同名称的部门");
        }*/

        SysDept before = sysDeptDao.selectById(param.getId());
        Preconditions.checkNotNull(before,"待更新部门不存在");

        SysDept after = SysDept.builder().id(Long.parseLong(param.getId() + "")).name(param.getName()).parentId(param.getParentId())
                .seq(param.getSeq()).remark(param.getRemark()).build();
        after.setLevel(LevelUtil.calculateLevel(getLevel(param.getParentId()), Long.parseLong(param.getParentId() + "")));
        //获取登录成功后存入ThreadLocal中的用户信息
        after.setOperator(RequestHolder.getCurrentUser().getUsername());
        after.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        after.setOperatorTime(new Date());

        //关联更新所有子级
        updateWithChild(before,after);

        //记录操作日志
        sysLogService.saveSysLog(1, null, JsonMapper.objectToString(before), JsonMapper.objectToString(after),
                "操作人","ip" , 1);
        return JsonData.success();
    }


    /**
     * 更新父级部门更新关联所有子部门，开启事务
     *
     * @param befor
     * @param after
     */
    @Transactional
    public void updateWithChild(SysDept befor, SysDept after) {

        String afterLevel = after.getLevel();
        String beforLevel = befor.getLevel();
        if (!afterLevel.equals(beforLevel)) {
            List<SysDept> deptList = sysDeptDao.getChilDeptListByLevel(beforLevel);
            if (CollectionUtils.isNotEmpty(deptList)) {
                for (SysDept sysDept : deptList) {
                    String level = sysDept.getLevel();

                    if (level.indexOf(beforLevel) == 0) {
                        level = afterLevel + level.substring(beforLevel.length());
                        sysDept.setLevel(level);
                    }
                }
                sysDeptDao.batchUpdateLevel(deptList);
            }
        }
        sysDeptDao.updateById(after);
    }


    /**
     * 删除部门信息
     */
    @Override
    public JsonResult deleteByPrimaryKey(Long id) {
        JsonResult result = new JsonResult();

        if (id == null) {
            return result.error(retMsg.PARAM_ID_ERROR);
        }

        int ret = sysDeptDao.deleteById(id);
        if (ret > 0) {
            return result.success();
        }
        return result.error();
    }


    /**
     * 修改部门信息
     */
    @Override
    public JsonResult updateByPrimaryKeySelective(SysDept sysDept) {
        JsonResult result = new JsonResult();

        if (sysDept.getId() == null) {
            return result.error(retMsg.PARAM_ID_ERROR);
        }

        int ret = sysDeptDao.updateById(sysDept);
        if (ret > 0) {
            return result.success();
        }
        return result.error();
    }


    /**
     * 分页条件查询部门信息
     */
    @Override
    public JsonResult getSysDeptList(Integer pageNum, Integer pageSize, SysDept sysDept) {
        JsonResult jsonResult = new JsonResult();

        QueryWrapper<SysDept> wrapper = new QueryWrapper<>();
        wrapper.setEntity(sysDept);

        Page<SysDept> page = new Page<>(pageNum == null ? 1 : pageNum, pageSize == null ? 10 : pageSize);
        IPage<SysDept> pageInfo = sysDeptDao.selectPage(page, wrapper);
        return jsonResult.success(pageInfo);
    }


    /**
     * 获取所有部门
     *
     * @return
     */
    @Override
    public List<DeptLevelDto> getAllDept() {

        List<SysDept> list = sysDeptDao.selectList(new QueryWrapper<>());

        ArrayList<DeptLevelDto> dtoList = Lists.newArrayList();
        for (SysDept sysDept : list) {
            DeptLevelDto adapt = DeptLevelDto.adapt(sysDept);
            dtoList.add(adapt);
        }
        return deptListToTree(dtoList);
    }

    //list转树形结构
    public List<DeptLevelDto> deptListToTree(List<DeptLevelDto> deptLevelList) {
        if (CollectionUtils.isEmpty(deptLevelList)) {
            return Lists.newArrayList();
        }
        // level -> [dept1, dept2, ...] Map<String, List<Object>>
        Multimap<String, DeptLevelDto> levelDeptMap = ArrayListMultimap.create();
        List<DeptLevelDto> rootList = Lists.newArrayList();

        for (DeptLevelDto dto : deptLevelList) {
            levelDeptMap.put(dto.getLevel(), dto);
            if (LevelUtil.ROOT.equals(dto.getLevel())) {
                rootList.add(dto);
            }
        }
        // 按照seq从小到大排序
        Collections.sort(rootList, new Comparator<DeptLevelDto>() {
            public int compare(DeptLevelDto o1, DeptLevelDto o2) {
                return o1.getSeq() - o2.getSeq();
            }
        });
        // 递归生成树
        transformDeptTree(rootList, LevelUtil.ROOT, levelDeptMap);
        return rootList;
    }

    // level:0, 0, all 0->0.1,0.2
    // level:0.1
    // level:0.2
    public void transformDeptTree(List<DeptLevelDto> deptLevelList, String level, Multimap<String, DeptLevelDto> levelDeptMap) {
        for (int i = 0; i < deptLevelList.size(); i++) {
            // 遍历该层的每个元素
            DeptLevelDto deptLevelDto = deptLevelList.get(i);
            // 处理当前层级的数据
            String nextLevel = LevelUtil.calculateLevel(level, deptLevelDto.getId());
            // 处理下一层
            List<DeptLevelDto> tempDeptList = (List<DeptLevelDto>) levelDeptMap.get(nextLevel);
            if (CollectionUtils.isNotEmpty(tempDeptList)) {
                // 排序
                Collections.sort(tempDeptList, deptSeqComparator);
                // 设置下一层部门
                deptLevelDto.setDeptList(tempDeptList);
                // 进入到下一层处理
                transformDeptTree(tempDeptList, nextLevel, levelDeptMap);
            }
        }
    }

    public Comparator<DeptLevelDto> deptSeqComparator = new Comparator<DeptLevelDto>() {
        public int compare(DeptLevelDto o1, DeptLevelDto o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };

}