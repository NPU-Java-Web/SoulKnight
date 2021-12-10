package org.example.server.thread;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.example.common.entity.Bullet;
import org.example.common.entity.Player;
import org.example.server.ServerCore;
import org.example.server.service.BulletService;
import org.example.server.service.MonsterService;
import org.example.server.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CalculateThread {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private BulletService bulletService;

    @Autowired
    private MonsterService monsterService;

    @Async
    public void run() {
        monsterService.createInitialMonsters();
        while (true) {
//            log.error("我真的在运行——calculate");
            try {
//                log.info("我希望从消息队列里取出一条消息");

                String message = ServerCore.messageQueue.poll();
//                log.info("我刚从消息队列里取出一条消息");
                if (message == null) {
                    continue;
                } else if (message.contains("playerType")) {
                    Player player = JSON.parseObject(message, Player.class);
                    playerService.saveOrUpdate(player);
                } else if (message.contains("bulletType")) {
                    Bullet bullet = JSON.parseObject(message, Bullet.class);
                    bulletService.save(bullet);
                } else {
                    log.warn("无法解析消息内容，无法解析的消息内容为" + message);
                }
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
