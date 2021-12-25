package org.example.common.model.monster.entity;

import org.example.common.config.GameConfig;
import org.example.common.model.bullet.Bullet;
import org.example.common.model.bullet.BulletFactory;
import org.example.common.model.monster.Monster;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Monster8 extends Monster {
    public static final int MONSTER_TYPE = 8;
    public static final int SPEED = 3;
    public static final int BLOOD = 500;
    public static final int CD = 500;
    public static final int VISIBILITY = 600;
    public static final int REWARD = 50;
    private static int count;

    public Monster8(String monsterId, Integer x, Integer y, Double angle) {
        super(MONSTER_TYPE, monsterId, x, y, angle, SPEED, BLOOD, CD, VISIBILITY, REWARD);
    }

    public static List<Bullet> shoot(Monster monster) {
        count = count % 2;
        List<Bullet> bullets = new ArrayList<>();

        if (count == 0) {
            bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.RED, monster.getMonsterId(), monster.getX(), monster.getY(), Math.toRadians(0)));
            bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.RED, monster.getMonsterId(), monster.getX(), monster.getY(), Math.toRadians(30)));
            bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.RED, monster.getMonsterId(), monster.getX(), monster.getY(), Math.toRadians(60)));
            bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.RED, monster.getMonsterId(), monster.getX(), monster.getY(), Math.toRadians(90)));
            bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.RED, monster.getMonsterId(), monster.getX(), monster.getY(), Math.toRadians(120)));
            bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.RED, monster.getMonsterId(), monster.getX(), monster.getY(), Math.toRadians(150)));
            bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.RED, monster.getMonsterId(), monster.getX(), monster.getY(), Math.toRadians(180)));
        } else {
            bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.RED, monster.getMonsterId(), monster.getX(), monster.getY(), Math.toRadians(180)));
            bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.RED, monster.getMonsterId(), monster.getX(), monster.getY(), Math.toRadians(210)));
            bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.RED, monster.getMonsterId(), monster.getX(), monster.getY(), Math.toRadians(240)));
            bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.RED, monster.getMonsterId(), monster.getX(), monster.getY(), Math.toRadians(270)));
            bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.RED, monster.getMonsterId(), monster.getX(), monster.getY(), Math.toRadians(300)));
            bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.RED, monster.getMonsterId(), monster.getX(), monster.getY(), Math.toRadians(330)));
            bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.RED, monster.getMonsterId(), monster.getX(), monster.getY(), Math.toRadians(0)));

        }

        count += 1;
        return bullets;
    }
}
