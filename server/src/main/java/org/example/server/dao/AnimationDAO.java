package org.example.server.dao;

import org.example.common.model.animation.Animation;
import org.example.common.model.animation.entity.Explosion;
import org.example.common.model.bullet.Bullet;
import org.example.common.model.bullet.BulletFactory;
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
            Explosion explosion=new Explosion(
                    Integer.parseInt(map.get("state")),
                    Integer.parseInt(map.get("x")),
                    Integer.parseInt(map.get("y"))
            );
            return explosion;
        }
    }
    public Set<String> getAllExplosionKeys() {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.keys("explosion*");
        }
    }

    public void insert(Explosion explosion){

    }


}
