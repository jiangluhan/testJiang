package com.spring.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FirstJobDemo {

    // 任务创建工厂
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    // 步骤创建工厂
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    // 创建第一个任务，通过jobBuilderFactory【任务创建工厂】
    // 需要注意的是，我们配置的任务Job必须注册到Spring IOC容器中，并且任务的名称和步骤的名称组成【唯一】
    // 比如下列例子，我们的任务名称为firstJob，步骤的名称为step；如果存在别的任务和步骤组合也叫这个名称的话，则会执行失败
    @Bean
    public Job firstJob(){
        // JobBuilderFactory的get方法用于创建一个指定名称的任务
        return jobBuilderFactory.get("firstJob")
                // start方法指定任务的开始步骤
                .start(step())
                .build();
    }

    private Step step(){
        // StepBuilderFactory构建指定名称的步骤
        return stepBuilderFactory.get("step")
                // 步骤step由若干个小任务Tasklet组成，所以我们通过tasklet方法创建
                .tasklet((contribution, chunkContext) -> {
            System.out.println("执行步骤....");
            // 该匿名实现必须返回一个明确的执行状态，这里返回RepeatStatus.FINISHED表示该小任务执行成功，正常结束
            return RepeatStatus.FINISHED;
        }).build();
    }

    // 结论：重新启动项目，控制台并不会再次打印出任务执行日志，因为Job名称和Step名称组成唯一，执行完的不可重复的任务，不会再次执行。
}
