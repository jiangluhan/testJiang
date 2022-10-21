package com.spring.register.bean.imp;

import com.spring.register.bean.domain.Strawberry;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar  implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        final String beanName = "Strawberry";
        // 判断IOC容器中是否包含了名称为strawberry的组件
        boolean b = beanDefinitionRegistry.containsBeanDefinition(beanName);
        // 当把下面手动注入的代码注释掉以后，会发现此时的Strawberry组件并没有注入进去
        if(!b) {
            // 如果没有，则手动通过BeanDefinitionRegistry的registerBeanDefinition方法注册一个
            RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Strawberry.class);
            beanDefinitionRegistry.registerBeanDefinition(beanName, rootBeanDefinition);
        }
    }
}
