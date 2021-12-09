package org.example.common.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.config.level.Level1;

@Data
@NoArgsConstructor
public class Level {
    private int number;
    private int sizeLength;
    private boolean[][] terrain;
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
                this.initialMonsters=Level1.INITIAL_MONSTERS;
                break;
        }

    }
}
