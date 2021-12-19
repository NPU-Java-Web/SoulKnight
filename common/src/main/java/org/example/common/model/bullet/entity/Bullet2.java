package org.example.common.model.bullet.entity;

import org.example.common.config.GameConfig;
import org.example.common.model.bullet.Bullet;

public class Bullet2 extends Bullet {
    public static final int BULLET_TYPE= GameConfig.BulletType.ARMOR;
    public static final int SPEED = 30;
    public static final int RADIUS = 40;
    public static final int POWER = 10;

    public Bullet2(String playerId, Integer x, Integer y, Double angle){
        super(BULLET_TYPE, null, playerId, x, y, angle, SPEED, RADIUS, POWER,System.currentTimeMillis());
    }
}
