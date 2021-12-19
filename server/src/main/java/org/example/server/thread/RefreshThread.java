package org.example.server.thread;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.example.common.config.level.Level2;
import org.example.common.config.level.Level3;
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

    @Async
    public void run() {
        monsterService.initializeMonsters();


            while (true) {
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
                        monsterService.initializeMonsters();
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
                creatures.bulletsCauseHarm();
                creatures.bulletsFlying();
                creatures.monstersAttack();
                ServerCore.GlobalInfo = (JSON.toJSONString(creatures.getResult()));
                //判断是否可以进去下一关
                if (!monsterService.remainMonsters()){
                    boolean authority=true;
                    for (Player player:players){
                        if (!Verification.atTransferArea(player.getX(), player.getY())) {
                            authority = false;
                            break;
                        }
                    }
                    if (authority){
                        if(ServerCore.level.getNumber()==1){
                            ServerCore.level=new Level2();
                        } else if (ServerCore.level.getNumber()==2){
                            ServerCore.level=new Level3();
                        }else {
//                            break;
                        }
                    }

                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}
