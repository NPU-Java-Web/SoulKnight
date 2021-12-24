package org.example.server.dao;

import org.example.common.model.bullet.Bullet;
import org.example.common.model.bullet.BulletFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;
import java.util.Set;

/**
 * 子弹DAO
 *
 * @author 廖菁璞
 */
@Repository
public class BulletDAO {
    /**
     * Redis连接池
     */
    @Autowired
    private JedisPool jedisPool;

    /**
     * 子弹的前缀
     */
    private static final String PREFIX = "bullet:";

    /**
     * 根据bulletId还原子弹
     *
     * @param bulletId bulletId
     * @return Bullet
     */
    public Bullet selectById(String bulletId) {
        String key = PREFIX + bulletId;
        return selectByKey(key);
    }

    /**
     * 根据缓存中的key还原子弹
     *
     * @param key 缓存中的key
     * @return Bullet
     */
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

    /**
     * 获取下一个子弹的编号
     *
     * @return 子弹编号
     */
    private Long getNextBulletNumber() {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.incr("assignedBulletNumber");
        }
    }

    /**
     * 插入指定子弹
     *
     * @param bullet 指定子弹
     */
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

    /**
     * 获取所有子弹的key
     *
     * @return Set
     */
    public Set<String> getAllBulletKeys() {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.keys("bullet*");
        }
    }

    /**
     * 删除指定子弹
     *
     * @param bullet 指定子弹
     */
    public void delete(Bullet bullet) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = PREFIX + bullet.getBulletId();
            jedis.del(key);
        }
    }

    /**
     * 删除所有子弹
     */
    public void deleteAll() {
        try (Jedis jedis = jedisPool.getResource()) {
            Set<String> keys = jedis.keys("bullet*");
            for (String key : keys) {
                jedis.del(key);
            }
        }
    }

    /**
     * 更新指定子弹的位置
     *
     * @param bullet 指定子弹
     */
    public void updateLocationById(Bullet bullet) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = PREFIX + bullet.getBulletId();
            jedis.hset(key, "x", bullet.getX().toString());
            jedis.hset(key, "y", bullet.getY().toString());
        }
    }

}
