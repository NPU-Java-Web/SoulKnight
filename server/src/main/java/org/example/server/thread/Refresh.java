package org.example.server.thread;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.example.common.entity.Bullet;
import org.example.common.entity.Monster;
import org.example.common.entity.Player;
import org.example.common.protocal.Result;
import org.example.server.ServerCore;
import org.example.server.util.RedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Slf4j
public class Refresh implements Runnable {
    private final JedisPool jedisPool;

    public Refresh(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    public void run() {
        while (true) {
            try (Jedis jedis = jedisPool.getResource()) {

                //TODO 遍历所有子弹和怪物，更新它们的位置，如果和怪物发生碰撞就扣血，并且删除子弹。



                //计算完毕，将计算好的结果全部返回
                List<Player> players = new ArrayList<>();
                Set<String> playerKeys = jedis.keys("player*");
                for (String playerKey : playerKeys) {
                    Map<String, String> map = jedis.hgetAll(playerKey);
                    players.add(RedisUtil.restorePlayerByMap(map));
                }

                List<Bullet> bullets = new ArrayList<>();
                Set<String> bulletKeys = jedis.keys("bullet*");
                for (String bulletKey : bulletKeys) {
                    Map<String, String> map = jedis.hgetAll(bulletKey);
                    bullets.add(RedisUtil.restoreBulletByMap(map));
                }

                List<Monster> monsters = new ArrayList<>();
                Set<String> monsterKeys = jedis.keys("monster*");
                for (String monsterKey : monsterKeys) {
                    Map<String, String> map = jedis.hgetAll(monsterKey);
                    monsters.add(RedisUtil.restoreMonsterByMap(map));
                }

                Result result = new Result(players, bullets, monsters);
                ServerCore.GlobalInfo = JSON.toJSONString(result);

                //等待一小会儿，再进行下次计算
                Thread.sleep(200);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
