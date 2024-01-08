package org.example.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Description:
 *
 * @author liuhao on 2022/12/5
 */
@Component
@Slf4j
public class InitConfig implements InitializingBean {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void afterPropertiesSet() {
        // 对redis进行操作
        String key = "gw:test:";
        for (int i = 0; i < 10; i++) {
            redisTemplate.boundSetOps(key).add(i);
        }
    }
}
