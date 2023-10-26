package com.quartz.dynamic.state.manager;

import com.quartz.dynamic.state.entity.TaskInfo;
import com.quartz.dynamic.state.enums.EnumTaskEnable;
import com.quartz.dynamic.state.factory.SpringJobFactory;
import com.quartz.dynamic.state.service.TaskInfoService;
import com.quartz.dynamic.state.vo.req.TaskInfoReq;
import org.apache.commons.lang3.StringUtils;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class QuartzManager {

    private Logger logger = LoggerFactory.getLogger(QuartzManager.class);

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private SpringJobFactory springJobFactory;

    @Autowired
    private TaskInfoService taskInfoService;

    @PostConstruct
    public void start() {
        //启动所有任务
        try {
            scheduler.setJobFactory(springJobFactory);
            // scheduler.clear();
            List<TaskInfo> tasks = taskInfoService.selectTasks();
            for (TaskInfo taskInfo : tasks) {
                if (EnumTaskEnable.START.getCode().equals(taskInfo.getStatus()) && !StringUtils.isEmpty(taskInfo.getCron())) {
                    TaskInfoReq data=new TaskInfoReq();
                    BeanUtils.copyProperties(taskInfo,data);
                    taskInfoService.addJob(data);
                }
            }
            logger.info("定时任务启动完成");
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("定时任务初始化失败");
        }
    }
}