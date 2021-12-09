package org.example.server.thread;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.example.common.entity.Bullet;
import org.example.common.entity.Monster;
import org.example.common.entity.Player;
import org.example.server.ServerCore;
import org.example.server.util.RedisUtil;
import org.example.server.util.Verification;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;

@Slf4j
public class Calculation implements Runnable {

    private final JedisPool jedisPool;

    public Calculation(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    public void run() {
        initializeMonsters();
        while (true) {
            try {
                String message = ServerCore.messages.take();
                if (message.contains("playerType")) {
                    Player player = JSON.parseObject(message, Player.class);
                    handlePlayer(player);
                } else if (message.contains("bulletType")) {
                    Bullet bullet = JSON.parseObject(message, Bullet.class);
                    handleBullet(bullet);
                } else {
                    log.warn("无法解析消息内容，无法解析的消息内容为" + message);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void initializeMonsters() {
        try (Jedis jedis = jedisPool.getResource()) {
            for (Monster monster : ServerCore.level.getInitialMonsters()) {
                String key = RedisUtil.getMonsterKey(monster);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handlePlayer(Player player) {
        if (!Verification.verifyLocation(player.getX(), player.getY())) {
            log.warn("无效的玩家位置" + player);
            return;
        }
        try (Jedis jedis = jedisPool.getResource()) {
            String key = RedisUtil.getPlayerKey(player);
            Map<String, String> res = jedis.hgetAll(key);
            if (res.size() == 0) {
                jedis.hset(key, "playerType", player.getPlayerType().toString());
                jedis.hset(key, "playerId", player.getPlayerId());
                jedis.hset(key, "x", player.getX().toString());
                jedis.hset(key, "y", player.getY().toString());
                jedis.hset(key, "angle", player.getAngle().toString());
                jedis.hset(key, "speed", player.getSpeed().toString());
                jedis.hset(key, "blood", player.getBlood().toString());
                jedis.hset(key, "score", player.getScore().toString());
                jedis.expire(key, 60L);
            } else {
                jedis.hset(key, "x", player.getX().toString());
                jedis.hset(key, "y", player.getY().toString());
                jedis.hset(key, "angle", player.getAngle().toString());
                jedis.expire(key, 60L);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleBullet(Bullet bullet) {
        if (!Verification.verifyLocation(bullet.getX(), bullet.getY())) {
            log.warn("无效的子弹位置" + bullet);
            return;
        }
        Jedis jedis = jedisPool.getResource();
        String key = RedisUtil.getBulletKey();
        jedis.hset(key, "bulletType", bullet.getBulletType().toString());
        jedis.hset(key, "playerId", bullet.getPlayerId());
        jedis.hset(key, "x", bullet.getX().toString());
        jedis.hset(key, "y", bullet.getY().toString());
        jedis.hset(key, "angle", bullet.getAngle().toString());
        jedis.hset(key, "speed", bullet.getSpeed().toString());
        jedis.hset(key, "radius", bullet.getRadius().toString());
        jedis.hset(key, "power", bullet.getPower().toString());
        jedis.hset(key, "createTime", bullet.getCreateTime().toString());
        jedis.expire(key, 60L);
        jedis.close();
    }

}
