package org.example.server.thread;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.common.entity.Bullet;
import org.example.common.entity.Monster;
import org.example.common.entity.Player;
import org.example.common.protocal.Result;
import org.example.server.ServerCore;
import org.example.server.util.RedisUtil;
import org.example.server.util.Verification;
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
                List<Coordinate> livings = new ArrayList<>();
                //对于player，没啥可计算的，直接返回即可。
                List<Player> players = new ArrayList<>();
                Set<String> playerKeys = jedis.keys("player*");
                for (String playerKey : playerKeys) {
                    Map<String, String> map = jedis.hgetAll(playerKey);
                    Player player = RedisUtil.restorePlayerByMap(map);
                    livings.add(new Coordinate(playerKey, player.getX(), player.getY()));
                    players.add(player);
                }

                //对于monster，第一阶段先暂时不搞怪物的移动
                List<Monster> monsters = new ArrayList<>();
                Set<String> monsterKeys = jedis.keys("monster*");
                for (String monsterKey : monsterKeys) {
                    Map<String, String> map = jedis.hgetAll(monsterKey);
                    Monster monster = RedisUtil.restoreMonsterByMap(map);
                    livings.add(new Coordinate(monsterKey, monster.getX(), monster.getY()));
                    monsters.add(monster);
                }

                //对于bullet，需要算一下
                List<Bullet> bullets = new ArrayList<>();
                Set<String> bulletKeys = jedis.keys("bullet*");
                for (String bulletKey : bulletKeys) {
                    Map<String, String> map = jedis.hgetAll(bulletKey);
                    Bullet bullet = RedisUtil.restoreBulletByMap(map);

                    long pastTime = System.currentTimeMillis() - bullet.getCreateTime();
                    long distance = bullet.getSpeed() * (pastTime) / 1000;
                    double angle = bullet.getAngle();
                    double currentX = bullet.getX() + Math.round(distance * Math.cos(angle));
                    double currentY = bullet.getY() + Math.round(distance * Math.sin(angle));
                    if (!Verification.verifyLocation((int) currentX, (int) currentY)) {
                        jedis.del(bulletKey);
                        continue;
                    }
                    for (Coordinate coordinate : livings) {
                        if (coordinate.getDistance(currentX, currentY) < bullet.getRadius()) {
                            //TODO 这里要处理扣血和死亡判断等

                            jedis.del(bulletKey);
                        }
                    }
                    bullets.add(bullet);
                }


                Result result = new Result(players, bullets, monsters);
                ServerCore.GlobalInfo = JSON.toJSONString(result);

                //等待一小会儿，再进行下次计算
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Data
    @AllArgsConstructor
    public static class Coordinate {
        private String key;
        private int x;
        private int y;

        public double getDistance(double x2, double y2) {
            return Math.sqrt((x2 - x) * (x2 - x) + (y2 - y) * (y2 - y));
        }
    }
}
