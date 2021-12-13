package org.example.server.thread;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.example.common.config.GameConfig;
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
        while (true) {
            List<Player> players = playerService.list();
            List<Monster> monsters = monsterService.list();
            List<Bullet> bullets = bulletService.list();
            //TODO 算法还没写完


            Result result = new Result(players, bullets, monsters);
            ServerCore.world.setGlobalInfo(JSON.toJSONString(result));

            //等待一小会儿，再进行下次计算
            try {
                Thread.sleep(GameConfig.SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


}
