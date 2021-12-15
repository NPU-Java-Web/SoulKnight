package org.example.common.model.player.entity;

import lombok.Data;
import org.example.common.config.GameConfig;
import org.example.common.model.player.Player;

/**
 * 默认玩家初始信息
 */
@Data
public class Player1 extends Player {
    public static final int PLAYER_TYPE = GameConfig.PlayerType.Classic;
    public static final int SPEED = 7;
    public static final int BLOOD = 100;
    public static final int SCORE = 0;

    public Player1(String playerId, Integer x, Integer y, Double angle){
        super(PLAYER_TYPE, playerId, x, y, angle, SPEED,BLOOD,SCORE);
    }
}