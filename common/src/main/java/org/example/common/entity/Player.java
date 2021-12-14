package org.example.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.config.GameConfig;
import org.example.common.config.player.Player1;
import org.example.common.keyListener.GameInput;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    protected GameConfig.PlayerType playerType;
    /**
     * 玩家的ID
     */
    private String playerId;
    /**
     * 玩家的X坐标
     */
    private Integer x;
    /**
     * 玩家的Y坐标
     */
    private Integer y;
    /**
     * 玩家面向的角度
     */
    private Double angle;
    /**
     * 玩家的最大速度
     */
    private Integer speed;
    /**
     * 玩家当前的血量
     */
    private Integer blood;
    /**
     * 玩家得到的分数
     */
    private Integer score;

    public void moveUp(){
        this.setY(this.y-this.speed);
    }

    public void moveDown(){
        this.setY(this.y+this.speed);
    }

    public void moveLeft(){
        this.setX(this.x-this.speed);
    }

    public void moveRight(){
        this.setX(this.x+this.speed);
    }

    public void onClick(){
        //从GameRenderThread改动palyer的变量，从而使得DisplayMain的线程能不间断实时将player发送到serve层
        //
        /**左方向键*/
        if(GameInput.getKeyDown(37)){
            moveLeft();
        }
        /**右方向键*/
        if(GameInput.getKeyDown(39)){
            moveRight();
        }
        /**上方向键*/
        if(GameInput.getKeyDown(38)){
            moveUp();
        }
        /**下方向键*/
        if(GameInput.getKeyDown(40)){
            moveDown();
        }
    }
}
