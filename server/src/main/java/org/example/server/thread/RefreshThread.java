package org.example.server.thread;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.example.common.model.bullet.Bullet;
import org.example.common.model.player.Player;
import org.example.common.protocal.Order;
import org.example.server.ServerCore;
import org.example.server.service.BulletService;
import org.example.server.service.MonsterService;
import org.example.server.service.OrderService;
import org.example.server.service.PlayerService;
import org.example.server.util.Creatures;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    private OrderService orderService;

    @Autowired
    private Creatures creatures;

    @Async
    public void run() {
        monsterService.initializeMonsters();
        while (true) {
            List<Order> orders = new ArrayList<>();
            while (true) {
                Order order = ServerCore.orderQueue.poll();
                if (order != null) {
                    orders.add(order);
                } else {
                    break;
                }
            }
            for (Order order : orders) {
                if (order.getCommand().equals("restart")) {
                    orderService.restart(order.getPlayerId());
                    monsterService.initializeMonsters();
                } else {
                    log.warn("未知的命令：" + order);
                }
            }

            List<Player> players = new ArrayList<>();
            while (true) {
                Player player = ServerCore.playerQueue.poll();
                if (player != null) {
                    players.add(player);
                } else {
                    break;
                }
            }
            playerService.saveOrUpdate(players);
            List<Bullet> bullets = new ArrayList<>();
            while (true) {
                Bullet bullet = ServerCore.bulletQueue.poll();
                if (bullet != null) {
                    bullets.add(bullet);
                } else {
                    break;
                }
            }
            bulletService.save(bullets);
            creatures.initialize();
            creatures.bulletsCauseHarm();
            creatures.bulletsFlying();
            ServerCore.GlobalInfo = (JSON.toJSONString(creatures.getResult()));
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


}
