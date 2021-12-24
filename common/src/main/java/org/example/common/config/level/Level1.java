package org.example.common.config.level;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.common.config.GameConfig;
import org.example.common.model.monster.Monster;
import org.example.common.model.monster.MonsterFactory;

import java.util.ArrayList;
import java.util.LinkedList;

@EqualsAndHashCode(callSuper = true)
@Data
public class Level1 extends Level {

    public Level1() {
        super(1, 1000, new boolean[1001][1001], new LinkedList<>());

        initialMonsters.add(MonsterFactory.makeMonster(GameConfig.MonsterType.MONSTER_ONE, "m1", 300, 350, 90.0));
        initialMonsters.add(MonsterFactory.makeMonster(GameConfig.MonsterType.MONSTER_ONE, "m2", 700, 700, 270.0));
        initialMonsters.add(MonsterFactory.makeMonster(GameConfig.MonsterType.MONSTER_ONE, "m3", 710, 400, 270.0));
        initialMonsters.add(MonsterFactory.makeMonster(GameConfig.MonsterType.MONSTER_TWO, "m4", 200, 300, 270.0));
        initialMonsters.add(MonsterFactory.makeMonster(GameConfig.MonsterType.MONSTER_THREE, "m5", 350, 380, 270.0));

        initialMonsters.add(MonsterFactory.makeMonster(GameConfig.MonsterType.MONSTER_TWO, "m6", 300, 400, 270.0));
        initialMonsters.add(MonsterFactory.makeMonster(GameConfig.MonsterType.MONSTER_TWO, "m7", 350, 380, 270.0));
        initialMonsters.add(MonsterFactory.makeMonster(GameConfig.MonsterType.MONSTER_THREE, "m8", 400, 300, 270.0));
        initialMonsters.add(MonsterFactory.makeMonster(GameConfig.MonsterType.MONSTER_FOUR, "m9", 450, 300, 270.0));
        initialMonsters.add(MonsterFactory.makeMonster(GameConfig.MonsterType.MONSTER_FOUR, "m10", 500, 350, 270.0));

        initialMonsters.add(MonsterFactory.makeMonster(GameConfig.MonsterType.MONSTER_THREE, "m11", 550, 380, 270.0));
        initialMonsters.add(MonsterFactory.makeMonster(GameConfig.MonsterType.MONSTER_FOUR, "m12", 600, 360, 270.0));
        initialMonsters.add(MonsterFactory.makeMonster(GameConfig.MonsterType.MONSTER_FOUR, "m13", 650, 300, 270.0));
        initialMonsters.add(MonsterFactory.makeMonster(GameConfig.MonsterType.MONSTER_FOUR, "m14", 760, 330, 270.0));
        initialMonsters.add(MonsterFactory.makeMonster(GameConfig.MonsterType.MONSTER_FIVE, "m15", 710, 400, 270.0));

        initialMonsters.add(MonsterFactory.makeMonster(GameConfig.MonsterType.MONSTER_FIVE, "m16", 500, 300, 270.0));
        initialMonsters.add(MonsterFactory.makeMonster(GameConfig.MonsterType.MONSTER_FIVE, "m17", 400, 360, 270.0));
        initialMonsters.add(MonsterFactory.makeMonster(GameConfig.MonsterType.MONSTER_FIVE, "m18", 300, 340, 270.0));
//        initialMonsters.add(MonsterFactory.makeMonster(GameConfig.MonsterType.MONSTER_SIX, "m19", 760, 300, 270.0));
        initialMonsters.add(MonsterFactory.makeMonster(GameConfig.MonsterType.MONSTER_SIX, "m20", 800, 300, 270.0));

        initWall();
    }

    private void initWall() {

        /**中心墙体*/
        for (int tempX = 450; tempX <= 537; tempX++) {
            for (int tempY = 370; tempY <= 455; tempY++) {
                terrain[tempX][tempY] = true;
            }
        }

        for (int tempX = 370; tempX <= 622; tempX++) {
            for (int tempY = 455; tempY <= 538; tempY++) {
                terrain[tempX][tempY] = true;
            }
        }

        for (int tempX = 580; tempX <= 622; tempX++) {
            for (int tempY = 456; tempY <= 664; tempY++) {
                terrain[tempX][tempY] = true;
            }
        }
        for (int tempX = 370; tempX <= 412; tempX++) {
            for (int tempY = 456; tempY <= 664; tempY++) {
                terrain[tempX][tempY] = true;
            }
        }

        for (int tempX = 286; tempX <= 412; tempX++) {
            for (int tempY = 580; tempY <= 664; tempY++) {
                terrain[tempX][tempY] = true;
            }
        }
        for (int tempX = 580; tempX <= 705; tempX++) {
            for (int tempY = 580; tempY <= 664; tempY++) {
                terrain[tempX][tempY] = true;
            }
        }

        /**四周墙体*/

        /**左上方*/
        for (int tempX = 0; tempX <= 412; tempX++) {
            for (int tempY = 0; tempY <= 201; tempY++) {
                terrain[tempX][tempY] = true;
            }
        }
        for (int tempX = 0; tempX <= 78; tempX++) {
            for (int tempY = 0; tempY <= 450; tempY++) {
                terrain[tempX][tempY] = true;
            }
        }

        /**右上方*/
        for (int tempX = 582; tempX <= 1000; tempX++) {
            for (int tempY = 0; tempY <= 200; tempY++) {
                terrain[tempX][tempY] = true;
            }
        }
        for (int tempX = 918; tempX <= 1000; tempX++) {
            for (int tempY = 0; tempY <= 450; tempY++) {
                terrain[tempX][tempY] = true;
            }
        }

        /**左下方*/
        for (int tempX = 0; tempX <= 82; tempX++) {
            for (int tempY = 572; tempY <= 1000; tempY++) {
                terrain[tempX][tempY] = true;
            }
        }
        for (int tempX = 0; tempX <= 414; tempX++) {
            for (int tempY = 790; tempY <= 1000; tempY++) {
                terrain[tempX][tempY] = true;
            }
        }

        /**右下方*/
        for (int tempX = 917; tempX <= 1000; tempX++) {
            for (int tempY = 572; tempY <= 1000; tempY++) {
                terrain[tempX][tempY] = true;
            }
        }
        for (int tempX = 578; tempX <= 1000; tempX++) {
            for (int tempY = 790; tempY <= 1000; tempY++) {
                terrain[tempX][tempY] = true;
            }
        }


    }

}
