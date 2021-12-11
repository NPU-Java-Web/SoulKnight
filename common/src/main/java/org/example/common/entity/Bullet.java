package org.example.common.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.config.bullet.Bullet1;

@Data
@NoArgsConstructor
public class Bullet {
    /**
     * 子弹种类
     */
    private Integer bulletType;
    /**
     * 子弹ID（由服务器分配，客户端不用管）
     */
    private String bulletId;
    /**
     * 发射出子弹的玩家ID
     */
    private String playerId;
    /**
     * 子弹发射时的X坐标（不能更改）
     */
    private Integer x;
    /**
     * 子弹发射时的Y坐标（不能更改）
     */
    private Integer y;
    /**
     * 子弹面向的角度
     */
    private Double angle;
    /**
     * 子弹的最大速度
     */
    private Integer speed;
    /**
     * 子弹的半径
     */
    private Integer radius;
    /**
     * 子弹的伤害
     */
    private Integer power;

    /**
     * 子弹创建的时刻（时间戳）
     */
    private Long createTime;

    public Bullet(Integer bulletType, String playerId, Integer x, Integer y, Double angle) {
        switch (bulletType) {
            case 2:
                //保留备用
                break;
            case 1:
            default:
                this.bulletType = Bullet1.BULLET_TYPE;
                this.speed = Bullet1.SPEED;
                this.radius = Bullet1.RADIUS;
                this.power = Bullet1.POWER;
                break;
        }
        this.bulletId = "待分配";
        this.playerId = playerId;
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.createTime = System.currentTimeMillis();
    }
}
