package org.example.common.model.monster.entity;

import org.example.common.config.GameConfig;
import org.example.common.model.bullet.Bullet;
import org.example.common.model.bullet.BulletFactory;
import org.example.common.model.monster.Monster;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Monster5 extends Monster {
    public static final int MONSTER_TYPE = 5;
    public static final int SPEED = 3;
    public static final int BLOOD = 100;
    public static final int CD = 200;
    public static final int VISIBILITY = 400;
    public static final int REWARD = 50;
    private static int count;

    public Monster5(String monsterId, Integer x, Integer y, Double angle) {
        super(MONSTER_TYPE, monsterId, x, y, angle, SPEED, BLOOD, CD, VISIBILITY, REWARD);
        count = 0;
    }

    public static List<Bullet> shoot(Monster monster) {
        count = count % 360;
        List<Bullet> bullets = new ArrayList<>();
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.LIGHT_BLUE, monster.getMonsterId(), monster.getX(), monster.getY(), Math.toRadians(count)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.LIGHT_BLUE, monster.getMonsterId(), monster.getX(), monster.getY(), Math.toRadians(count + 120)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.LIGHT_BLUE, monster.getMonsterId(), monster.getX(), monster.getY(), Math.toRadians(count + 240)));
        count += 20;
        return bullets;
    }
}
