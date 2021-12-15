package org.example.client.display;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.example.client.GameStartCore;
import org.example.common.config.GameConfig;
import org.example.common.model.bullet.Bullet;
import org.example.common.model.bullet.BulletFactory;


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

        if(StaticInfo.gameStartCore!=null){
            if(e.getButton() == MouseEvent.BUTTON1){
                int x = e.getX()-StaticInfo.gameStartCore.getPlayer().getX();
                int y = e.getY()-StaticInfo.gameStartCore.getPlayer().getY();
                double l = Math.sqrt(Math.pow(Math.abs(x),2)+Math.pow(Math.abs(y),2));
                double angle;
                if(y>0){
                    angle = Math.acos(x/l);
                } else {
                    angle = 2*Math.PI - Math.acos(x/l);
                }
                Bullet bullet = BulletFactory.makeBullet(GameConfig.BulletType.Classic,StaticInfo.gameStartCore.getPlayer().getPlayerId(),
                        StaticInfo.gameStartCore.getPlayer().getX(),StaticInfo.gameStartCore.getPlayer().getY(),
                        angle);
                boolean success = GameStartCore.sendQueue.offer(JSON.toJSONString(bullet));
                if (!success) {
                    log.warn("发送队列已满，子弹发送信息被丢弃，子弹为" + bullet);
                } else {
                    log.info("已发送子弹信息，角度为:"+Math.toDegrees(bullet.getAngle()));
                }
            } else {
                log.warn("鼠标单击键位错误，应单击左键");
            }
        } else {
            log.info("Mouse Clicked");
        }
    }
}
