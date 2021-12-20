package org.example.common.model.monster.entity;

import org.example.common.config.GameConfig;
import org.example.common.model.bullet.Bullet;
import org.example.common.model.bullet.BulletFactory;
import org.example.common.model.monster.Monster;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Monster2 extends Monster {
    public static final int MONSTER_TYPE = 2;
    public static final int SPEED = 3;
    public static final int BLOOD = 100;
    public static final int STATE = 0;
    public static final int VISIBILITY = 300;
    public static final int REWARD = 50;
    private int count;

    public Monster2(String monsterId, Integer x, Integer y, Double angle){
        super(MONSTER_TYPE, monsterId, x, y, angle, SPEED, BLOOD, STATE, VISIBILITY, REWARD);
        count = 0;
    }
    @Override
    public List<Bullet> shoot(){
        this.count = this.count % 45;
        List<Bullet> bullets = new ArrayList<>();
        for(int temp = 0; temp < 6 ; temp++){
            bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.LIGHT_BLUE,this.monsterId,this.getX(),this.getY(), Math.toRadians(count)));
            bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.LIGHT_BLUE,this.monsterId,this.getX(),this.getY(),Math.toRadians(count+45)));
            bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.LIGHT_BLUE,this.monsterId,this.getX(),this.getY(),Math.toRadians(count+90)));
            bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.LIGHT_BLUE,this.monsterId,this.getX(),this.getY(),Math.toRadians(count+135)));
            bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.LIGHT_BLUE,this.monsterId,this.getX(),this.getY(),Math.toRadians(count+180)));
            bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.LIGHT_BLUE,this.monsterId,this.getX(),this.getY(),Math.toRadians(count+225)));
            bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.LIGHT_BLUE,this.monsterId,this.getX(),this.getY(),Math.toRadians(count+270)));
            bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.LIGHT_BLUE,this.monsterId,this.getX(),this.getY(),Math.toRadians(count+315)));
            bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.LIGHT_BLUE,this.monsterId,this.getX(),this.getY(),Math.toRadians(count+360)));
        }
        this.count += 5;
        return bullets;
    }
}
