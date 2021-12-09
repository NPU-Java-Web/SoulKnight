package org.example.common.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.config.level.Level1;

@Data
@NoArgsConstructor
public class Level {
    /**
     * 关卡编号
     */
    private int number;
    /**
     * 地图边长
     */
    private int sizeLength;
    /**
     * 地图地形（false代表无障碍物，true代表有障碍物）
     */
    private boolean[][] terrain;
    /**
     * 初始的怪物
     */
    private Monster[] initialMonsters;

    public Level(int number) {
        switch (number) {
            case 2:
                //保留备用
                break;
            case 1:
            default:
                this.number = Level1.NUMBER;
                this.sizeLength = Level1.SIDE_LENGTH;
                this.terrain = Level1.TERRAIN;
                this.initialMonsters = Level1.INITIAL_MONSTERS;
                break;
        }

    }
}
