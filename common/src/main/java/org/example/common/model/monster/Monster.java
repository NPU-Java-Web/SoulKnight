package org.example.common.model.monster;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 怪物实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Monster {
    /**
     * 怪物的种类
     */
    protected Integer monsterType;
    /**
     * 怪物的ID
     */
    protected String monsterId;
    /**
     * 怪物的X坐标
     */
    protected Integer x;
    /**
     * 怪物的Y坐标
     */
    protected Integer y;
    /**
     * 怪物面向的角度
     */
    protected Double angle;
    /**
     * 怪物的最大速度
     */
    protected Integer speed;
    /**
     * 怪物当前的血量
     */
    protected Integer blood;
    /**
     * 怪物大招冷却
     */
    protected Integer CD;
    /**
     * 怪物的视力范围
     */
    protected Integer visibility;
    /**
     * 击杀怪物后的奖励
     */
    protected Integer reward;

}
