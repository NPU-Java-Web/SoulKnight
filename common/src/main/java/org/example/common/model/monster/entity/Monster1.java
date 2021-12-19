package org.example.common.model.monster.entity;

import org.example.common.config.GameConfig;
import org.example.common.model.bullet.Bullet;
import org.example.common.model.bullet.BulletFactory;
import org.example.common.model.monster.Monster;

import java.util.Vector;

public class Monster1 extends Monster {
    public static final int MONSTER_TYPE = 1;
    public static final int SPEED = 5;
    public static final int BLOOD = 40;
    public static final int STATE = 0;
    public static final int VISIBILITY = 300;
    public static final int REWARD = 30;

    public Monster1(String monsterId, Integer x, Integer y, Double angle){
        super(MONSTER_TYPE, monsterId, x, y, angle, SPEED, BLOOD, STATE, VISIBILITY, REWARD);
    }

    @Override
    public Vector<Bullet> shoot(){
        return null;
    }

    public Vector<Bullet> shoot(int x, int y){
        Vector<Bullet> bullets = new Vector<Bullet>();
        int tempX = x-this.getX();
        int tempY = y-this.getY();
        double l = Math.sqrt(Math.pow(Math.abs(tempX),2)+Math.pow(Math.abs(tempY),2));
        double angle;
        if(y>0){
            angle = Math.acos(tempX/l);
        } else {
            angle = 2*Math.PI - Math.acos(tempY/l);
        }
        bullets.add(BulletFactory.makeBullet(GameConfig.BulletType.COLORFUL, this.getMonsterId(),this.getX(),this.getX(),angle));
        return bullets;
    }

}