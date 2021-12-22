package org.example.common.model.bullet.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.config.GameConfig;
import org.example.common.model.bullet.Bullet;

@Data
@NoArgsConstructor
public class Bullet1 extends Bullet {
    public static final int BULLET_TYPE= GameConfig.BulletType.CLASSIC;
    public static final int SPEED = 50;
    public static final int RADIUS = 50;
    public static final int POWER = 80;

    public Bullet1(String playerId, Integer x, Integer y, Double angle){
        super(BULLET_TYPE, null, playerId, x, y, angle, SPEED, RADIUS, POWER,System.currentTimeMillis());
    }
}


