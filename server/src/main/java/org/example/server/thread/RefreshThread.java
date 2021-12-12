package org.example.server.thread;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.example.common.entity.Bullet;
import org.example.common.entity.Monster;
import org.example.common.entity.Player;
import org.example.common.protocal.Result;
import org.example.server.ServerCore;
import org.example.server.service.BulletService;
import org.example.server.service.MonsterService;
import org.example.server.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class RefreshThread {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private BulletService bulletService;

    @Autowired
    private MonsterService monsterService;

    @Async
    public void run() {
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        while (true) {
//            log.error("我真的在运行——refresh");

//            对于player，没啥可计算的，直接返回即可。
            List<Player> players = playerService.list();
            //对于monster，第一阶段先暂时不搞怪物的移动
            List<Monster> monsters = monsterService.list();

            List<Bullet> bullets = bulletService.list();


//            List<Coordinate> livings = new ArrayList<>();
//            livings.add(new Coordinate(monsterKey, monster.getX(), monster.getY()));
//            livings.add(new Coordinate(playerKey, player.getX(), player.getY()));
//            //对于bullet，需要算一下
//            List<Bullet> bullets = new ArrayList<>();
//            Set<String> bulletKeys = jedis.keys("bullet*");
//            for (String bulletKey : bulletKeys) {
//                Bullet bullet = PlayerDAO.getBulletByKey(bulletKey);
//                long pastTime = System.currentTimeMillis() - bullet.getCreateTime();
//                long distance = bullet.getSpeed() * (pastTime) / 1000;
//                double angle = bullet.getAngle();
//                double currentX = bullet.getX() + Math.round(distance * Math.cos(angle));
//                double currentY = bullet.getY() + Math.round(distance * Math.sin(angle));
//                if (!Verification.verifyLocation((int) currentX, (int) currentY)) {
//                    jedis.del(bulletKey);
//                    continue;
//                }
//                for (Coordinate coordinate : livings) {
//                    if (coordinate.getDistance(currentX, currentY) < bullet.getRadius()) {
//                        //TODO 这里要处理扣血和死亡判断等
//
//                        jedis.del(bulletKey);
//                    }
//                }
//                bullets.add(bullet);
//            }
            Result result = new Result(players, bullets, monsters);
            ServerCore.world.setGlobalInfo(JSON.toJSONString(result));

            //等待一小会儿，再进行下次计算
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


}
