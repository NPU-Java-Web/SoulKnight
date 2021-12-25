package org.example.common.model.player.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.common.config.GameConfig;
import org.example.common.model.player.Player;

/**
 * 默认玩家初始信息
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Player1 extends Player {
    public static final int PLAYER_TYPE = GameConfig.PlayerType.CLASSIC;
    public static final int SPEED = 6;
    public static final int BLOOD = 150;
    public static final int SCORE = 0;

    public Player1(String playerId, Integer x, Integer y, Double angle) {
        super(PLAYER_TYPE, playerId, x, y, angle, SPEED, BLOOD, SCORE);
    }

    public static int[] moveUp(int x, int y) {
        int[] point = new int[2];
        point[0] = x;
        point[1] = y - SPEED;
        return point;
    }

    public static int[] moveDown(int x, int y) {
        int[] point = new int[2];
        point[0] = x;
        point[1] = y + SPEED;
        return point;
    }

    public static int[] moveLeft(int x, int y) {
        int[] point = new int[2];
        point[0] = x - SPEED;
        point[1] = y;
        return point;
    }

    public static int[] moveRight(int x, int y) {
        int[] point = new int[2];
        point[0] = x + SPEED;
        point[1] = y;
        return point;
    }
}
