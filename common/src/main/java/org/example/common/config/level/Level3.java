package org.example.common.config.level;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.common.config.GameConfig;
import org.example.common.model.monster.Monster;
import org.example.common.model.monster.MonsterFactory;

@EqualsAndHashCode(callSuper = true)
@Data
public class Level3 extends Level {

    public Level3() {
        super(3, 1000, new boolean[1001][1001], new Monster[]{
                MonsterFactory.makeMonster(GameConfig.MonsterType.MONSTER_ONE, "m7", 300, 300, 90.0),
                MonsterFactory.makeMonster(GameConfig.MonsterType.MONSTER_ONE, "m8", 700, 700, 270.0),
                MonsterFactory.makeMonster(GameConfig.MonsterType.MONSTER_THREE, "m9",500,300,0.0),
                MonsterFactory.makeMonster(GameConfig.MonsterType.MONSTER_THREE, "m10",300,700,0.0),
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
            for(int tempY = 0; tempY <= 472; tempY++){
                terrain[tempX][tempY] = true;
            }
        }
        /**右上方*/
        for(int tempX = 762; tempX <= 1000; tempX++){
            for(int tempY = 0; tempY <= 472; tempY++){
                terrain[tempX][tempY] = true;
            }
        }

        /**左下方*/
        for(int tempX = 0; tempX <= 130; tempX++){
            for(int tempY = 580; tempY <= 1000; tempY++){
                terrain[tempX][tempY] = true;
            }
        }
        /**右下方*/
        for(int tempX = 763; tempX <= 1000; tempX++){
            for(int tempY = 580; tempY <= 1000; tempY++){
                terrain[tempX][tempY] = true;
            }
        }

        /**下方*/
        for(int tempX = 0; tempX <= 1000; tempX++){
            for(int tempY = 840; tempY <= 1000; tempY++){
                terrain[tempX][tempY] = true;
            }
        }

        /**左上障碍物*/
        for(int tempX = 300; tempX <= 426; tempX++){
            for(int tempY = 326; tempY <= 407; tempY++){
                terrain[tempX][tempY] = true;
            }
        }
        for(int tempX = 300; tempX <= 342; tempX++){
            for(int tempY = 407; tempY <= 492; tempY++){
                terrain[tempX][tempY] = true;
            }
        }

        /**右上方障碍物*/
        for(int tempX = 470; tempX <= 594; tempX++){
            for(int tempY = 326; tempY <= 407; tempY++){
                terrain[tempX][tempY] = true;
            }
        }
        for(int tempX = 552; tempX <= 594; tempX++){
            for(int tempY = 407; tempY <= 492; tempY++){
                terrain[tempX][tempY] = true;
            }
        }

        /**左下方障碍物*/
        for(int tempX = 300; tempX <= 342; tempX++){
            for(int tempY = 537; tempY <= 702; tempY++){
                terrain[tempX][tempY] = true;
            }
        }
        for(int tempX = 342; tempX <= 426; tempX++){
            for(int tempY = 620; tempY <= 702; tempY++){
                terrain[tempX][tempY] = true;
            }
        }

        /**右下方障碍物*/
        for(int tempX = 470; tempX <= 554; tempX++){
            for(int tempY = 620; tempY <= 702; tempY++){
                terrain[tempX][tempY] = true;
            }
        }
        for(int tempX = 554; tempX <= 593; tempX++){
            for(int tempY = 538; tempY <= 702; tempY++){
                terrain[tempX][tempY] = true;
            }
        }

    }

}
