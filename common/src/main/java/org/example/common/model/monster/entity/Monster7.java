package org.example.common.model.monster.entity;

import org.example.common.model.bullet.Bullet;
import org.example.common.model.monster.Monster;

import java.util.List;
import java.util.Vector;

public class Monster7 extends Monster {
    public static final int MONSTER_TYPE = 7;
    public static final int SPEED = 3;
    public static final int BLOOD = 100;
    public static final int STATE = 0;
    public static final int VISIBILITY = 300;
    public static final int REWARD = 50;

    public Monster7(String monsterId, Integer x, Integer y, Double angle){
        super(MONSTER_TYPE, monsterId, x, y, angle, SPEED, BLOOD, STATE, VISIBILITY, REWARD);
    }
    @Override
    //boss2号
    //为5-6号小怪子弹方式的集合
    public List<Bullet> shoot(){
        return null;
    }
}
