package org.example.common.model.monster;

import lombok.NoArgsConstructor;

/**
 * 怪物 工厂模式
 */
@NoArgsConstructor
public class MonsterFactory {
     /**
      * Desc:
      * @param monsterType 怪物种类
      * @param monsterId 怪物id
      * @param x 怪物初始x坐标
      * @param y 怪物初始y坐标
      * @param angle 怪物朝向
      * @return 怪物实例
      * @date 2021/12/21 10:04
      */
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
