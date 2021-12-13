package org.example.server.dao;

import lombok.extern.slf4j.Slf4j;
import org.example.common.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;
import java.util.Set;

@Slf4j
@Repository
public class PlayerDAO {

    @Autowired
    private JedisPool jedisPool;

    private static final String PREFIX = "player:";

    public Player selectById(String playerId) {
        String key = PREFIX + playerId;
        return selectByKey(key);
    }

    public Player selectByKey(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            if (!jedis.exists(key)) {
                return null;
            }
            Map<String, String> map = jedis.hgetAll(key);
            Player player = new Player();
            player.setPlayerType(Integer.parseInt(map.get("playerType")));
            player.setPlayerId(map.get("playerId"));
            player.setX(Integer.parseInt(map.get("x")));
            player.setY(Integer.parseInt(map.get("y")));
            player.setAngle(Double.parseDouble(map.get("angle")));
            player.setSpeed(Integer.parseInt(map.get("speed")));
            player.setBlood(Integer.parseInt(map.get("blood")));
            player.setScore(Integer.parseInt(map.get("score")));
            return player;
        }
    }

    public boolean exists(String playerId) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = PREFIX + playerId;
            return jedis.exists(key);
        }
    }

    public void insert(Player player) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = PREFIX + player.getPlayerId();
            if (jedis.exists(key)) {
                throw new RuntimeException("玩家已存在，无法插入同名玩家。");
            }
            jedis.hset(key, "playerType", player.getPlayerType().toString());
            jedis.hset(key, "playerId", player.getPlayerId());
            jedis.hset(key, "x", player.getX().toString());
            jedis.hset(key, "y", player.getY().toString());
            jedis.hset(key, "angle", player.getAngle().toString());
            jedis.hset(key, "speed", player.getSpeed().toString());
            jedis.hset(key, "blood", player.getBlood().toString());
            jedis.hset(key, "score", player.getScore().toString());
            jedis.expire(key, 10L);
        }
    }

    public void updateLocationById(Player player) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = PREFIX + player.getPlayerId();
            if (!jedis.exists(key)) {
                throw new RuntimeException("指定的玩家不存在，无法更新其位置。");
            }
            jedis.hset(key, "x", player.getX().toString());
            jedis.hset(key, "y", player.getY().toString());
            jedis.hset(key, "angle", player.getAngle().toString());
            jedis.expire(key, 10L);
        }
    }

    public Set<String> getAllPlayerKeys() {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.keys("player*");
        }
    }


}




