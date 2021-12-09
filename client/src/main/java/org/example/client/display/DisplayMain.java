package org.example.client.display;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.example.client.ClientCore;
import org.example.client.display.thread.DeliverPlayer;
import org.example.common.entity.Bullet;

/**
 * 客户端显示模块
 * 负责人：孟辰林、翁宇哲
 */
@Slf4j
public class DisplayMain implements Runnable {
    private final ClientCore clientCore;

    public DisplayMain(ClientCore clientCore) {
        this.clientCore = clientCore;
    }

    /**
     * 这是显示模块的【入口】
     */
    @Override
    public void run() {

        //在游戏开始后开启以下线程，这个线程能够不停地把玩家坐标发给服务器，服务器收到消息之后才会回应客户端
        Thread deliverPlayer = new Thread(new DeliverPlayer(clientCore), "deliverPlayer");
        deliverPlayer.start();


        //如果想要告诉服务器，就用下面的方式发送（如果队列已满，尝试放入队列则会返回false）
        Bullet bullet = new Bullet(1, clientCore.getPlayerId(), clientCore.getX(), clientCore.getY(), clientCore.getAngle());
        boolean success = ClientCore.sendQueue.offer(JSON.toJSONString(bullet));
        if (!success) {
            log.warn("发送队列已满，子弹发送信息被丢弃，子弹为" + bullet);
        }

        //渲染的原理是高频地读取clientCore中的信息，例如下面这样
        while (true) {
            System.out.println("玩家信息" + clientCore.getPlayers());
            System.out.println("子弹信息" + clientCore.getBullets());
            System.out.println("怪物信息" + clientCore.getMonsters());

            try {
                //单位是毫秒
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
