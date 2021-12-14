package org.example.common.config.player;

import org.example.common.config.GameConfig;
import org.example.common.entity.Player;

/**
 * 默认玩家初始信息
 */
public class Player1 extends Player {
    public static final GameConfig.PlayerType PLAYER_TYPE = GameConfig.PlayerType.Classic;
    public static final int SPEED = 10;
    public static final int BLOOD = 100;
    public static final int SCORE = 0;

    public Player1(String playerId, Integer x, Integer y, Double angle){
        super(PLAYER_TYPE, playerId, x, y, angle, SPEED,BLOOD,SCORE);
    }
}