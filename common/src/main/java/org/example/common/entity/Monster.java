package org.example.common.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.config.monster.Monster1;

@Data
@NoArgsConstructor
public class Monster {
    /**
     * 怪物的种类
     */
    private Integer monsterType;
    /**
     * 怪物的ID
     */
    private String monsterId;
    /**
     * 怪物的X坐标
     */
    private Integer x;
    /**
     * 怪物的Y坐标
     */
    private Integer y;
    /**
     * 怪物面向的角度
     */
    private Double angle;
    /**
     * 怪物的最大速度
     */
    private Integer speed;
    /**
     * 怪物当前的血量
     */
    private Integer blood;
    /**
     * 怪物当前的状态
     */
    private Integer state;
    /**
     * 怪物的视力范围
     */
    private Integer visibility;
    /**
     * 击杀怪物后的奖励
     */
    private Integer reward;

    public Monster(Integer monsterType, String monsterId, Integer x, Integer y, Double angle) {
        switch (monsterType) {
            case 2:
                //保留备用
                break;
            case 1:
            default:
                this.monsterType = Monster1.MONSTER_TYPE;
                this.speed = Monster1.SPEED;
                this.blood = Monster1.BLOOD;
                this.state = Monster1.STATE;
                this.visibility = Monster1.VISIBILITY;
                this.reward = Monster1.REWARD;
                break;
        }

        this.monsterId = monsterId;
        this.x = x;
        this.y = y;
        this.angle = angle;
    }
}
