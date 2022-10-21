package com.spring.register.bean.condition;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 在WebConfig1中进行注册
 */
public class MyCondition implements Condition {

    /**
     * ConditionContext：上下文信息；
     * AnnotatedTypeMetadata：注解信息。
     * @param conditionContext
     * @param annotatedTypeMetadata
     * @return
     */
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String property = conditionContext.getEnvironment().getProperty("os.name");
        return property != null && property.contains("Windows");
    }
}


