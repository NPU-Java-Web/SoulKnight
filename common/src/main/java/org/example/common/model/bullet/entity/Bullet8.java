package org.example.common.model.bullet.entity;

import org.example.common.config.GameConfig;
import org.example.common.model.bullet.Bullet;

public class Bullet8 extends Bullet {
    public static final int BULLET_TYPE= GameConfig.BulletType.COLORFUL;
    public static final int SPEED = 3;
    public static final int RADIUS = 15;
    public static final int POWER = 5;

    public Bullet8(String playerId, Integer x, Integer y, Double angle){
        super(BULLET_TYPE, null, playerId, x, y, angle, SPEED, RADIUS, POWER,System.currentTimeMillis());
    }
}
