package org.example.common.model.bullet.entity;

import org.example.common.config.GameConfig;
import org.example.common.model.bullet.Bullet;

public class Bullet5 extends Bullet {
    public static final int BULLET_TYPE = GameConfig.BulletType.LIGHT_BLUE;
    public static final int SPEED = 5;
    public static final int RADIUS = 15;
    public static final int POWER = 5;

    public Bullet5(String playerId, Integer x, Integer y, Double angle) {
        super(BULLET_TYPE, null, playerId, x, y, angle, SPEED, RADIUS, POWER, System.currentTimeMillis());
    }
}
