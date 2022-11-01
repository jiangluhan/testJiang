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
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * 测试并行执行：开启并行化后，并行的步骤执行顺序并不能100%确定，因为线程调度具有不确定性
 */
@Component
public class SplitJobDemo {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    /**
     * 结果：
     * 测试并行执行，执行步骤一操作。。。
     * 测试并行执行，执行步骤三操作。。。
     * 测试并行执行，执行步骤二操作。。。
     * 结论：通过上述步骤可以得知并行已开启
     */
    @Bean
    public Job splitJob() {
        return jobBuilderFactory.get("splitJob")
                .start(flow())
                // 通过JobBuilderFactory的split方法，指定一个异步执行器，将flow和flow_1异步执行（也就是并行）
                .split(new SimpleAsyncTaskExecutor()).add(flow_1())
                .split(new SimpleAsyncTaskExecutor()).add(flow_2())
                .end()
                .build();
    }

    /**
     * 创建了两个Flow：flow（包含step1和step2）和flow_1（包含step3）
     */
    private Flow flow(){
        return new FlowBuilder<Flow>("flow")
                .start(step1())
                .next(step2())
                .build();
    }

    private Flow flow_1(){
        return new FlowBuilder<Flow>("flow_1")
                .start(step3())
                .build();
    }

    private Flow flow_2(){
        return new FlowBuilder<Flow>("flow_2")
                .start(step4())
                .build();
    }

    private Step step1(){
        return stepBuilderFactory.get("step1")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("测试并行执行，执行步骤一操作。。。");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    private Step step2(){
        return stepBuilderFactory.get("step2")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("测试并行执行，执行步骤二操作。。。");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    private Step step3(){
        return stepBuilderFactory.get("step3")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("测试并行执行，执行步骤三操作。。。");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    private Step step4(){
        return stepBuilderFactory.get("step4")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("测试并行执行，执行步骤四操作。。。");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }


}
