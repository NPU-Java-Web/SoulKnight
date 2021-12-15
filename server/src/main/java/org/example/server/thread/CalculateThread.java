package org.example.server.thread;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.example.common.model.bullet.Bullet;
import org.example.common.model.player.Player;
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

        monsterService.initializeMonsters();
        while (true) {
            try {
                String message = ServerCore.messageQueue.poll();
                if (message == null) {
                    continue;
                } else if (message.contains("playerType")) {
                    Player player = JSON.parseObject(message, Player.class);
                    playerService.saveOrUpdate(player);
                } else if (message.contains("bulletType")) {
                    System.out.println("收到子弹"+message);
                    Bullet bullet = JSON.parseObject(message, Bullet.class);
                    bulletService.save(bullet);
                } else {
                    log.warn("无法解析消息内容，无法解析的消息内容为" + message);
                }
                //调成1保证向client的帧率
                Thread.sleep(10);
            } catch (Exception e) {
                log.error("CalculateThread中出现严重异常" + e.getCause());
                e.printStackTrace();
            }
        }
    }
}
