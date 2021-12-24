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

/**
 * 用于刷新的线程
 *
 * @author 廖菁璞
 */
@Slf4j
@Service
public class RefreshThread {
    /**
     * 玩家服务
     */
    @Autowired
    private PlayerService playerService;

    /**
     * 子弹服务
     */
    @Autowired
    private BulletService bulletService;

    /**
     * 怪物服务
     */
    @Autowired
    private MonsterService monsterService;

    /**
     * 命令服务
     */
    @Autowired
    private OrderService orderService;

    /**
     * 用Creatures类暂存全局信息
     */
    @Autowired
    private Creatures creatures;

    /**
     * 记录这一关的怪物是否都打完了
     */
    public static boolean enterNextLevel;

    /**
     * 指定刷新线程所运行的方法
     */
    @Async
    public void run() {
        //首先清空缓存
        monsterService.flushDB();
        //使用循环，以免方法运行完就不运行了
        while (true) {
            log.info(ServerCore.level.toString());
            while (true) {
                //确保场上有怪物
                monsterService.initialize();
                //处理收到的命令
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
                    //处理重启游戏的命令
                    if (order.getCommand().equals("restart")) {
                        orderService.restart(order.getPlayerId());
                        //地图恢复到第一关
                        ServerCore.level = new Level1();
                        //确保场上有怪物
                        monsterService.initialize();
                    } else {
                        log.warn("未知的命令：" + order);
                    }
                }
                //处理收到的玩家
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
                //处理收到的子弹
                List<Bullet> bullets = new ArrayList<>();
                while (true) {
                    Bullet bullet = ServerCore.bulletQueue.poll();
                    if (bullet != null) {
                        bullets.add(bullet);
                    } else {
                        break;
                    }
                }

                //把所有新发射的子弹保存下来
                bulletService.save(bullets);
                //把全局信息更新到最新
                creatures.initialize();
                //让怪物进行攻击
                creatures.monstersAttack();
                //让子弹造成伤害
                creatures.bulletsCauseHarm();
                //让子弹向前飞一段距离
                creatures.bulletsFlying();
                //让动画播放到下一帧
                creatures.AnimationsPlay();

                //判断是否可以进去下一关
                if (enterNextLevel && !monsterService.remainMonsters()) {
                    //假设所有玩家都站在传送门上
                    boolean standOnPortal = true;
                    //按照关卡数不一样，有不同的处理逻辑
                    if (ServerCore.level.getNumber() == 1) {
                        //怪物都打完了，传送门肯定要显示出来
                        creatures.addPortal(new Portal(500, 50));
                        //遍历场上的所有玩家
                        for (Player player : players) {
                            //只要有一个玩家不站在传送门上
                            if (!Verification.atTransferArea1(player.getX(), player.getY())) {
                                //那就表明不是所有玩家都站在传送门上
                                standOnPortal = false;
                                break;
                            }
                        }
                    } else if (ServerCore.level.getNumber() == 2) {
                        creatures.addPortal(new Portal(50, 525));
                        for (Player player : players) {
                            if (!Verification.atTransferArea2(player.getX(), player.getY())) {
                                standOnPortal = false;
                                break;
                            }
                        }
                    } else if (ServerCore.level.getNumber() == 3) {
                        creatures.addPortal(new Portal(50, 525));
                        for (Player player : players) {
                            if (!Verification.atTransferArea3(player.getX(), player.getY())) {
                                standOnPortal = false;
                                break;
                            }
                        }
                    }
                    //如果确定所有玩家都站在了传送门上，于是开启了下一关
                    if (standOnPortal) {
                        //进入了下一关后要把enterNextLevel改为false，以免发生怪物还没打完就出现了传送门
                        enterNextLevel = false;
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

                //把ServerCore中的全局信息更新到最新
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

