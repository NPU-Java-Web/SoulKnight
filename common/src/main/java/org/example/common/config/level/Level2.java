package org.example.common.config.level;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.common.config.GameConfig;
import org.example.common.model.monster.Monster;
import org.example.common.model.monster.MonsterFactory;

@EqualsAndHashCode(callSuper = true)
@Data
public class Level2 extends Level {

    public Level2() {
        super(2, 1000, new boolean[1001][1001], new Monster[]{
                MonsterFactory.makeMonster(GameConfig.MonsterType.ONE, "m3", 600, 300, 90.0),
                MonsterFactory.makeMonster(GameConfig.MonsterType.ONE, "m4", 700, 200, 270.0)
        });
        initWall();
    }

    private void initWall(){

    }

}
