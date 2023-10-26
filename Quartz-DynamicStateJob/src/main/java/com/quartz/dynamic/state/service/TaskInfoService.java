package com.quartz.dynamic.state.service;

import com.quartz.dynamic.state.comm.Result;
import com.quartz.dynamic.state.entity.TaskInfo;
import com.quartz.dynamic.state.vo.req.TaskInfoReq;

import java.util.List;

/**
 * 定时任务接口
 **/
public interface TaskInfoService {
    /**获取任务列表分页*/
    Result selectTaskListByPage(TaskInfoReq taskInfoReq);
    /**添加定时任务*/
    Result addJob(TaskInfoReq taskInfoReq);
    /**更新任务*/
    Result updateJob(TaskInfoReq taskInfoReq);
    /**暂停任务*/
    Result pauseJob(Integer taskId);
    /**恢复任务*/
    Result resumeJob(Integer taskId);
    /**获取所有任务*/
    List<TaskInfo> selectTasks();
    /**删除任务*/
    Result delete(TaskInfoReq reqVo);
}