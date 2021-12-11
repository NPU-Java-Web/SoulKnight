package org.example.server.dao;

import org.example.common.entity.Bullet;
import org.example.server.config.RedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;
import java.util.Set;

@Repository
public class BulletDAO {

    @Autowired
    private JedisPool jedisPool;

    private static final String PREFIX = "bullet:";

//    public BulletDAO() {
//        JedisPoolConfig config = new JedisPoolConfig();
//        config.setMaxTotal(20);
//        config.setMaxIdle(10);
//        this.jedisPool = new JedisPool(config, RedisConfig.ADDRESS, RedisConfig.PORT);
//    }

    public Bullet selectById(String bulletId) {
        String key = PREFIX + bulletId;
        return selectByKey(key);
    }

    public Bullet selectByKey(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            if (!jedis.exists(key)) {
                return null;
            }
            Map<String, String> map = jedis.hgetAll(key);
            Bullet bullet = new Bullet();
            bullet.setBulletType(Integer.parseInt(map.get("bulletType")));
            bullet.setBulletId(map.get("bulletId"));
            bullet.setPlayerId(map.get("playerId"));
            bullet.setX(Integer.parseInt(map.get("x")));
            bullet.setY(Integer.parseInt(map.get("y")));
            bullet.setAngle(Double.parseDouble(map.get("angle")));
            bullet.setSpeed(Integer.parseInt(map.get("speed")));
            bullet.setRadius(Integer.parseInt(map.get("radius")));
            bullet.setPower(Integer.parseInt(map.get("power")));
            bullet.setCreateTime(Long.parseLong(map.get("createTime")));
            return bullet;
        }
    }

    public boolean exists(String playerId) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = PREFIX + playerId;
            return jedis.exists(key);
        }
    }

    private Long getNextBulletNumber() {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.incr("assignedNumber");
        }
    }

    public void insert(Bullet bullet) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = PREFIX + getNextBulletNumber();
            jedis.hset(key, "bulletType", bullet.getBulletType().toString());
            jedis.hset(key, "bulletId", key);
            jedis.hset(key, "playerId", bullet.getPlayerId());
            jedis.hset(key, "x", bullet.getX().toString());
            jedis.hset(key, "y", bullet.getY().toString());
            jedis.hset(key, "angle", bullet.getAngle().toString());
            jedis.hset(key, "speed", bullet.getSpeed().toString());
            jedis.hset(key, "radius", bullet.getRadius().toString());
            jedis.hset(key, "power", bullet.getPower().toString());
            jedis.hset(key, "createTime", bullet.getCreateTime().toString());
            jedis.expire(key, 30L);
        }
    }

    public Set<String> getAllBulletKeys() {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.keys("bullet*");
        }
    }


}
