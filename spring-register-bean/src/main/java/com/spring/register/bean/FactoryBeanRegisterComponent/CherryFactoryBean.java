package com.spring.register.bean.FactoryBeanRegisterComponent;

import com.spring.register.bean.domain.Cherry;
import org.springframework.beans.factory.FactoryBean;

public class CherryFactoryBean implements FactoryBean<Cherry> {

    // 返回需要注册的组件对象
    @Override
    public Cherry getObject() throws Exception {
        return new Cherry();
    }

    // 返回需要注册的组件类型
    @Override
    public Class<?> getObjectType() {
        return Cherry.class;
    }

    // 指明该组件是否为单例
    // 如果为多例的话，每次从容器中获取该组件都会调用其getObject方法
    @Override
    public boolean isSingleton() {
        return false;
    }
}
