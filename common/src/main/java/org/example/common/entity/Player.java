package org.example.common.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.config.GameConfig;
import org.example.common.config.player.Player1;
import org.example.common.keyListener.GameInput;

@Data
@NoArgsConstructor
public class Player {
    /**
     * 玩家的种类
     */
    private Integer playerType;
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

    public Player(Integer playerType, String playerId, Integer x, Integer y, Double angle) {
        switch (playerType) {
            case 2:
                //保留备用
                break;
            case 1:
            default:
                this.playerType = Player1.PLAYER_TYPE;
                this.speed = Player1.SPEED;
                this.blood = Player1.BLOOD;
                this.score = Player1.SCORE;
        }
        this.playerId = playerId;
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public void onClick(){
        /**左方向键*/
        if(GameInput.getKeyDown(37)){
            this.setX(this.x-this.speed);
        }
        /**右方向键*/
        if(GameInput.getKeyDown(39)){
            this.setX(this.x+this.speed);
        }
        /**上方向键*/
        if(GameInput.getKeyDown(38)){
            this.setY(this.y-this.speed);
        }
        /**下方向键*/
        if(GameInput.getKeyDown(40)){
            this.setY(this.y+this.speed);
        }
    }
}
