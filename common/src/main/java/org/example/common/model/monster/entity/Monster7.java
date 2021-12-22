package org.example.common.model.monster.entity;

import org.example.common.config.GameConfig;
import org.example.common.model.bullet.Bullet;
import org.example.common.model.bullet.BulletFactory;
import org.example.common.model.monster.Monster;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Monster7 extends Monster {
    public static final int MONSTER_TYPE = 7;
    public static final int SPEED = 3;
    public static final int BLOOD = 100;
    public static final int CD = 2000;
    public static final int VISIBILITY = 600;
    public static final int REWARD = 50;
    private static int count;

    public Monster7(String monsterId, Integer x, Integer y, Double angle){
        super(MONSTER_TYPE, monsterId, x, y, angle, SPEED, BLOOD, CD, VISIBILITY, REWARD);
    }

    public static List<Bullet> shoot(Monster monster){
        count = count % 45;
        List<Bullet> bullets = new ArrayList<>();
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.RED,monster.getMonsterId(),monster.getX(),monster.getY(), Math.toRadians(count)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.RED,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+45)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.RED,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+90)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.RED,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+135)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.RED,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+180)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.RED,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+225)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.RED,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+270)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.RED,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+315)));

        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.RED,monster.getMonsterId(),monster.getX(),monster.getY(), Math.toRadians(count+15)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.RED,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+60)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.RED,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+105)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.RED,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+150)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.RED,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+195)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.RED,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+240)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.RED,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+285)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.RED,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+330)));

        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.RED,monster.getMonsterId(),monster.getX(),monster.getY(), Math.toRadians(count+30)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.RED,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+75)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.RED,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+120)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.RED,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+165)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.RED,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+210)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.RED,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+255)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.RED,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+300)));
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.RED,monster.getMonsterId(),monster.getX(),monster.getY(),Math.toRadians(count+345)));
        count += 5;
        return bullets;
    }
}
