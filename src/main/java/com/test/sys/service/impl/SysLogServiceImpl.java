package com.test.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.sys.dao.SysLogDao;
import com.test.sys.entity.SysLog;
import com.test.sys.service.SysLogService;
import com.test.util.BeanValidator;
import com.test.util.JsonResult;
import com.test.util.retMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;


/**
 * @Description: 权限日志-serviceImpl
 * @Author:
 * @CreateDate: 2019-11-23T08:50:22.584Z
 * @Version: V1.0
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogDao, SysLog> implements SysLogService {


    @Autowired
    private SysLogDao sysLogDao;


    /**
     * 查询权限日志信息
	 * @return
	 */
    @Override
    public JsonResult getSysLogById(Integer id) {
        JsonResult result = new JsonResult();

        if (id == null) {
            return result.error(retMsg.PARAM_ID_ERROR);
        }

        SysLog sysLog = sysLogDao.selectById(id);
        if (sysLog == null) {
            return result.success(1, retMsg.QUERY_NODATA);
        }
        return result.success(sysLog);
    }


    /**
     * 添加权限日志信息
     */
    @Override
    public JsonResult insertSelective(SysLog sysLog) {
        JsonResult result = new JsonResult();

        Map<String, String> map = BeanValidator.validateObject(sysLog);
        if (!map.isEmpty()) {
            return result.error(retMsg.PARAM_ERROR, map);
        }

        //TODO:设置需要后端生成的参数，例如修改日期等

        int ret = sysLogDao.insert(sysLog);
        if (ret > 0) {
            return result.success();
        }
        return result.error(retMsg.INSERT_FAILED);
    }


    /**
     * 删除权限日志信息
     */
    @Override
    public JsonResult deleteByPrimaryKey(Integer id) {
        JsonResult result = new JsonResult();

        if (id == null) {
            return result.error(retMsg.PARAM_ID_ERROR);
        }

        int ret = sysLogDao.deleteById(id);
        if (ret > 0) {
            return result.success();
        }
        return result.error();
    }


    /**
     * 修改权限日志信息
     */
    @Override
    public JsonResult updateByPrimaryKeySelective(SysLog sysLog) {
        JsonResult result = new JsonResult();

        if (sysLog.getId() == null) {
            return result.error(retMsg.PARAM_ID_ERROR);
        }

        int ret = sysLogDao.updateById(sysLog);
        if (ret > 0) {
            return result.success();
        }
        return result.error();
    }


    /**
     * 分页条件查询权限日志信息
     */
    @Override
    public JsonResult getSysLogList(Integer pageNum, Integer pageSize, SysLog sysLog) {
        JsonResult jsonResult = new JsonResult();

        QueryWrapper<SysLog> wrapper = new QueryWrapper<>();
        wrapper.setEntity(sysLog);

        Page<SysLog> page = new Page<>(pageNum == null ? 1 : pageNum, pageSize == null ? 10 : pageSize);
        IPage<SysLog> pageInfo = sysLogDao.selectPage(page, wrapper);
        return jsonResult.success(pageInfo);
    }

    /**
     * @param type       类型：1部门 2用户 3权限模块 4权限 5角色 6角色用户关系 7角色权限关系
     * @param targetId   表的主键ID
     * @param oldValue   修改前数据
     * @param newValue   修改后的值
     * @param operator   最后一次操作人
     * @param operatorIp 最后一次操作ip
     * @param status     当前是否恢复过：0未使用该记录复原过 1复原过
     */
    @Override
    public void saveSysLog(Integer type, Integer targetId, String oldValue, String newValue, String operator, String operatorIp, Integer status) {
        SysLog sysLog = new SysLog();
        sysLog.setType(type);
        sysLog.setTargetId(targetId);
        sysLog.setOldValue(oldValue);
        sysLog.setNewValue(newValue);
        sysLog.setOperator(operator);
        sysLog.setOperatorIp(operatorIp);
        sysLog.setOperatorTime(new Date());
        sysLog.setStatus(1);
        sysLogDao.insert(sysLog);
    }


}