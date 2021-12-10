package org.example.server.dao;

import org.example.common.entity.Monster;
import org.example.server.config.RedisConfig;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;
import java.util.Set;

@Repository
public class MonsterDAO {


    private final JedisPool jedisPool;

    private static final String PREFIX = "monster:";

    public MonsterDAO() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(10);
        this.jedisPool = new JedisPool(config, RedisConfig.ADDRESS, RedisConfig.PORT);
    }


    public Monster selectById(String monsterId) {
        String key = PREFIX + monsterId;
        return selectByKey(key);
    }

    public Monster selectByKey(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            if (!jedis.exists(key)) {
                return null;
            }
            Map<String, String> map = jedis.hgetAll(key);
            Monster monster = new Monster();
            monster.setMonsterType(Integer.parseInt(map.get("monsterType")));
            monster.setMonsterId(map.get("monsterId"));
            monster.setX(Integer.parseInt(map.get("x")));
            monster.setY(Integer.parseInt(map.get("y")));
            monster.setAngle(Double.parseDouble(map.get("angle")));
            monster.setSpeed(Integer.parseInt(map.get("speed")));
            monster.setBlood(Integer.parseInt(map.get("blood")));
            monster.setState(Integer.parseInt(map.get("state")));
            monster.setVisibility(Integer.parseInt(map.get("visibility")));
            monster.setReward(Integer.parseInt(map.get("reward")));
            return monster;
        }
    }

    public boolean exists(String playerId) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = PREFIX + playerId;
            return jedis.exists(key);
        }
    }

    public void insert(Monster monster) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = PREFIX + monster.getMonsterId();
            if (jedis.exists(key)) {
                throw new RuntimeException("怪物已存在，无法插入同名怪物。");
            }
            jedis.hset(key, "monsterType", monster.getMonsterType().toString());
            jedis.hset(key, "monsterId", monster.getMonsterId());
            jedis.hset(key, "x", monster.getX().toString());
            jedis.hset(key, "y", monster.getY().toString());
            jedis.hset(key, "angle", monster.getAngle().toString());
            jedis.hset(key, "speed", monster.getSpeed().toString());
            jedis.hset(key, "blood", monster.getBlood().toString());
            jedis.hset(key, "state", monster.getState().toString());
            jedis.hset(key, "visibility", monster.getVisibility().toString());
            jedis.hset(key, "reward", monster.getReward().toString());
        }
    }

    public Set<String> getAllMonsterKeys() {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.keys("monster*");
        }
    }

}
