package com.test.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.test.dto.AclDto;
import com.test.dto.AclModuleLevelDto;
import com.test.dto.DeptLevelDto;
import com.test.sys.dao.SysAclDao;
import com.test.sys.dao.SysAclModuleDao;
import com.test.sys.dao.SysDeptDao;
import com.test.sys.entity.SysAcl;
import com.test.sys.entity.SysAclModule;
import com.test.sys.entity.SysDept;
import com.test.util.LevelUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author
 * @create 2019-11-23 20:01
 */
@Service
public class SysTreeService {


    @Autowired
    private SysDeptDao sysDeptDao;
    @Autowired
    private SysAclModuleDao sysAclModuleDao;
    @Autowired
    private SysCoreService sysCoreService;
    @Autowired
    private SysAclDao sysAclDao;


    // 根据角色id获取角色对应的权限树
    public List<AclModuleLevelDto> roleTree(int roleId) {
        //当前用户已分配权限点
        List<SysAcl> userAcList = sysCoreService.getCurrentUserAcList();
        //当前用户角色已分配的权限点
        List<SysAcl> roleAclList = sysCoreService.getRoleAclList(roleId);
        //当前系统所有权限点
        List<AclDto> aclDtoList = Lists.newArrayList();

        //将用户和用户应角色已分配的权限点组装为set，流式编程，遍历userAcList，组成一个set
        Set<Long> userAclIdSet = userAcList.stream().map(sysAcl -> sysAcl.getId()).collect(Collectors.toSet());
        Set<Long> roleAclIdSet = roleAclList.stream().map(sysAcl -> sysAcl.getId()).collect(Collectors.toSet());

        //查询系统所有权限点集合
        List<SysAcl> allAclList = sysAclDao.selectList(new QueryWrapper<>());

        //遍历系统所有权限点集合
        for (SysAcl sysAcl : allAclList) {
            AclDto dto = AclDto.adapt(sysAcl);
            //将用户已分配权限跟系统全部权限匹配，看用户已分配的权限
            if (userAclIdSet.contains(sysAcl.getId())) {
                dto.setHasAcl(true);
            }
            //将用户对应角色权限跟系统全部权限匹配，看用户用户角色对应的权限
            if (roleAclIdSet.contains(sysAcl.getId())) {
                dto.setChecked(true);
            }
            aclDtoList.add(dto);
        }
        return aclListToTree(aclDtoList);
    }

    public List<AclModuleLevelDto> aclListToTree(List<AclDto> aclDtoList) {
        if (CollectionUtils.isNotEmpty(aclDtoList)) {
            return Lists.newArrayList();
        }
        List<AclModuleLevelDto> aclModuleLevelDtoList = aclModuleTree();

        Multimap<Integer, AclDto> moduleIdAclMap = ArrayListMultimap.create();
        for (AclDto aclDto : aclDtoList) {
            //1权限点可用，0不可用
            if (aclDto.getStatus() == 1) {
                moduleIdAclMap.put(aclDto.getAclModuleId(), AclDto.adapt(aclDto));
            }
        }
        bindAclsWithOder(aclModuleLevelDtoList,moduleIdAclMap);
        return aclModuleLevelDtoList;
    }

    // 权限点绑定到权限模块树上
    public void bindAclsWithOder(List<AclModuleLevelDto> aclModuleLevelDtoList, Multimap<Integer, AclDto> moduleIdAclMap) {
        if (CollectionUtils.isNotEmpty(aclModuleLevelDtoList)){
            return;
        }
        for (AclModuleLevelDto dto : aclModuleLevelDtoList) {
            List<AclDto> aclDtoLists = (List<AclDto>) moduleIdAclMap.get(Integer.parseInt(dto.getId() + ""));
            if (CollectionUtils.isNotEmpty(aclDtoLists)) {
                //排序
                Collections.sort(aclDtoLists, new Comparator<AclDto>() {
                    @Override
                    public int compare(AclDto o1, AclDto o2) {
                        return o1.getSeq() - o2.getSeq();
                    }
                });
                dto.setAclList(aclDtoLists);
            }
            //递归处理下个层级
            bindAclsWithOder(dto.getAclModuleList(),moduleIdAclMap);
        }
    }

    public List<AclModuleLevelDto> aclModuleTree() {

        List<SysAclModule> aclModuleList = sysAclModuleDao.selectList(new QueryWrapper<SysAclModule>());
        List<AclModuleLevelDto> dtoList = Lists.newArrayList();
        for (SysAclModule sysAclModule : aclModuleList) {
            dtoList.add(AclModuleLevelDto.adapt(sysAclModule));
        }
        return aclModuleListToTree(dtoList);
    }

