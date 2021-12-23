package org.example.common.model.monster;

import org.example.common.config.GameConfig;
import org.example.common.model.bullet.Bullet;
import org.example.common.model.monster.entity.*;

import java.util.List;
import java.util.Map;


public class MonsterShoot {

    public static List<Bullet> shoot(Monster monster) {
        if (monster.monsterType == GameConfig.MonsterType.MONSTER_ONE) {
            return (Monster1.shoot(monster));
        } else if (monster.monsterType == GameConfig.MonsterType.MONSTER_TWO) {
            return (Monster2.shoot(monster));
        } else if (monster.monsterType == GameConfig.MonsterType.MONSTER_THREE) {
            return (Monster3.shoot(monster));
        } else if (monster.monsterType == GameConfig.MonsterType.MONSTER_FOUR) {
            return (Monster4.shoot(monster));
        } else if (monster.monsterType == GameConfig.MonsterType.MONSTER_FIVE) {
            return (Monster5.shoot(monster));
        } else if (monster.monsterType == GameConfig.MonsterType.MONSTER_SIX) {
            return (Monster6.shoot(monster));
        } else if (monster.monsterType == GameConfig.MonsterType.MONSTER_SEVEN) {
            return (Monster7.shoot(monster));
        } else if (monster.monsterType == GameConfig.MonsterType.MONSTER_EIGHT) {
            return (Monster8.shoot(monster));
        }
        return null;
        /*try {
            for(int i=1; i<=8; i++){
                Class<?> c = Class.forName("org.example.common.model.monster.entity.Monster" + i);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;*/
    }

}
