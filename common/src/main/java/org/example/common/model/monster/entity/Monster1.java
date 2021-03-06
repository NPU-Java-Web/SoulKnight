package org.example.common.model.monster.entity;

import org.example.common.config.GameConfig;
import org.example.common.model.bullet.Bullet;
import org.example.common.model.bullet.BulletFactory;
import org.example.common.model.monster.Monster;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Monster1 extends Monster {
    public static final int MONSTER_TYPE = 1;
    public static final int SPEED = 5;
    public static final int BLOOD = 40;
    public static final int CD = 3000;
    public static final int VISIBILITY = 400;
    public static final int REWARD = 30;

    public Monster1(String monsterId, Integer x, Integer y, Double angle) {
        super(MONSTER_TYPE, monsterId, x, y, angle, SPEED, BLOOD, CD, VISIBILITY, REWARD);
    }

    public static List<Bullet> shoot(Monster monster) {
        return new ArrayList<>();
    }

}