    //数据生成树
    public List<AclModuleLevelDto> aclModuleListToTree(List<AclModuleLevelDto> dtoList) {
        if (CollectionUtils.isEmpty(dtoList)) {
            return Lists.newArrayList();
        }
        Multimap<String, AclModuleLevelDto> levelAclMap = ArrayListMultimap.create();
        List<AclModuleLevelDto> rootList = new ArrayList<>();

        for (AclModuleLevelDto aclModuleLevelDto : dtoList) {
            levelAclMap.put(aclModuleLevelDto.getLevel(), aclModuleLevelDto);
            if (LevelUtil.ROOT.equals(aclModuleLevelDto.getLevel())) {
                rootList.add(aclModuleLevelDto);
            }
        }

        //顶层节点排序,根据seq字段
        Collections.sort(rootList, new Comparator<AclModuleLevelDto>() {
            @Override
            public int compare(AclModuleLevelDto o1, AclModuleLevelDto o2) {
                return o1.getSeq() - o2.getSeq();
            }
        });

        //递归生成树结构
        transformAclModulrTree(rootList, LevelUtil.ROOT, levelAclMap);
        return rootList;
    }

    //生成树
    public void transformAclModulrTree(List<AclModuleLevelDto> dtoList, String level, Multimap<String, AclModuleLevelDto> levelAclMap) {
        for (AclModuleLevelDto aclModuleLevelDto : dtoList) {
            //level + id = 下层级的level
            String nextLevel = LevelUtil.calculateLevel(level, Long.parseLong(aclModuleLevelDto.getId() + ""));
            //根据层级level获取所有属于该level下的AclModule
            List<AclModuleLevelDto> tempList = (List<AclModuleLevelDto>) levelAclMap.get(nextLevel);
            if (CollectionUtils.isNotEmpty(tempList)) {
                //当前层级排序
                Collections.sort(tempList, new Comparator<AclModuleLevelDto>() {
                    @Override
                    public int compare(AclModuleLevelDto o1, AclModuleLevelDto o2) {
                        return o1.getSeq() - o2.getSeq();
                    }
                });
                aclModuleLevelDto.setAclModuleList(tempList);
                //递归处理下个层级
                transformAclModulrTree(tempList, nextLevel, levelAclMap);
            }
        }
    }


    //组装部门层级树
    public List<DeptLevelDto> deptTree() {
        List<SysDept> deptList = sysDeptDao.selectList(new QueryWrapper<>());

        List<DeptLevelDto> dtoList = Lists.newArrayList();
        for (SysDept sysDept : deptList) {
            DeptLevelDto dto = DeptLevelDto.adapt(sysDept);
            dtoList.add(dto);
        }
        return deptListToTree(dtoList);
    }

    public List<DeptLevelDto> deptListToTree(List<DeptLevelDto> deptLevelList) {
        //递归组装dept树结构
        if (CollectionUtils.isEmpty(deptLevelList)) {
            return Lists.newArrayList();
        }
        //key=相同level（部门层级） value= dept1，dept2....，将多个相同层级的dept组装到一个map
        //Multimap<String,List<Object>>
        Multimap<String, DeptLevelDto> levelDeptMap = ArrayListMultimap.create();

        //层级为1的dept
        List<DeptLevelDto> rootList = Lists.newArrayList();
        for (DeptLevelDto dto : deptLevelList) {
            levelDeptMap.put(dto.getLevel(), dto);
            if (LevelUtil.ROOT.equals(dto.getLevel())) {
                //如果层级为1则加入对应list
                rootList.add(dto);
            }
        }

        //对rootList排序
        Collections.sort(rootList, new Comparator<DeptLevelDto>() {
            @Override
            public int compare(DeptLevelDto o1, DeptLevelDto o2) {
                //以seq属性排序
                return o1.getSeq() - o2.getSeq();
            }
        });
        //递归生成树
        transformDeptTree(rootList, LevelUtil.ROOT, levelDeptMap);
        return rootList;
    }

    public void transformDeptTree(List<DeptLevelDto> deptLevelList, String level, Multimap<String, DeptLevelDto> levelDeptMap) {
        for (DeptLevelDto deptLevelDto : deptLevelList) {
            //遍历该层每一个元素
            //处理当前层级数据,根据当前level层级及parentId计算下一下层级
            //0.
            String nextLevel = LevelUtil.calculateLevel(level, deptLevelDto.getId());
            //处理下一层
            List<DeptLevelDto> tempDeptList = (List<DeptLevelDto>) levelDeptMap.get(nextLevel);
            if (CollectionUtils.isNotEmpty(tempDeptList)) {
                //排序
                Collections.sort(tempDeptList, deptSeqComparator);
                //设置下一层部门
                deptLevelDto.setDeptList(tempDeptList);
                //进入下一层处理
                transformDeptTree(tempDeptList, nextLevel, levelDeptMap);
            }
        }

    }

    public Comparator<DeptLevelDto> deptSeqComparator = new Comparator<DeptLevelDto>() {
        @Override
        public int compare(DeptLevelDto o1, DeptLevelDto o2) {
            //以seq字段排序
            return o1.getSeq() - o2.getSeq();
        }
    };
}
