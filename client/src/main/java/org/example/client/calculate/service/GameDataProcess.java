package org.example.client.calculate.service;

import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import org.example.client.GameStartCore;
import org.example.common.config.GameConfig;
import org.example.common.config.level.Level1;
import org.example.common.keyListener.GameInput;
import org.example.common.model.bullet.Bullet;
import org.example.common.model.bullet.BulletFactory;
import org.example.common.model.player.entity.Player1;
import sun.tools.jstack.JStack;

import java.awt.event.MouseEvent;

@Slf4j
public class GameDataProcess {

    public static void menuRouter(){
    }


    public static void moveMyPlayer(){
        Integer tempX = StaticInfo.gameStartCore.getPlayer().getX();
        Integer tempY = StaticInfo.gameStartCore.getPlayer().getY();
        /**左方向键*/
        if(GameInput.getKeyDown(37)){
            Player1.moveLeft(tempX,tempY);
            if(StaticInfo.gameLevel.getTerrain()[tempX][tempY] == false){
                StaticInfo.gameStartCore.getPlayer().moveLeft();
            }
        }
        /**右方向键*/
        if(GameInput.getKeyDown(39)){
            Player1.moveRight(tempX,tempY);
            if(StaticInfo.gameLevel.getTerrain()[tempX][tempY] == false) {
                StaticInfo.gameStartCore.getPlayer().moveRight();
            }
        }
        /**上方向键*/
        if(GameInput.getKeyDown(38)){
            Player1.moveUp(tempX,tempY);
            if(StaticInfo.gameLevel.getTerrain()[tempX][tempY] == false) {
                StaticInfo.gameStartCore.getPlayer().moveUp();
            }
        }
        /**下方向键*/
        if(GameInput.getKeyDown(40)){
            Player1.moveDown(tempX,tempY);
            if(StaticInfo.gameLevel.getTerrain()[tempX][tempY] == false) {
                StaticInfo.gameStartCore.getPlayer().moveDown();
            }
        }
    }

    public static void shootBullet(MouseEvent e){
        if(GameConfig.playerStrength>=1)
        {
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
                        GameConfig.playerStrength = GameConfig.playerStrength-20;
                        log.info("已发送子弹信息，角度为:"+Math.toDegrees(bullet.getAngle()));
                    }
                } else {
                    log.warn("鼠标单击键位错误，应单击左键");
                }
            } else {
                log.info("Mouse Clicked");
            }
        }
        else {
            log.info("体力不足");
        }
    }

}
