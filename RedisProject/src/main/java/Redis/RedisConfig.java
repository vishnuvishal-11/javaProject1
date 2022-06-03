package Redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.*;
import security.AccessList;
import security.Counter;


import java.time.Duration;
import java.util.Date;
import java.util.Map;

@Configuration
@EnableRedisRepositories(basePackages = {"Redis.AccessListRepository"," security.AccessList"})
@PropertySource("classpath:application.properties")
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String REDIS_HOST;

    @Value("${spring.redis.port}")
    private int REDIS_PORT;

    @Bean
  public  JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration configuration=new RedisStandaloneConfiguration(REDIS_HOST,REDIS_PORT);
        JedisClientConfiguration jedisClientConfiguration= JedisClientConfiguration.builder().build();
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(configuration,jedisClientConfiguration);
        return jedisConnectionFactory;
    }

    @Bean(name = "redis1")
    public RedisTemplate<String, Integer> redis1redisTemplate() {
        RedisTemplate<String, Integer> redis1redisTemplate = new RedisTemplate<>();
        redis1redisTemplate.setKeySerializer(new StringRedisSerializer());
        redis1redisTemplate.setHashKeySerializer(new GenericToStringSerializer<String>(String.class));
        redis1redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        redis1redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redis1redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redis1redisTemplate;
    }
    @Bean(name = "redis2")
    public RedisTemplate<String, Date> redis2redisTemplate() {
        RedisTemplate<String, Date> redis2redisTemplate = new RedisTemplate<>();
        redis2redisTemplate.setKeySerializer(new StringRedisSerializer());
        redis2redisTemplate.setHashKeySerializer(new GenericToStringSerializer<String>(String.class));
        redis2redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        redis2redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redis2redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redis2redisTemplate;
    }

}