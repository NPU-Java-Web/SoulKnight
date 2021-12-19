package org.example.common.model.monster.entity;

import org.example.common.model.bullet.Bullet;
import org.example.common.model.monster.Monster;

import java.util.List;
import java.util.Vector;

public class Monster8 extends Monster {
    public static final int MONSTER_TYPE = 8;
    public static final int SPEED = 3;
    public static final int BLOOD = 100;
    public static final int STATE = 0;
    public static final int VISIBILITY = 300;
    public static final int REWARD = 50;

    public Monster8(String monsterId, Integer x, Integer y, Double angle){
        super(MONSTER_TYPE, monsterId, x, y, angle, SPEED, BLOOD, STATE, VISIBILITY, REWARD);
    }
    @Override
    public List<Bullet> shoot(){
        return null;
    }
}
