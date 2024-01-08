package org.example.redisWatch;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.stereotype.Component;

@Component
public class RedisExpiredListener implements MessageListener {

    //监听主题
    private  final PatternTopic topic = new PatternTopic("__keyevent@*__:expired");

    public PatternTopic getTopic() {
        return topic;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String topic = new String(pattern);
        String msg = new String(message.getBody());
        System.out.println("收到key的过期，消息主题是："+ topic+",消息内容是："+msg);
    }
}
