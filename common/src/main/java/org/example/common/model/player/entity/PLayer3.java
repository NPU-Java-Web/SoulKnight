package org.example.common.model.player.entity;

import org.example.common.config.GameConfig;
import org.example.common.model.player.Player;

public class PLayer3 extends Player {
    public static final int PLAYER_TYPE = GameConfig.PlayerType.ASSASSIN;
    public static final int SPEED = 7;
    public static final int BLOOD = 170;
    public static final int SCORE = 0;

    public PLayer3(String playerId, Integer x, Integer y, Double angle) {
        super(PLAYER_TYPE, playerId, x, y, angle, SPEED, BLOOD, SCORE);
    }
}
