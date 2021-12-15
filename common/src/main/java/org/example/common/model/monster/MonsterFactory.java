package org.example.common.model.monster;

import lombok.NoArgsConstructor;
import org.example.common.config.GameConfig;
import org.example.common.model.monster.entity.Monster1;

@NoArgsConstructor
public class MonsterFactory {
    public static Monster makeMonster(GameConfig.MonsterType monsterType,
                                      String monsterId, Integer x, Integer y, Double angle){
        switch (monsterType){
            case ONE:
                return new Monster1(monsterId,x,y,angle);
            default:
                return new Monster1(monsterId,x,y,angle);
        }
    }
}
