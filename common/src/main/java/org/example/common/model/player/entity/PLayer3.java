package org.example.common.model.player.entity;

import org.example.common.config.GameConfig;
import org.example.common.model.player.Player;

public class PLayer3 extends Player {
    public static final int PLAYER_TYPE = GameConfig.PlayerType.Assassin;
    public static final int SPEED = 10;
    public static final int BLOOD = 50;
    public static final int SCORE = 0;

    public PLayer3(String playerId, Integer x, Integer y, Double angle){
        super(PLAYER_TYPE, playerId, x, y, angle, SPEED,BLOOD,SCORE);
    }
}
