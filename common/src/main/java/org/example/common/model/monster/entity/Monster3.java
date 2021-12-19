package org.example.common.model.monster.entity;

import org.example.common.config.GameConfig;
import org.example.common.model.bullet.Bullet;
import org.example.common.model.bullet.BulletFactory;
import org.example.common.model.monster.Monster;

import java.util.Vector;

public class Monster3 extends Monster {
    public static final int MONSTER_TYPE = 3;
    public static final int SPEED = 3;
    public static final int BLOOD = 100;
    public static final int STATE = 0;
    public static final int VISIBILITY = 300;
    public static final int REWARD = 50;
    private int count;
    public Monster3(String monsterId, Integer x, Integer y, Double angle){
        super(MONSTER_TYPE, monsterId, x, y, angle, SPEED, BLOOD, STATE, VISIBILITY, REWARD);
        count = 0;
    }
    @Override
    public Vector<Bullet> shoot(){
        this.count = this.count % 180;
        Vector<Bullet> bullets = new Vector<>();
        for(int temp = 0; temp < 6 ; temp++){
            bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.LIGHT_BLUE,this.monsterId,this.getX(),this.getY(), Math.toRadians(count)));
            bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.LIGHT_BLUE,this.monsterId,this.getX(),this.getY(),Math.toRadians(count+180)));
        }
        this.count += 10;
        return bullets;
    }
}
