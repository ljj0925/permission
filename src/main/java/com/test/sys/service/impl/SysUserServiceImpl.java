package com.test.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.beans.PageQuery;
import com.test.beans.PageResult;
import com.test.common.JsonData;
import com.test.common.RequestHolder;
import com.test.sys.dao.SysUserMapper;
import com.test.sys.entity.SysUser;
import com.test.sys.param.UserParam;
import com.test.sys.service.SysLogService;
import com.test.sys.service.SysUserService;
import com.test.util.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Description: 部门-serviceImpl
 * @Author:
 * @CreateDate: 2019-11-22T11:45:47.509Z
 * @Version: V1.0
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {


    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysLogService sysLogService;

    //根据部门ID分页查询所属部门用户
    @Override
    public PageResult<SysUser> getPageByDeptId(Integer deptId, PageQuery pageQuery) {
        BeanValidator.validate(pageQuery);

        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dept_id", deptId);
        //查询总条数
        Integer count = sysUserMapper.selectCount(queryWrapper);
        if (count > 0) {
            List<SysUser> list = sysUserMapper.getPageByDeptId(deptId, pageQuery);
            return PageResult.<SysUser>builder().data(list).total(count).build();
        }

        return PageResult.<SysUser>builder().build();
    }

    //用户手机号或邮箱查询用户
    @Override
    public SysUser findByKeyword(String keyword) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("telephone", keyword).or().eq("mail", keyword);
        return sysUserMapper.selectOne(wrapper);
    }

    public JsonData save(UserParam param) {

        Map<String, String> map = BeanValidator.validateObject(param);
        if (!map.isEmpty()) {
            return JsonData.fail(retMsg.PARAM_ERROR, map);
        }

        //判断用户id，电话是否存在
        Map<String, Object> paramMap = new HashMap<>(5);
        paramMap.put("telephone", param.getTelephone());
        if (sysUserMapper.selectByMap(paramMap).size() > 0) {
            return JsonData.fail("电话已被占用");
        }
        paramMap.clear();
        paramMap.put("mail", param.getMail());
        if (sysUserMapper.selectByMap(paramMap).size() > 0) {
            return JsonData.fail("邮箱已被占用");
        }

        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(param, sysUser);
        //获取登录成功后存入ThreadLocal中的用户信息
        sysUser.setOperator(RequestHolder.getCurrentUser().getUsername());
        //从ThreadLocal中过去request，然后获取ip
        sysUser.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        sysUser.setOperatorTime(new Date());
        sysUser.setPassword(PasswordUtils.randomPassword());
        int insert = sysUserMapper.insert(sysUser);
        if (insert <= 0) {
            return JsonData.fail("新增用户失败");
        }
        return JsonData.success();
    }

    //后台管理人员使用的更新方法
    public JsonData update(UserParam param) {
        if (param.getId() == null) {
            return JsonData.fail("待更新用户ID不能为空");
        }
        Map<String, String> map = BeanValidator.validateObject(param);
        if (!map.isEmpty()) {
            return JsonData.fail(retMsg.PARAM_ERROR, map);
        }

        //判断用户id，电话是否存在
        Map<String, Object> paramMap = new HashMap<>(5);
        paramMap.put("telephone", param.getTelephone());
        if (sysUserMapper.selectByMap(paramMap).size() > 1) {
            return JsonData.fail("电话已被占用");
        }
        paramMap.clear();
        paramMap.put("mail", param.getMail());
        if (sysUserMapper.selectByMap(paramMap).size() > 1) {
            return JsonData.fail("邮箱已被占用");
        }

        //查询待更新用户是否存在
        SysUser beforUser = sysUserMapper.selectById(param.getId());
        if (beforUser == null) {
            return JsonData.fail("待更新用户不存在");
        }

        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(param, sysUser);
        //操作人
        sysUser.setOperator(RequestHolder.getCurrentUser().getUsername());
        sysUser.setOperatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));

        int ret = sysUserMapper.updateById(sysUser);
        if (ret <= 0) {
            return JsonData.fail("更新用户失败");
        }

        //记录日志
        sysLogService.saveSysLog(2, sysUser.getId(), JsonMapper.objectToString(param), JsonMapper.objectToString(sysUser),
                "test", "192.168", 0);

        return JsonData.success();
    }


    /**
     * 查询部门信息
     */
    @Override
    public JsonResult getSysUserById(Integer id) {
        JsonResult result = new JsonResult();

        if (id == null) {
            return result.error(retMsg.PARAM_ID_ERROR);
        }

        SysUser sysUser = sysUserMapper.selectById(id);
        if (sysUser == null) {
            return result.success(1, retMsg.QUERY_NODATA);
        }
        return result.success(sysUser);
    }


    /**
     * 添加部门信息
     */
    @Override
    public JsonResult insertSelective(SysUser sysUser) {
        JsonResult result = new JsonResult();

        Map<String, String> map = BeanValidator.validate(sysUser);
        if (!map.isEmpty()) {
            return result.error(retMsg.PARAM_ERROR, map);
        }

        //TODO:设置需要后端生成的参数，例如修改日期等

        int ret = sysUserMapper.insert(sysUser);
        if (ret > 0) {
            return result.success();
        }
        return result.error(retMsg.INSERT_FAILED);
    }


    /**
     * 删除部门信息
     */
    @Override
    public JsonResult deleteByPrimaryKey(Integer id) {
        JsonResult result = new JsonResult();

        if (id == null) {
            return result.error(retMsg.PARAM_ID_ERROR);
        }

        int ret = sysUserMapper.deleteById(id);
        if (ret > 0) {
            return result.success();
        }
        return result.error();
    }


    /**
     * 修改部门信息
     */
    @Override
    public JsonResult updateByPrimaryKeySelective(SysUser sysUser) {
        JsonResult result = new JsonResult();

        if (sysUser.getId() == null) {
            return result.error(retMsg.PARAM_ID_ERROR);
        }

        int ret = sysUserMapper.updateById(sysUser);
        if (ret > 0) {
            return result.success();
        }
        return result.error();
    }


    /**
     * 分页条件查询部门信息
     */
    @Override
    public JsonResult getSysUserList(Integer pageNum, Integer pageSize, SysUser sysUser) {
        JsonResult jsonResult = new JsonResult();

        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.setEntity(sysUser);

        Page<SysUser> page = new Page<>(pageNum == null ? 1 : pageNum, pageSize == null ? 10 : pageSize);
        IPage<SysUser> pageInfo = sysUserMapper.selectPage(page, wrapper);
        return jsonResult.success(pageInfo);
    }

}