package org.example.client.calculate.communication;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.example.client.GameStartCore;
import org.example.client.calculate.service.StaticInfo;
import org.example.common.config.GameConfig;
import org.example.common.model.player.Player;

/**
 * 这个类往【发送队列】里放东西，它高频地发送玩家自己的坐标，想要发送给服务器
 *
 * @see Runnable
 * @see GameStartCore
 * @see Player
 * @see GameConfig
 */
@Slf4j
public class DeliverPlayer implements Runnable {
    private final GameStartCore gameStartCore;

    public DeliverPlayer(GameStartCore gameStartCore) {
        this.gameStartCore = gameStartCore;
    }

    @Override
    public void run() {

        while (true) {
            try {
                //暂时设置为每一秒发1次，上线后要改成一秒发30次
                Thread.sleep(GameConfig.SLEEP_TIME);
                if (StaticInfo.isrunning) {

                    Player player = new Player();
                    player.setPlayerType(gameStartCore.getPlayer().getPlayerType());
                    player.setPlayerId(gameStartCore.getPlayer().getPlayerId());
                    player.setX(gameStartCore.getPlayer().getX());
                    player.setY(gameStartCore.getPlayer().getY());
                    player.setAngle(gameStartCore.getPlayer().getAngle());
                    player.setSpeed(gameStartCore.getPlayer().getSpeed());
                    player.setBlood(gameStartCore.getPlayer().getBlood());
                    player.setScore(gameStartCore.getPlayer().getScore());
                    String message = JSON.toJSONString(player);
                    boolean success = GameStartCore.sendQueue.offer(message);
                    if (!success) {
                        log.warn("队列已满，客户端无法把人物加入队列，消息是" + message);
                    }
                    //按时间增加蓝量
                    if (GameConfig.playerStrength < 100) {
                        GameConfig.playerStrength += 0.5;
                    }

                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
