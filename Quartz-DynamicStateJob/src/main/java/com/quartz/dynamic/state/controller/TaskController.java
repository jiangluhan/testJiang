package com.quartz.dynamic.state.controller;

import com.quartz.dynamic.state.comm.Result;
import com.quartz.dynamic.state.service.TaskInfoService;
import com.quartz.dynamic.state.vo.req.TaskInfoReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 定时任务管理
 **/
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskInfoService taskInfoService;

    /**定时器列表*/
    @PostMapping("/list")
    public Result list(@RequestBody TaskInfoReq reqVo) {
        return taskInfoService.selectTaskListByPage(reqVo);
    }

    /**定时器修改*/
    @PostMapping("/edit")
    public Result edit(@RequestBody TaskInfoReq reqVo) {
        return taskInfoService.updateJob(reqVo);
    }

    /**暂停任务*/
    @PostMapping("/pause")
    public Result pause(Integer taskId) {
        return taskInfoService.pauseJob(taskId);
    }

    /**增加任务*/
    @PostMapping("/add")
    public Result add(@RequestBody TaskInfoReq taskInfoReq) {
        return taskInfoService.addJob(taskInfoReq);
    }

    /**恢复任务*/
    @PostMapping("/resume")
    public Result resume(Integer taskId) {
        return taskInfoService.resumeJob(taskId);
    }

    /**删除任务*/
    @PostMapping("/del")
    public Result delete(@RequestBody TaskInfoReq reqVo) {
        return taskInfoService.delete(reqVo);
    }
}