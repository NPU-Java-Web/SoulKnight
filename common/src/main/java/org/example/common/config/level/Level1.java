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
                MonsterFactory.makeMonster(GameConfig.MonsterType.ONE, "m3", 500, 500, 270.0)
        });
        initWall();
    }

    private void initWall(){
        for(int tempX = 460; tempX <= 545; tempX++){
            for(int tempY = 400; tempY <= 485; tempY++){
                terrain[tempX][tempY] = true;
            }
        }

        for(int tempX = 378; tempX <= 630; tempX++){
            for(int tempY = 485; tempY <= 568; tempY++){
                terrain[tempX][tempY] = true;
            }
        }

        for(int tempX = 588; tempX <= 630; tempX++){
            for(int tempY = 568; tempY <= 694; tempY++){
                terrain[tempX][tempY] = true;
            }
        }
        for(int tempX = 378; tempX <= 420; tempX++){
            for(int tempY = 568; tempY <= 694; tempY++){
                terrain[tempX][tempY] = true;
            }
        }

        for(int tempX = 294; tempX <= 420; tempX++){
            for(int tempY = 610; tempY <= 694; tempY++){
                terrain[tempX][tempY] = true;
            }
        }
        for(int tempX = 588; tempX <= 713; tempX++){
            for(int tempY = 610; tempY <= 694; tempY++){
                terrain[tempX][tempY] = true;
            }
        }
    }

}
