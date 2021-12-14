package org.example.client.display;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.example.client.GameStartCore;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 鼠标监听
 */
@Slf4j
public class GameMouseListener implements MouseListener {
    /**鼠标释放*/
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**鼠标按下*/
    @Override
    public void mousePressed(MouseEvent e) {
    }

    /**鼠标离开组件*/
    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**鼠标进入组件*/
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**鼠标单击*/
    @Override
    public void mouseClicked(MouseEvent e) {

        log.info("Mouse Clicked");
//        Bullet bullet = new Bullet(1, GameStartCore.player.getPlayerId(), GameStartCore.player.getX(),
//                GameStartCore.player.getY(), GameStartCore.player.getAngle());
//        boolean success = GameStartCore.sendQueue.offer(JSON.toJSONString(bullet));
//        if (!success) {
//            log.warn("发送队列已满，子弹发送信息被丢弃，子弹为" + bullet);
//        }
    }
}
