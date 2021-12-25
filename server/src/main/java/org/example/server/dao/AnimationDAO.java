package org.example.server.dao;

import org.example.common.model.animation.Animation;
import org.example.common.model.animation.entity.Explosion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;
import java.util.Set;

/**
 * 动画DAO
 *
 * @author 廖菁璞
 */
@Repository
public class AnimationDAO {
    /**
     * Redis连接池
     */
    @Autowired
    private JedisPool jedisPool;

    /**
     * 爆炸效果的前缀
     */
    private static final String PREFIX = "explosion:";

    /**
     * 根据key还原出爆炸动画
     *
     * @param key 缓存中的key
     * @return Explosion
     */
    public Explosion selectByKey(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            if (!jedis.exists(key)) {
                return null;
            }
            Map<String, String> map = jedis.hgetAll(key);
            Explosion explosion = new Explosion();
            explosion.setAnimationType(Integer.parseInt(map.get("animationType")));
            explosion.setAnimationId(map.get("animationId"));
            explosion.setState(Integer.parseInt(map.get("state")));
            explosion.setX(Integer.parseInt(map.get("x")));
            explosion.setY(Integer.parseInt(map.get("y")));
            return explosion;
        }
    }

    /**
     * 根据animationId还原出爆炸效果
     *
     * @param animationId 动画ID
     * @return Explosion
     */
    public Explosion selectById(String animationId) {
        String key = PREFIX + animationId;
        return selectByKey(key);
    }

    /**
     * 获取所有爆炸动画的key
     *
     * @return Set
     */
    public Set<String> getAllExplosionKeys() {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.keys("explosion*");
        }
    }

    /**
     * 获取下一个动画编号
     *
     * @return 动画编号
     */
    private Long getNextAnimationNumber() {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.incr("assignedAnimationNumber");
        }
    }

    /**
     * 插入指定的爆炸效果
     *
     * @param explosion 指定爆炸效果
     */
    public void insert(Explosion explosion) {
        try (Jedis jedis = jedisPool.getResource()) {
            Long number = getNextAnimationNumber();
            String key = PREFIX + number;
            jedis.hset(key, "animationType", explosion.getAnimationType().toString());
            jedis.hset(key, "animationId", number.toString());
            jedis.hset(key, "state", explosion.getState().toString());
            jedis.hset(key, "x", explosion.getX().toString());
            jedis.hset(key, "y", explosion.getY().toString());
            jedis.expire(key, 2L);
        }
    }

    /**
     * 删除指定的动画
     *
     * @param animation 指定动画
     */
    public void delete(Animation animation) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = PREFIX + animation.getAnimationId();
            jedis.del(key);
        }
    }

    /**
     * 让指定动画进入到下一帧
     *
     * @param animation 指定动画
     */
    public void increaseStateById(Animation animation) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = PREFIX + animation.getAnimationId();
            Integer currentState = animation.getState();
            //因为爆炸效果只有9帧，再大也没有意义，超出范围就删掉这个动画
            if (currentState < 9) {
                jedis.hset(key, "state", String.valueOf(currentState + 1));
            } else {
                jedis.del(key);
            }
        }
    }

}
