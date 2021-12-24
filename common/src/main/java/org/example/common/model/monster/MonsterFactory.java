package org.example.common.model.monster;

import lombok.NoArgsConstructor;

/**
 * 怪物工厂
 */
@NoArgsConstructor
public class MonsterFactory {
    public static Monster makeMonster(int monsterType,
                                      String monsterId, Integer x, Integer y, Double angle) {
        try {
            Class<?> c = Class.forName("org.example.common.model.monster.entity.Monster" + monsterType);
            return (Monster) c.getDeclaredConstructor(String.class, Integer.class, Integer.class, Double.class)
                    .newInstance(monsterId, x, y, angle);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
