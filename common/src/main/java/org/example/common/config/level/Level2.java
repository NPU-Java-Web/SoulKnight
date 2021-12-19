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
        super(1, 1000, new boolean[1001][1001], new Monster[]{
                MonsterFactory.makeMonster(GameConfig.MonsterType.ONE, "m1", 300, 300, 90.0),
                MonsterFactory.makeMonster(GameConfig.MonsterType.ONE, "m2", 700, 700, 270.0)
        });
        initWall();
    }

    private void initWall(){
        /**上方*/
        for(int tempX = 0; tempX <= 1000; tempX++){
            for(int tempY = 0; tempY <= 110; tempY++){
                terrain[tempX][tempY] = true;
            }
        }
        /**左上方*/
        for(int tempX = 0; tempX <= 130; tempX++){
            for(int tempY = 0; tempY <= 470; tempY++){
                terrain[tempX][tempY] = true;
            }
        }
        /**右上方*/
        for(int tempX = 900; tempX <= 1000; tempX++){
            for(int tempY = 0; tempY <= 1000; tempY++){
                terrain[tempX][tempY] = true;
            }
        }

        /**下方*/
        for(int tempX = 0; tempX <= 1000; tempX++){
            for(int tempY = 850; tempY <= 1000; tempY++){
                terrain[tempX][tempY] = true;
            }
        }
        /**左下方*/
        for(int tempX = 0; tempX <= 130; tempX++){
            for(int tempY = 580; tempY <= 1000; tempY++){
                terrain[tempX][tempY] = true;
            }
        }

        /**中间墙体*/
        for(int tempX = 394; tempX <= 607; tempX++){
            for(int tempY = 235; tempY <= 320; tempY++){
                terrain[tempX][tempY] = true;
            }
        }
        for(int tempX = 480; tempX <= 522; tempX++){
            for(int tempY = 320; tempY <= 492; tempY++){
                terrain[tempX][tempY] = true;
            }
        }

        /**左下角墙体*/
        for(int tempX = 309; tempX <= 352; tempX++){
            for(int tempY = 404; tempY <= 534; tempY++){
                terrain[tempX][tempY] = true;
            }
        }
        for(int tempX = 309; tempX <= 476; tempX++){
            for(int tempY = 535; tempY <= 620; tempY++){
                terrain[tempX][tempY] = true;
            }
        }

        /**右下角墙体*/
        for(int tempX = 647; tempX <= 690; tempX++){
            for(int tempY = 404; tempY <= 534; tempY++){
                terrain[tempX][tempY] = true;
            }
        }
        for(int tempX = 520; tempX <= 690; tempX++){
            for(int tempY = 535; tempY <= 620; tempY++){
                terrain[tempX][tempY] = true;
            }
        }

    }

}
