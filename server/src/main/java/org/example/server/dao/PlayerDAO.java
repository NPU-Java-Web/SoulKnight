package org.example.server.dao;

import lombok.extern.slf4j.Slf4j;
import org.example.common.model.player.Player;
import org.example.common.model.player.PlayerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;
import java.util.Set;

/**
 * 玩家DAO
 *
 * @author 廖菁璞
 */
@Slf4j
@Repository
public class PlayerDAO {
    /**
     * Redis连接池
     */
    @Autowired
    private JedisPool jedisPool;

    /**
     * 玩家的前缀
     */
    private static final String PREFIX = "player:";

    /**
     * 根据playerId还原出玩家
     *
     * @param playerId playerId
     * @return Player
     */
    public Player selectById(String playerId) {
        String key = PREFIX + playerId;
        return selectByKey(key);
    }

    /**
     * 根据缓存中的key还原出玩家
     *
     * @param key 缓存中的key
     * @return Player
     */
    public Player selectByKey(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            if (!jedis.exists(key)) {
                return null;
            }
            Map<String, String> map = jedis.hgetAll(key);
            Player player = PlayerFactory.makePlayer(Integer.parseInt(map.get("playerType")),
                    map.get("playerId"), Integer.parseInt(map.get("x")), Integer.parseInt(map.get("y")), Double.parseDouble(map.get("angle")));
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

    /**
     * 根据playerId判断指定的玩家是否存在
     *
     * @param playerId playerId
     * @return 是否存在
     */
    public boolean exists(String playerId) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = PREFIX + playerId;
            return jedis.exists(key);
        }
    }

    /**
     * 先初始化指定玩家，再将其插入
     *
     * @param player 指定玩家
     */
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
            jedis.expire(key, 3L);
        }
    }

    /**
     * 更新指定玩家的坐标
     *
     * @param player 指定玩家
     */
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

    /**
     * 获取所有玩家的key
     *
     * @return Set
     */
    public Set<String> getAllPlayerKeys() {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.keys("player*");
        }
    }

    /**
     * 给指定玩家扣血
     *
     * @param player     指定玩家
     * @param difference 扣除的血量
     */
    public void subtractBlood(Player player, int difference) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = PREFIX + player.getPlayerId();
            jedis.hincrBy(key, "blood", -difference);
        }
    }

    /**
     * 根据playerId删除指定的玩家
     *
     * @param playerId playerId
     */
    public void deleteById(String playerId) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = PREFIX + playerId;
            jedis.del(key);
        }
    }

}




