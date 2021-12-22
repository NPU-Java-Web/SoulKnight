package org.example.common.model.monster.entity;

import org.example.common.config.GameConfig;
import org.example.common.model.bullet.Bullet;
import org.example.common.model.bullet.BulletFactory;
import org.example.common.model.monster.Monster;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Monster6 extends Monster {
    public static final int MONSTER_TYPE = 6;
    public static final int SPEED = 3;
    public static final int BLOOD = 100;
    public static final int CD = 2000;
    public static final int VISIBILITY = 300;
    public static final int REWARD = 50;
    private static int count;

    public Monster6(String monsterId, Integer x, Integer y, Double angle){
        super(MONSTER_TYPE, monsterId, x, y, angle, SPEED, BLOOD, CD, VISIBILITY, REWARD);
        this.count = 0;
    }

    public static List<Bullet> shoot(Monster monster){
        count = count % 45;
        List<Bullet> bullets = new ArrayList<>();

        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.COLORFUL,monster.getMonsterId(),monster.getX(),monster.getY(), Math.toRadians(count)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.COLORFUL,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+45)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.COLORFUL,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+90)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.COLORFUL,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+135)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.COLORFUL,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+180)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.COLORFUL,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+225)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.COLORFUL,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+270)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.COLORFUL,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+315)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.COLORFUL,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+360)));

        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.PURPLE,monster.getMonsterId(),monster.getX(),monster.getY(), Math.toRadians(count)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.PURPLE,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+45)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.PURPLE,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+90)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.PURPLE,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+135)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.PURPLE,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+180)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.PURPLE,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+225)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.PURPLE,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+270)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.PURPLE,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+315)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.PURPLE,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+360)));

        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.DARK_BLUE,monster.getMonsterId(),monster.getX(),monster.getY(), Math.toRadians(count)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.DARK_BLUE,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+45)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.DARK_BLUE,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+90)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.DARK_BLUE,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+135)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.DARK_BLUE,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+180)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.DARK_BLUE,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+225)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.DARK_BLUE,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+270)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.DARK_BLUE,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+315)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.DARK_BLUE,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+360)));


        count += 5;
        return bullets;
    }
}
