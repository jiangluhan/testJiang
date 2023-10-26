package com.quartz.cluster.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class DongAoJob extends QuartzJobBean {
    private static final Log logger = LogFactory.getLog(DongAoJob.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 定时任务执行逻辑
        logger.info("[cluster-one]幼年是盼盼，青年是晶晶，中年是冰墩墩，生活见好逐渐发福");
    }
}
