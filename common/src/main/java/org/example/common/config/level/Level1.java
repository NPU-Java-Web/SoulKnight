package org.example.common.config.level;

import org.example.common.entity.Monster;

public class Level1 {
    public static final int NUMBER = 1;
    public static final int SIDE_LENGTH = 1000;
    public static final boolean[][] TERRAIN = new boolean[SIDE_LENGTH][SIDE_LENGTH];

    public static final Monster[] INITIAL_MONSTERS={
            new Monster(1,"m1",300,300,90.0),
            new Monster(1,"m2",500,500,180.0),
            new Monster(1,"m3",500,500,270.0)
    };
}
