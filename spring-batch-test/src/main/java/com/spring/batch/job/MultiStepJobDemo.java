package com.spring.batch.job;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MultiStepJobDemo {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
//    public Job multiStepJob(){
//        return jobBuilderFactory.get("multiStepJob")
//                // 通过JobBuilderFactory的start方法指定第一个步骤，然后通过next方法不断地指定下一个步骤
//                .start(step1())
//                .next(step2())
//                .next(step3())
//                .build();
//    }

    /**
     * 通过step1()、step2()和step3()三个方法创建了三个步骤
     * 三个步骤依次执行成功
     */
    private Step step1(){
        return stepBuilderFactory.get("step1")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("执行步骤一操作。。。");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    private Step step2(){
        return stepBuilderFactory.get("step2")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("执行步骤二操作。。。");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    private Step step3(){
        return stepBuilderFactory.get("step3")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("执行步骤三操作。。。");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    /**
     * 多个步骤在执行过程中也可以通过上一个步骤的执行状态来决定是否执行下一个步骤
     */
    @Bean
    public Job multiStepJob2(){
        return jobBuilderFactory.get("multiStepJob2")
                // multiStepJob2任务先执行step1_1，当step1_1状态为完成时，接着执行step2_1，当step2_1的状态为完成时，接着执行step3_1
                .start(step1_1())
                // ExitStatus.COMPLETED常量表示任务顺利执行完毕，正常退出
                .on(ExitStatus.COMPLETED.getExitCode())
                .to(step2_1())
                .from(step2_1())
                .on(ExitStatus.COMPLETED.getExitCode())
                .to(step3_1())
                .from(step3_1())
                .end()
                .build();
    }

    private Step step1_1(){
        return stepBuilderFactory.get("step1_1")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("执行步骤一操作。。。");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    private Step step2_1(){
        return stepBuilderFactory.get("step2_1")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("执行步骤二操作。。。");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    private Step step3_1(){
        return stepBuilderFactory.get("step3_1")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("执行步骤三操作。。。");
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
