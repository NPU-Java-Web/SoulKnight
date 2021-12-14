package org.example.client.calculate.service;

import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import org.example.client.GameStartCore;
import org.example.common.config.GameConfig;
import org.example.common.keyListener.GameInput;
import org.example.common.model.bullet.Bullet;
import org.example.common.model.bullet.BulletFactory;

import java.awt.event.MouseEvent;

@Slf4j
public class GameDataProcess {

    public static void menuRouter(){
    }


    public static void moveMyPlayer(){
        /**左方向键*/
        if(GameInput.getKeyDown(37)){
            StaticInfo.gameStartCore.getPlayer().moveLeft();
        }
        /**右方向键*/
        if(GameInput.getKeyDown(39)){
            StaticInfo.gameStartCore.getPlayer().moveRight();
        }
        /**上方向键*/
        if(GameInput.getKeyDown(38)){
            StaticInfo.gameStartCore.getPlayer().moveUp();
        }
        /**下方向键*/
        if(GameInput.getKeyDown(40)){
            StaticInfo.gameStartCore.getPlayer().moveDown();
        }
    }

    public static void shootBullet(MouseEvent e){
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
