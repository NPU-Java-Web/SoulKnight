package org.example.common.model.bullet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 子弹实体类
 *
 * @author 廖菁璞
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bullet {
    /**
     * 子弹种类
     */
    private Integer bulletType;
    /**
     * 子弹ID（由服务器分配，客户端不用管）
     */
    protected String bulletId;
    /**
     * 发射出子弹的玩家ID
     */
    protected String playerId;
    /**
     * 子弹发射时的X坐标（不能更改）
     */
    protected Integer x;
    /**
     * 子弹发射时的Y坐标（不能更改）
     */
    protected Integer y;
    /**
     * 子弹面向的角度
     */
    protected Double angle;
    /**
     * 子弹的最大速度
     */
    protected Integer speed;
    /**
     * 子弹的半径
     */
    protected Integer radius;
    /**
     * 子弹的伤害
     */
    protected Integer power;

    /**
     * 子弹创建的时刻（时间戳）
     */
    protected Long createTime;

}
