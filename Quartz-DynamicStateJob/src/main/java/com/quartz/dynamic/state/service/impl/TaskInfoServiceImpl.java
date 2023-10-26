package com.quartz.dynamic.state.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.quartz.dynamic.state.comm.CodeMsg;
import com.quartz.dynamic.state.comm.ResponseFactory;
import com.quartz.dynamic.state.comm.Result;
import com.quartz.dynamic.state.dao.TaskInfoDao;
import com.quartz.dynamic.state.entity.TaskInfo;
import com.quartz.dynamic.state.enums.EnumTaskEnable;
import com.quartz.dynamic.state.manager.TaskManager;
import com.quartz.dynamic.state.service.TaskInfoService;
import com.quartz.dynamic.state.vo.req.TaskInfoReq;
import org.quartz.CronExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 定时任务业务实现
 **/
@Service
public class TaskInfoServiceImpl implements TaskInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskInfoServiceImpl.class);

    @Resource
    private TaskInfoDao taskInfoDao;

    @Resource
    private TaskManager taskManager;

    @Override
    public Result selectTaskListByPage(TaskInfoReq taskInfoReq) {
        PageHelper.startPage(taskInfoReq.getPageCurrent(), taskInfoReq.getPageSize());
        List<TaskInfo> list = taskInfoDao.selectTaskInfos(taskInfoReq);
        PageInfo<TaskInfo> pageInfo = new PageInfo<>(list);
        return ResponseFactory.build(pageInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updateJob(TaskInfoReq taskInfoReq) {
        if (!CronExpression.isValidExpression(taskInfoReq.getCron())) {
            LOGGER.error("更新任务失败，表达式有误：{}", taskInfoReq.getCron());
            return ResponseFactory.build(CodeMsg.TASK_CRON_ERROR);
        }
        TaskInfo isExistData = taskInfoDao.selectByJobName(taskInfoReq.getJobName());
        //当任务存在，则更改失败
        if ((!Objects.isNull(isExistData)) && (!isExistData.getId().equals(taskInfoReq.getId()))) {
            return ResponseFactory.build(CodeMsg.TASK_CRON_DOUBLE);
        }
        TaskInfo data = taskInfoDao.selectByPrimaryKey(taskInfoReq.getId());
        if (data == null) {
            return ResponseFactory.build(CodeMsg.TASK_NOT_EXITES);
        }

        BeanUtils.copyProperties(taskInfoReq, data);
        data.setUpdateTime(new Date());
        taskInfoDao.updateByPrimaryKeySelective(data);

        if (!taskManager.updateJob(data)) {
            return ResponseFactory.build(CodeMsg.TASK_EXCEPTION);
        }
        return ResponseFactory.build();
    }

    @Override
    public Result pauseJob(Integer taskId) {
        TaskInfo data = taskInfoDao.selectByPrimaryKey(taskId);
        if (data == null) {
            return ResponseFactory.build(CodeMsg.TASK_NOT_EXITES);
        }
        if (!taskManager.pauseJob(data)) {
            return ResponseFactory.build(CodeMsg.TASK_EXCEPTION);
        }
        data.setStatus(EnumTaskEnable.STOP.getCode());
        taskInfoDao.updateByPrimaryKeySelective(data);
        return ResponseFactory.build();
    }

    @Override
    public Result resumeJob(Integer taskId) {
        TaskInfo data = taskInfoDao.selectByPrimaryKey(taskId);
        if (data == null) {
            return ResponseFactory.build(CodeMsg.TASK_NOT_EXITES);
        }
        if (!taskManager.resumeJob(data)) {
            return ResponseFactory.build(CodeMsg.TASK_EXCEPTION);
        }
        data.setStatus(EnumTaskEnable.START.getCode());
        taskInfoDao.updateByPrimaryKeySelective(data);
        return ResponseFactory.build();
    }

    @Override
    public Result addJob(TaskInfoReq taskInfoReq) {
        if (!taskManager.addJob(taskInfoReq)) {
            return ResponseFactory.build(CodeMsg.TASK_EXCEPTION);
        }
        TaskInfo data = taskInfoDao.selectByJobName(taskInfoReq.getJobName());
        //当任务不存在，则返回成功插入
        if (Objects.isNull(data)) {
            data = new TaskInfo();
            BeanUtils.copyProperties(taskInfoReq, data);
            data.setCreateTime(new Date());
            taskInfoDao.insertSelective(data);
            return ResponseFactory.build();
        } else {
            return ResponseFactory.build(CodeMsg.TASK_CRON_DOUBLE);
        }

    }

    @Override
    public Result delete(TaskInfoReq reqVo) {
        try {
            //TODO 删除任务只是做了暂停，如果是 Quartz Jdbc 模式下添加重复任务可能加不进去，并没有真正删除(可自行调整)
            Result result = this.pauseJob(reqVo.getId());
            //只有暂停成功的任务才能删除
            if (CodeMsg.SUCCESS == result.getCode()) {
                taskInfoDao.deleteByPrimaryKey(reqVo.getId());
                return ResponseFactory.build();
            } else {
                return ResponseFactory.build(CodeMsg.TASK_EXCEPTION);
            }
        } catch (Exception e) {
            return ResponseFactory.build(CodeMsg.TASK_EXCEPTION);
        }
    }

    @Override
    public List<TaskInfo> selectTasks() {
        return taskInfoDao.selectAll();
    }
}