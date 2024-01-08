package org.example.redisWatch;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisSubscribeExample {
    public static void main(String[] args) throws InterruptedException {
        // 创建 JedisPoolConfig 对象，设置最大连接数等参数
//        JedisPoolConfig poolConfig = new JedisPoolConfig();
        
        // 创建 JedisPool 对象，传入 Redis 服务器地址、端口号及配置信息
        JedisPool jedisPool = new JedisPool();
        
        try (Jedis jedis = jedisPool.getResource()) {
            // 获取当前客户端所属的订阅组名称列表
            System.out.println(jedis.pubsubNumPat());

            System.out.println("已订阅的频道有：");
//            for (String channel : subscribedChannels) {
//                System.out.println(channel);
//            }
        } finally {
            // 关闭 JedisPool
            jedisPool.close();
        }
    }
}