package org.example.redisWatch;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.stereotype.Component;

@Component
public class RedisDeleteListener implements MessageListener {

    //监听主题
    private  final PatternTopic topic = new PatternTopic("__keyevent@*__:del");

    public PatternTopic getTopic() {
        return topic;
    }

    /**
     *
     * @param message 消息
     * @param pattern 主题
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        String topic = new String(pattern);
        String msg = new String(message.getBody());
        System.out.println("收到key的删除，消息主题是："+ topic+",消息内容是："+msg);
    }
}
