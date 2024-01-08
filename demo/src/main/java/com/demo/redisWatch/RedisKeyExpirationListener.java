package com.demo.redisWatch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    public RedisKeyExpirationListener(RedisMessageListenerContainer redisMessageListenerContainer) {
        super(redisMessageListenerContainer);
    }

    /**
     * 针对redis数据失效事件，进行数据处理
     * @param message message must not be {@literal null}. 过期的key
     * @param pattern pattern matching the channel (if specified) - can be {@literal null}. 队列名称
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 拿到key
        String expiredKey = message.toString();
        log.info("监听Redis key过期，key：{}，channel：{}", expiredKey, new String(pattern));
    }
}
