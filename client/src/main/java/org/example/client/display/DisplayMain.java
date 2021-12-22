package org.example.client.display;

import lombok.extern.slf4j.Slf4j;
import org.example.client.GameStartCore;
import org.example.client.calculate.communication.DeliverPlayer;

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



    }
}
