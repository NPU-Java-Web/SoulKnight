package org.example.server.dao;

import org.example.common.model.animation.Animation;
import org.example.common.model.animation.entity.Explosion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;
import java.util.Set;

@Repository
public class AnimationDAO {
    @Autowired
    private JedisPool jedisPool;

    private static final String PREFIX = "explosion:";

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

    public Animation selectById(String animationId) {
        String key = PREFIX + animationId;
        return selectByKey(key);
    }

    public Set<String> getAllExplosionKeys() {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.keys("explosion*");
        }
    }

    public boolean exists(String playerId) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = PREFIX + playerId;
            return jedis.exists(key);
        }
    }

    private Long getNextAnimationNumber() {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.incr("assignedAnimationNumber");
        }
    }

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

    public void delete(Animation animation) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = PREFIX + animation.getAnimationId();
            jedis.del(key);
        }
    }

    public void increaseStateById(Animation animation) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = PREFIX + animation.getAnimationId();
            Integer currentState = animation.getState();
            if (currentState < 9) {
                jedis.hset(key, "state", String.valueOf(currentState+1));
            }else {
                jedis.del(key);
            }
        }
    }

}
