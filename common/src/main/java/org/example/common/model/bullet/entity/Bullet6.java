package org.example.common.model.bullet.entity;

import org.example.common.config.GameConfig;
import org.example.common.model.bullet.Bullet;

public class Bullet6 extends Bullet {
    public static final int BULLET_TYPE = GameConfig.BulletType.PURPLE;
    public static final int SPEED = 7;
    public static final int RADIUS = 15;
    public static final int POWER = 5;

    public Bullet6(String playerId, Integer x, Integer y, Double angle) {
        super(BULLET_TYPE, null, playerId, x, y, angle, SPEED, RADIUS, POWER, System.currentTimeMillis());
    }
}
