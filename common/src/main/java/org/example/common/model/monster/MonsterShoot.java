package org.example.common.model.monster;

import org.example.common.config.GameConfig;
import org.example.common.model.bullet.Bullet;
import org.example.common.model.monster.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 怪物放大招的工具类，用于获取生成的子弹
 */
public class MonsterShoot {

    /**
     * Desc:
     * @param monster 将要发射机子弹的Monster
     * @return 发射出的子弹列表
     * @date 2021/12/22 10:05
     */
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
        return new ArrayList<>();
    }

}
