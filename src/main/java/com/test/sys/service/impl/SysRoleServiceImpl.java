package com.test.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Preconditions;
import com.test.common.JsonData;
import com.test.common.RequestHolder;
import com.test.exception.ParamException;
import com.test.sys.dao.SysRoleDao;
import com.test.sys.entity.SysRole;
import com.test.sys.param.RoleParam;
import com.test.sys.service.SysRoleService;
import com.test.util.BeanValidator;
import com.test.util.IpUtil;
import com.test.util.JsonResult;
import com.test.util.retMsg;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @Description: 角色管理-serviceImpl
 * @Author:
 * @CreateDate: 2019-11-25T07:44:34.554Z
 * @Version: V1.0
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements SysRoleService {


    @Autowired
    private SysRoleDao sysRoleDao;


    @Override
    public JsonData getAll() {
		return JsonData.success(sysRoleDao.selectList(new QueryWrapper<SysRole>()));
    }

	@Override
	public List roleTree(int roleId) {
		return null;
	}

	public JsonData save(RoleParam param) {
        Map<String, String> validate = BeanValidator.validate(param);
        if (!validate.isEmpty()) {
            throw new ParamException(validate.toString());
        }
        if (checkExist(param.getName(), param.getId())) {
            throw new ParamException("角色名称已存在");
        }

        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(param, sysRole);
        sysRole.setOperator(RequestHolder.getCurrentUser().getUsername());
        sysRole.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        sysRole.setOperatorTime(new Date());

        int ret = sysRoleDao.insert(sysRole);
        if (ret > 0) {
            return JsonData.success();
        }
        return JsonData.fail();
    }


    public JsonData update(RoleParam param) {
        Map<String, String> validate = BeanValidator.validate(param);
        if (!validate.isEmpty()) {
            throw new ParamException(validate.toString());
        }
        if (checkExist(param.getName(), param.getId())) {
            throw new ParamException("角色名称已存在");
        }

        //校验数据是否存在
        SysRole befor = sysRoleDao.selectById(param.getId());
        Preconditions.checkNotNull(befor, "待更新的角色不存在");

        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(param, sysRole);
        sysRole.setOperator(RequestHolder.getCurrentUser().getUsername());
        sysRole.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        sysRole.setOperatorTime(new Date());

        int ret = sysRoleDao.updateById(sysRole);
        if (ret > 0) {
            return JsonData.success();
        }
        return JsonData.fail();
    }

    public Boolean checkExist(String name, Integer id) {
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        if (id != null) {
            wrapper.ne("id", id);
        }

        return sysRoleDao.selectCount(wrapper) > 0;
    }


    /**
     * 查询角色管理信息
     */
    @Override
    public JsonResult getSysRoleById(Integer id) {

        if (id == null) {
            return JsonResult.error(retMsg.PARAM_ID_ERROR);
        }

        SysRole sysRole = sysRoleDao.selectById(id);
        if (sysRole == null) {
            return JsonResult.success(1, retMsg.QUERY_NODATA);
        }
        return JsonResult.success(sysRole);
    }


    /**
     * 添加角色管理信息
     */
    @Override
    public JsonResult insertSelective(SysRole sysRole) {

        Map<String, String> map = BeanValidator.validate(sysRole);
        if (!map.isEmpty()) {
            return JsonResult.error(retMsg.PARAM_ERROR, map);
        }

        //TODO:设置需要后端生成的参数，例如修改日期等

        int ret = sysRoleDao.insert(sysRole);
        if (ret > 0) {
            return JsonResult.success();
        }
        return JsonResult.error(retMsg.INSERT_FAILED);
    }


    /**
     * 删除角色管理信息
     */
    @Override
    public JsonResult deleteByPrimaryKey(Integer id) {

        if (id == null) {
            return JsonResult.error(retMsg.PARAM_ID_ERROR);
        }

        int ret = sysRoleDao.deleteById(id);
        if (ret > 0) {
            return JsonResult.success();
        }
        return JsonResult.error();
    }


    /**
     * 修改角色管理信息
     */
    @Override
    public JsonResult updateByPrimaryKeySelective(SysRole sysRole) {

        if (sysRole.getId() == null) {
            return JsonResult.error(retMsg.PARAM_ID_ERROR);
        }

        int ret = sysRoleDao.updateById(sysRole);
        if (ret > 0) {
            return JsonResult.success();
        }
        return JsonResult.error();
    }


    /**
     * 分页条件查询角色管理信息
     */
    @Override
    public JsonResult getSysRoleList(Integer pageNum, Integer pageSize, SysRole sysRole) {

        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        wrapper.setEntity(sysRole);

        Page<SysRole> page = new Page<>(pageNum == null ? 1 : pageNum, pageSize == null ? 10 : pageSize);
        IPage<SysRole> pageInfo = sysRoleDao.selectPage(page, wrapper);
        return JsonResult.success(pageInfo);
    }

}