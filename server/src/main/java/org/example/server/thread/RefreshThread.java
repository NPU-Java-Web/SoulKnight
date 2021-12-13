package org.example.server.thread;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.example.common.config.GameConfig;
import org.example.server.ServerCore;
import org.example.server.util.Creatures;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class RefreshThread {

//    @Autowired
//    private PlayerService playerService;
//
//    @Autowired
//    private BulletService bulletService;
//
//    @Autowired
//    private MonsterService monsterService;

    @Autowired
    private Creatures creatures;

    @Async
    public void run() {
        while (true) {
            //TODO 算法还没写完
            creatures.initialize();
            creatures.bulletsCauseHarm();
            creatures.bulletsFlying();
//            System.err.println(creatures.getResult());
            ServerCore.world.setGlobalInfo(JSON.toJSONString(creatures.getResult()));

            //等待一小会儿，再进行下次计算
            try {
                Thread.sleep(GameConfig.SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


}
