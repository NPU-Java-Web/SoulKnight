package org.example.common.config.level;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.model.monster.Monster;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Level {
    public int number;
    public int sideLength;
    public boolean[][] terrain;
    public Monster[] initialMonsters;
}
