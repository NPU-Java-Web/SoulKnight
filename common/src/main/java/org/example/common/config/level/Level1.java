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
        super(1, 1000, new boolean[1001][1001], new Monster[]{
                MonsterFactory.makeMonster(GameConfig.MonsterType.ONE, "m1", 300, 300, 90.0),
                MonsterFactory.makeMonster(GameConfig.MonsterType.ONE, "m2", 700, 700, 270.0),
                MonsterFactory.makeMonster(GameConfig.MonsterType.ONE, "m3", 710, 300, 270.0)

        });
        initWall();
    }

    private void initWall(){

        /**中心墙体*/
        for(int tempX = 450; tempX <= 537; tempX++){
            for(int tempY = 370; tempY <= 455; tempY++){
                terrain[tempX][tempY] = true;
            }
        }

        for(int tempX = 370; tempX <= 622; tempX++){
            for(int tempY = 455; tempY <= 538; tempY++){
                terrain[tempX][tempY] = true;
            }
        }

        for(int tempX = 580; tempX <= 622; tempX++){
            for(int tempY = 456; tempY <= 664; tempY++){
                terrain[tempX][tempY] = true;
            }
        }
        for(int tempX = 370; tempX <= 412; tempX++){
            for(int tempY = 456; tempY <= 664; tempY++){
                terrain[tempX][tempY] = true;
            }
        }

        for(int tempX = 286; tempX <= 412; tempX++){
            for(int tempY = 580; tempY <= 664; tempY++){
                terrain[tempX][tempY] = true;
            }
        }
        for(int tempX = 580; tempX <= 705; tempX++){
            for(int tempY = 580; tempY <= 664; tempY++){
                terrain[tempX][tempY] = true;
            }
        }

        /**四周墙体*/

        /**左上方*/
        for(int tempX = 0; tempX <= 412; tempX++){
            for(int tempY = 0; tempY <= 201; tempY++){
                terrain[tempX][tempY] = true;
            }
        }
        for(int tempX = 0; tempX <= 78; tempX++){
            for(int tempY = 0; tempY <= 450; tempY++){
                terrain[tempX][tempY] = true;
            }
        }

        /**右上方*/
        for(int tempX = 582; tempX <= 1000; tempX++){
            for(int tempY = 0; tempY <= 200; tempY++){
                terrain[tempX][tempY] = true;
            }
        }
        for(int tempX = 918; tempX <= 1000; tempX++){
            for(int tempY = 0; tempY <= 450; tempY++){
                terrain[tempX][tempY] = true;
            }
        }

        /**左下方*/
        for(int tempX = 0; tempX <= 82; tempX++){
            for(int tempY = 572; tempY <= 1000; tempY++){
                terrain[tempX][tempY] = true;
            }
        }
        for(int tempX = 0; tempX <= 414; tempX++){
            for(int tempY = 790; tempY <= 1000; tempY++){
                terrain[tempX][tempY] = true;
            }
        }

        /**右下方*/
        for(int tempX = 917; tempX <= 1000; tempX++){
            for(int tempY = 572; tempY <= 1000; tempY++){
                terrain[tempX][tempY] = true;
            }
        }
        for(int tempX = 578; tempX <= 1000; tempX++){
            for(int tempY = 790; tempY <= 1000; tempY++){
                terrain[tempX][tempY] = true;
            }
        }



    }

}
