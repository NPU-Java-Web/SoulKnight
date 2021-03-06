package org.example.client.calculate.service;

import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import org.example.client.GameStartCore;
import org.example.client.display.GamePanel;
import org.example.common.config.GameConfig;
import org.example.common.config.level.Level1;
import org.example.common.config.level.Level2;
import org.example.common.config.level.Level3;
import org.example.client.Input.GameInput;
import org.example.common.model.bullet.Bullet;
import org.example.common.model.bullet.BulletFactory;
import org.example.common.model.player.entity.Player1;

import java.awt.event.MouseEvent;

/**
 * 此类为client端数据处理静态方法的集合
 * <p>主要进行人物移动数据的计算和判定，本地人物射击的数据处理，地图跳转变量改变</p>
 */
@Slf4j
public class GameDataProcess {

    public static void menuRouter(){
    }

    /**
     * 判断是否移动飞机
     */
    public static void moveMyPlayer(){
        int tempX = StaticInfo.gameStartCore.getPlayer().getX();
        int tempY = StaticInfo.gameStartCore.getPlayer().getY();
        int[] newPoint;
        /**左方向键*/
        if(GameInput.getKeyDown(37)){
            newPoint = Player1.moveLeft(tempX,tempY);
            if(!locationLegal(newPoint[0],newPoint[1])){
                StaticInfo.gameStartCore.getPlayer().moveLeft();
            }
        }
        /**右方向键*/
        if(GameInput.getKeyDown(39)){
            newPoint = Player1.moveRight(tempX,tempY);
            if(!locationLegal(newPoint[0],newPoint[1])) {
                StaticInfo.gameStartCore.getPlayer().moveRight();
            }
        }
        /**上方向键*/
        if(GameInput.getKeyDown(38)){
            newPoint = Player1.moveUp(tempX,tempY);
            if(!locationLegal(newPoint[0],newPoint[1])) {
                StaticInfo.gameStartCore.getPlayer().moveUp();
            }
        }
        /**下方向键*/
        if(GameInput.getKeyDown(40)){
            newPoint = Player1.moveDown(tempX,tempY);
            if( !locationLegal(newPoint[0],newPoint[1]) ) {
                StaticInfo.gameStartCore.getPlayer().moveDown();
            }
        }
    }

    /**
     *
     * @param x x坐标
     * @param y y坐标
     * @return 位置是否合法
     */
    private static boolean locationLegal(int x , int y){
        if(StaticInfo.gameStartCore.getLevel().getTerrain()[x][y]){
            return true;
        }
        if( x <= 10 || x >= 990 || y <= 0 || y>= 990 ){
            return true;
        }
        return false;
    }

    /**
     *
     * @param e e
     */
    public static void shootBullet(MouseEvent e){
        if(GameConfig.playerStrength>=1)
        {
            if(StaticInfo.gameStartCore!=null){
                if(e.getButton() == MouseEvent.BUTTON1){
                    /**因为鼠标点击有一个偏移量，所以坐标需要变化（8，30）*/
                    int x = e.getX()-8-StaticInfo.gameStartCore.getPlayer().getX();
                    int y = e.getY()-30-StaticInfo.gameStartCore.getPlayer().getY();
                    double l = Math.sqrt(Math.pow(Math.abs(x),2)+Math.pow(Math.abs(y),2));
                    double angle;
                    if(y>0){
                        angle = Math.acos(x/l);
                    } else {
                        angle = 2*Math.PI - Math.acos(x/l);
                    }

                        Bullet bullet = BulletFactory.makeBullet(GameConfig.playerType,StaticInfo.gameStartCore.getPlayer().getPlayerId(),
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

    /**
     * 是否地图跳转
     */
    public static void ifSwitchMap(){
        if(GamePanel.result == null) {
            StaticInfo.gameStartCore.setLevel(new Level1());
        } else{
            if (GamePanel.result.getMapType() == 1 && !(StaticInfo.gameStartCore.getLevel() instanceof Level1)) {
                StaticInfo.gameStartCore.setLevel(new Level1());
                StaticInfo.gameStartCore.getPlayer().setX(500);
                StaticInfo.gameStartCore.getPlayer().setY(700);
            } else if(GamePanel.result.getMapType() == 2 && !(StaticInfo.gameStartCore.getLevel() instanceof Level2)) {
                StaticInfo.gameStartCore.setLevel(new Level2());
                StaticInfo.gameStartCore.getPlayer().setX(500);
                StaticInfo.gameStartCore.getPlayer().setY(800);
            } else if(GamePanel.result.getMapType() == 3 && !(StaticInfo.gameStartCore.getLevel() instanceof Level3)){
                StaticInfo.gameStartCore.setLevel(new Level3());
                StaticInfo.gameStartCore.getPlayer().setX(900);
                StaticInfo.gameStartCore.getPlayer().setY(480);
            }
            else if(GamePanel.result.getMapType() == 4 &&GameConfig.flag)
            {
                //游戏结束并胜利
                GameConfig.end = true;
            }
        }
    }

}
