package com.quartz.dynamic.state.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TaskInfo implements Serializable {
    private Integer id;
    private String cron;
    private String jobName;
    private String status;
    private Date createTime;
    private Date updateTime;
}