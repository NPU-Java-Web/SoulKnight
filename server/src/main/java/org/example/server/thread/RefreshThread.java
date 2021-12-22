package org.example.server.thread;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.example.common.config.level.Level1;
import org.example.common.config.level.Level2;
import org.example.common.config.level.Level3;
import org.example.common.model.animation.entity.Portal;
import org.example.common.model.bullet.Bullet;
import org.example.common.model.player.Player;
import org.example.common.protocal.Order;
import org.example.server.ServerCore;
import org.example.server.service.BulletService;
import org.example.server.service.MonsterService;
import org.example.server.service.OrderService;
import org.example.server.service.PlayerService;
import org.example.server.util.Creatures;
import org.example.server.util.Verification;
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

    public static boolean enterNextLevel;

    @Async
    public void run() {
        monsterService.flushDB();
        while (true) {
            log.info(ServerCore.level.toString());
            while (true) {
                //初始化怪物
                monsterService.initialize();
                //处理Order
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
                        ServerCore.level = new Level1();
                        monsterService.initialize();
                    } else {
                        log.warn("未知的命令：" + order);
                    }
                }
                //处理Player
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
                //处理Bullet
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
                creatures.monstersAttack();
                creatures.bulletsCauseHarm();
                creatures.bulletsFlying();
                creatures.AnimationsPlay();

                //判断是否可以进去下一关
                if (enterNextLevel && !monsterService.remainMonsters()) {
                    boolean authority = true;
                    if (ServerCore.level.getNumber() == 1) {
                        creatures.addPortal(new Portal(500, 50));
                        for (Player player : players) {
                            if (!Verification.atTransferArea1(player.getX(), player.getY())) {
                                authority = false;
                                break;
                            }
                        }
                    } else if (ServerCore.level.getNumber() == 2) {
                        creatures.addPortal(new Portal(50, 525));
                        for (Player player : players) {
                            if (!Verification.atTransferArea2(player.getX(), player.getY())) {
                                authority = false;
                                break;
                            }
                        }
                    } else if (ServerCore.level.getNumber() == 3) {
                        creatures.addPortal(new Portal(50, 525));
                        for (Player player : players) {
                            if (!Verification.atTransferArea3(player.getX(), player.getY())) {
                                authority = false;
                                break;
                            }
                        }
                    }
                    if (authority) {
                        enterNextLevel=false;
                        log.info(ServerCore.GlobalInfo);
                        if (ServerCore.level.getNumber() == 1) {
                            ServerCore.level = new Level2();
                            break;
                        } else if (ServerCore.level.getNumber() == 2) {
                            ServerCore.level = new Level3();
                            break;
                        } else if (ServerCore.level.getNumber() == 3) {
                            ServerCore.level.setNumber(4);
                        }
                    }
                }

                //最终设置全局信息
                ServerCore.GlobalInfo = (JSON.toJSONString(creatures.getResult()));
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}

