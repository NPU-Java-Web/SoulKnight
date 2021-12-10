package org.example.server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.common.entity.Monster;

@Data
@AllArgsConstructor
public abstract class World {
    private final int number;
    private final int sizeLength;
    private final boolean[][] terrain;
    private final Monster[] initialMonsters;
    private volatile String GlobalInfo;

}
