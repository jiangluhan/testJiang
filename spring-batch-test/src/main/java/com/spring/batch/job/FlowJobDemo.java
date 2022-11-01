package com.spring.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Flow的作用就是可以将多个步骤Step组合在一起然后再组装到任务Job中
 */
@Component
public class FlowJobDemo {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    /**
     * 使用Flow和Step构建Job的区别是，Job流程中包含Flow类型的时候需要在build()方法前调用end()方法【重点】
     */
    @Bean
    public Job flowJob(){
        return jobBuilderFactory.get("flowJob")
                .start(flow())
                .next(step3())
                .end()
                .build();
    }

    // 创建一个flow对象，包含若干个step
    // 通过FlowBuilder将step1和step2组合在一起，创建了一个名为flow的Flow，然后再将其赋给任务Job
    private Flow flow(){
        return new FlowBuilder<Flow>("flow")
                .start(step1())
                .next(step2())
                .build();
    }

    private Step step1(){
        return stepBuilderFactory.get("step1")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("测试Flow，执行步骤一操作。。。");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    private Step step2(){
        return stepBuilderFactory.get("step2")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("测试Flow，执行步骤二操作。。。");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    private Step step3(){
        return stepBuilderFactory.get("step3")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("测试Flow，执行步骤三操作。。。");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
