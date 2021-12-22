package org.example.common.config.level;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.model.monster.Monster;

import java.util.List;
import java.util.Queue;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Level {
    protected int number;
    protected int sideLength;
    protected boolean[][] terrain;
    protected Queue<Monster> initialMonsters;
}
