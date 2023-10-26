package com.quartz.config;

import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

public class QuartzConfig {
    // 配置文件路径
    private static final String QUARTZ_CONFIG = "/quartz.properties";
    // 按照自己注入的数据源自行修改
    @Qualifier("writeDataSource")
    @Autowired
    private DataSource dataSource;

    @Autowired
    private AutoWiredSpringBeanToJobFactory autoWiredSpringBeanToJobFactory;

    /**
     * 从quartz.properties文件中读取Quartz配置属性
     * @return
     * @throws IOException
     */
    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource(QUARTZ_CONFIG));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }
    /**
     * JobFactory与schedulerFactoryBean中的JobFactory相互依赖,注意bean的名称
     * 在这里为JobFactory注入了Spring上下文
     *
     * @param applicationContext
     * @return
     */
    @Bean
    public JobFactory buttonJobFactory(ApplicationContext applicationContext) {
        AutoWiredSpringBeanToJobFactory jobFactory = new AutoWiredSpringBeanToJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setJobFactory(autoWiredSpringBeanToJobFactory);
        factory.setOverwriteExistingJobs(true);
        factory.setAutoStartup(true); // 设置自行启动
        // 延时启动，应用启动1秒后
        factory.setStartupDelay(1);
        factory.setQuartzProperties(quartzProperties());
        factory.setDataSource(dataSource);// 使用应用的dataSource替换quartz的dataSource
        return factory;
    }
}