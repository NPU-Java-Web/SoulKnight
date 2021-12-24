package org.example.common.model.player.entity;

import org.example.common.config.GameConfig;
import org.example.common.model.player.Player;

public class Player2 extends Player {
    public static final int PLAYER_TYPE = GameConfig.PlayerType.ARMOR;
    public static final int SPEED = 4;
    public static final int BLOOD = 300;
    public static final int SCORE = 0;

    public Player2(String playerId, Integer x, Integer y, Double angle) {
        super(PLAYER_TYPE, playerId, x, y, angle, SPEED, BLOOD, SCORE);
    }
}
