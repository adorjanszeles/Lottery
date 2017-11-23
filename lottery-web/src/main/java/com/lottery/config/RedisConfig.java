package com.lottery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * A Redis tárolónk beállításait tartalmazó osztály
 */
@Configuration
public class RedisConfig {

    /**
     * A serialization/deserialization folyamatokért felelős beant előállító függvény.
     *
     * @return RedisTemplate instance
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }

    /**
     * A JedisConnectionFactory bean előállításáért, illetve a beállításokért felelős függvény.
     *
     * @return
     */
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
        jedisConFactory.setHostName("localhost");
        jedisConFactory.setPort(6379);
        return new JedisConnectionFactory();
    }

}
