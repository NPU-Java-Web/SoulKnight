package org.example.common.model.bullet.entity;

import org.example.common.config.GameConfig;
import org.example.common.model.bullet.Bullet;

public class Bullet3 extends Bullet {
    public static final int BULLET_TYPE= GameConfig.BulletType.ASSASSIN;
    public static final int SPEED = 10;
    public static final int RADIUS = 15;
    public static final int POWER = 20;

    public Bullet3(String playerId, Integer x, Integer y, Double angle){
        super(BULLET_TYPE, null, playerId, x, y, angle, SPEED, RADIUS, POWER,System.currentTimeMillis());
    }
}
