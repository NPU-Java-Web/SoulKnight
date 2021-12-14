package org.example.common.config.level;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.config.GameConfig;
import org.example.common.model.monster.Monster;
import org.example.common.model.monster.MonsterFactory;

@NoArgsConstructor
public class Level1 {
    public static final int NUMBER = 1;
    public static final int SIDE_LENGTH = 1000;
    public static final boolean[][] TERRAIN = new boolean[SIDE_LENGTH][SIDE_LENGTH];
    public static final Monster[] INITIAL_MONSTERS={
            MonsterFactory.makeMonster(GameConfig.MonsterType.ONE,"m1",300,300,90.0),
            MonsterFactory.makeMonster(GameConfig.MonsterType.ONE, "m2", 500, 500, 180.0),
            MonsterFactory.makeMonster(GameConfig.MonsterType.ONE,"m3",500,500,270.0)
    };
}
