package org.example.common.config.level;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.model.monster.Monster;

import java.util.Queue;

/**
 * 地图实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Level {
    /**
     * 地图编号
     */
    protected int number;
    /**
     * 地图边长
     */
    protected int sideLength;
    /**
     * 地形信息
     */
    protected boolean[][] terrain;
    /**
     * 初始的怪物信息
     */
    protected Queue<Monster> initialMonsters;
}
