package com.test.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Preconditions;
import com.test.beans.PageQuery;
import com.test.beans.PageResult;
import com.test.common.JsonData;
import com.test.common.RequestHolder;
import com.test.exception.ParamException;
import com.test.sys.dao.SysAclDao;
import com.test.sys.entity.SysAcl;
import com.test.sys.param.AclParam;
import com.test.sys.service.SysAclService;
import com.test.util.BeanValidator;
import com.test.util.IpUtil;
import com.test.util.JsonResult;
import com.test.util.retMsg;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Description: 权限模块表-serviceImpl
 * @Author:
 * @CreateDate: 2019-11-25T05:42:24.716Z
 * @Version: V1.0
 */
@Service
public class SysAclServiceImpl extends ServiceImpl<SysAclDao, SysAcl> implements SysAclService {


    @Autowired
    private SysAclDao sysAclDao;


    @Override
    public PageResult<SysAcl> getPageByAclModuleId(Integer aclModuleId, PageQuery pageQuery) {
        Map<String, String> validate = BeanValidator.validate(pageQuery);
        if (!validate.isEmpty()) {
            throw new ParamException(validate.toString());
        }

        QueryWrapper<SysAcl> wrapper = new QueryWrapper<>();
        wrapper.eq("acl_module_id", aclModuleId);
        Integer count = sysAclDao.selectCount(wrapper);
        if (count > 0) {
            List<SysAcl> pageByAclModuleId = sysAclDao.getPageByAclModuleId(aclModuleId, pageQuery);
            return PageResult.<SysAcl>builder().data(pageByAclModuleId).total(count).build();
        }

        return PageResult.<SysAcl>builder().build();
    }

    public JsonData save(AclParam aclParam) {
        Map<String, String> validate = BeanValidator.validate(aclParam);
        if (!validate.isEmpty()) {
            throw new ParamException(validate.toString());
        }

        Map<String, Object> map = new HashMap<>(1);
        map.put("name", aclParam.getName());
        if (aclParam.getId() != null) {
            map.put("id", aclParam.getId());
        }
        List<SysAcl> sysAcls = sysAclDao.selectByMap(map);
        if (sysAcls.size() > 0) {
            throw new ParamException("同一层级下存在相同名称的权限模块");
        }

        SysAcl sysAcl = new SysAcl();
        BeanUtils.copyProperties(aclParam, sysAcl);
        sysAcl.setCode(generateCode());
        sysAcl.setOperator(RequestHolder.getCurrentUser().getUsername());
        sysAcl.setOperatorTime(new Date());
        sysAcl.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        int ret = sysAclDao.insert(sysAcl);
        if (ret > 0) {
            return JsonData.success();
        }

        return JsonData.fail();
    }

    //生成随机编码
    public String generateCode() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(new Date()) + "_" + (int) (Math.random() * 100);
    }

    public void update(AclParam aclParam) {
        Map<String, String> validate = BeanValidator.validate(aclParam);
        if (!validate.isEmpty()) {
            throw new ParamException(validate.toString());
        }

        Map<String, Object> map = new HashMap<>(1);
        map.put("name", aclParam.getName());
        List<SysAcl> sysAcls = sysAclDao.selectByMap(map);
        if (sysAcls.size() > 0) {
            throw new ParamException("同一层级下存在相同名称的权限点");
        }

        SysAcl sysAcl = sysAclDao.selectById(aclParam.getId());
        Preconditions.checkNotNull(sysAcl, "待更新的权限点不存在");

        SysAcl after = new SysAcl();
        BeanUtils.copyProperties(aclParam, after);
        after.setId(aclParam.getId());
        after.setOperator(RequestHolder.getCurrentUser().getUsername());
        after.setOperatorTime(new Date());
        after.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        sysAclDao.updateById(sysAcl);
    }


    /**
     * 查询权限模块表信息
     */
    @Override
    public JsonResult getSysAclById(Long id) {

        if (id == null) {
            return JsonResult.error(retMsg.PARAM_ID_ERROR);
        }

        SysAcl sysAcl = sysAclDao.selectById(id);
        if (sysAcl == null) {
            return JsonResult.success(1, retMsg.QUERY_NODATA);
        }
        return JsonResult.success(sysAcl);
    }


    /**
     * 添加权限模块表信息
     */
    @Override
    public JsonResult insertSelective(SysAcl sysAcl) {

        Map<String, String> map = BeanValidator.validate(sysAcl);
        if (!map.isEmpty()) {
            return JsonResult.error(retMsg.PARAM_ERROR, map);
        }

        //TODO:设置需要后端生成的参数，例如修改日期等

        int ret = sysAclDao.insert(sysAcl);
        if (ret > 0) {
            return JsonResult.success();
        }
        return JsonResult.error(retMsg.INSERT_FAILED);
    }


    /**
     * 删除权限模块表信息
     */
    @Override
    public JsonResult deleteByPrimaryKey(Long id) {

        if (id == null) {
            return JsonResult.error(retMsg.PARAM_ID_ERROR);
        }

        int ret = sysAclDao.deleteById(id);
        if (ret > 0) {
            return JsonResult.success();
        }
        return JsonResult.error();
    }


    /**
     * 修改权限模块表信息
     */
    @Override
    public JsonResult updateByPrimaryKeySelective(SysAcl sysAcl) {

        if (sysAcl.getId() == null) {
            return JsonResult.error(retMsg.PARAM_ID_ERROR);
        }

        int ret = sysAclDao.updateById(sysAcl);
        if (ret > 0) {
            return JsonResult.success();
        }
        return JsonResult.error();
    }


    /**
     * 分页条件查询权限模块表信息
     */
    @Override
    public JsonResult getSysAclList(Integer pageNum, Integer pageSize, SysAcl sysAcl) {

        QueryWrapper<SysAcl> wrapper = new QueryWrapper<>();
        wrapper.setEntity(sysAcl);

        Page<SysAcl> page = new Page<>(pageNum == null ? 1 : pageNum, pageSize == null ? 10 : pageSize);
        IPage<SysAcl> pageInfo = sysAclDao.selectPage(page, wrapper);
        return JsonResult.success(pageInfo);
    }

}