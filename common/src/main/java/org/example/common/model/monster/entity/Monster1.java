package org.example.common.model.monster.entity;

import org.example.common.model.monster.Monster;

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

}