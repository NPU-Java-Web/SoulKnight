package org.example.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.Executor;

/**
 * 配置需要的线程池、Redis连接池，开启组件扫描
 *
 * @author 廖菁璞
 */
@SpringBootApplication
@EnableAsync
public class ThreadConfig {
    /**
     * 线程池配置
     *
     * @return Executor
     */
    @Bean
    public Executor getExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(25);
        executor.initialize();
        return executor;
    }

    /**
     * Redis连接池配置
     *
     * @return JedisPool
     */
    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(10);
        return new JedisPool(config, "localhost", 6379);
    }

}