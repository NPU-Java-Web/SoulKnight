package org.example.common.config.level;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.common.config.GameConfig;
import org.example.common.model.monster.Monster;
import org.example.common.model.monster.MonsterFactory;

@EqualsAndHashCode(callSuper = true)
@Data
public class Level1 extends Level {

    public Level1() {
        super(1, 1000, new boolean[1000][1000], new Monster[]{
                MonsterFactory.makeMonster(GameConfig.MonsterType.ONE, "m1", 300, 300, 90.0),
                MonsterFactory.makeMonster(GameConfig.MonsterType.ONE, "m2", 500, 500, 180.0),
                MonsterFactory.makeMonster(GameConfig.MonsterType.ONE, "m3", 700, 700, 270.0)
        });
    }
}
