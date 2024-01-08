//package org.example.redisWatch;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.listener.PatternTopic;
//import org.springframework.data.redis.listener.RedisMessageListenerContainer;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//@Configuration
//public class RedisListenerConfig {
//
//    @Autowired
//    private RedisKeyExpirationListener redisKeyExpiredListener;
//
//	//配置redis监听容器
//    @Bean
//    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
//        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.addMessageListener(redisKeyExpiredListener, new PatternTopic("__keyevent@*__:expired"));
//        return container;
//    }
//    //配置redis的序列化策略
//
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);//所有属性均可见
//        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);//为null不参加序列化
//        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);//在Redis中存储对象类信息
//        jackson2JsonRedisSerializer.setObjectMapper(mapper);
//        template.setKeySerializer(stringRedisSerializer);
//        template.setValueSerializer(jackson2JsonRedisSerializer);
//        template.setHashKeySerializer(stringRedisSerializer);
//        template.setHashValueSerializer(jackson2JsonRedisSerializer);
//        template.setConnectionFactory(factory);
//        return template;
//    }
//}
