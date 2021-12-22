package org.example.server.dao;

import org.example.common.model.bullet.Bullet;
import org.example.common.model.bullet.BulletFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;
import java.util.Set;

@Repository
public class BulletDAO {

    @Autowired
    private JedisPool jedisPool;

    private static final String PREFIX = "bullet:";

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
            Bullet bullet = BulletFactory.makeBullet(Integer.parseInt(map.get("bulletType")),
                    map.get("playerId"), Integer.parseInt(map.get("x")),
                    Integer.parseInt(map.get("y")), Double.parseDouble(map.get("angle")));
            bullet.setBulletId(map.get("bulletId"));
            bullet.setPlayerId(map.get("playerId"));
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
            return jedis.incr("assignedBulletNumber");
        }
    }

    public void insert(Bullet bullet) {
        try (Jedis jedis = jedisPool.getResource()) {
            Long number = getNextBulletNumber();
            String key = PREFIX + number;
            jedis.hset(key, "bulletType", bullet.getBulletType().toString());
            jedis.hset(key, "bulletId", number.toString());
            jedis.hset(key, "playerId", bullet.getPlayerId());
            jedis.hset(key, "x", bullet.getX().toString());
            jedis.hset(key, "y", bullet.getY().toString());
            jedis.hset(key, "angle", bullet.getAngle().toString());
            jedis.hset(key, "speed", bullet.getSpeed().toString());
            jedis.hset(key, "radius", bullet.getRadius().toString());
            jedis.hset(key, "power", bullet.getPower().toString());
            jedis.hset(key, "createTime", bullet.getCreateTime().toString());
            jedis.expire(key, 20L);
        }
    }

    public Set<String> getAllBulletKeys() {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.keys("bullet*");
        }
    }

    public void delete(Bullet bullet) {
        try (Jedis jedis = jedisPool.getResource()) {
            Exception e=new Exception("delete bullet");
            e.printStackTrace();
            String key = PREFIX + bullet.getBulletId();
            jedis.del(key);
        }
    }

    public void deleteAll() {
        try (Jedis jedis = jedisPool.getResource()) {
            Set<String> keys = jedis.keys("bullet*");
            for (String key : keys) {
                jedis.del(key);
            }
        }
    }

    public void updateLocationById(Bullet bullet) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = PREFIX + bullet.getBulletId();
            jedis.hset(key, "x", bullet.getX().toString());
            jedis.hset(key, "y", bullet.getY().toString());
        }
    }


}
