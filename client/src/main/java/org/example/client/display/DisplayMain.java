package org.example.client.display;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.example.client.GameStartCore;
import org.example.client.display.thread.DeliverPlayer;
import org.example.common.entity.Bullet;

import javax.swing.*;

/**
 *
 * 此类只负责开启发送玩家坐标的线程
 * 以及在控制台中打印信息
 *
 * 负责人：孟辰林、翁宇哲
 */
@Slf4j
public class DisplayMain implements Runnable {
    private final GameStartCore gameStartCore;

    public DisplayMain(GameStartCore gameStartCore) {
        this.gameStartCore = gameStartCore;
    }

    /**
     * 这是显示模块的【入口】
     */
    @Override
    public void run() {

        //标注游戏开始了
        gameStartCore.setStart(true);

        //在游戏开始后开启以下线程，这个线程能够不停地把玩家坐标发给服务器，服务器收到消息之后才会回应客户端
        Thread deliverPlayer = new Thread(new DeliverPlayer(gameStartCore), "deliverPlayer");
        deliverPlayer.start();


        //如果想要告诉服务器，就用下面的方式发送（如果队列已满，尝试放入队列则会返回false）
        Bullet bullet = new Bullet(1, gameStartCore.getPlayer().getPlayerId(), gameStartCore.getPlayer().getX(),
                gameStartCore.getPlayer().getY(), gameStartCore.getPlayer().getAngle());
        boolean success = GameStartCore.sendQueue.offer(JSON.toJSONString(bullet));
        if (!success) {
            log.warn("发送队列已满，子弹发送信息被丢弃，子弹为" + bullet);
        }


        //渲染的原理是高频地读取gameStartCore中的信息，例如下面这样

        while (true) {
            //gameStartCore.getFrames().poll()是非阻塞方法，如果队列里啥都没有就会返回false，程序继续运行下去
            //gameStartCore.getFrames().take()是阻塞方法，如果队列里啥都没有，程序就会停下来，一直等到队列里有东西为止
            //请选择最合适的方法来取
            try {
                //单位是毫秒
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }








    }
}
