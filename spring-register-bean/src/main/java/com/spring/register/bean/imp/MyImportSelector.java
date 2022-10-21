package com.spring.register.bean.imp;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 测试一次性导入较多组件
 */
public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{
                "com.spring.register.bean.domain.Apple",
                "com.spring.register.bean.domain.Banana",
                "com.spring.register.bean.domain.Watermelon"
        };
    }
}
