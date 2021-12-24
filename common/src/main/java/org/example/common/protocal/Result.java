package org.example.common.protocal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.model.animation.Animation;
import org.example.common.model.bullet.Bullet;
import org.example.common.model.monster.Monster;
import org.example.common.model.player.Player;

import java.util.List;

/**
 * 服务器返回信息给客户端的格式，包含一帧的信息
 *
 * @author 廖菁璞
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    /**
     * 此时场上的所有玩家
     */
    private List<Player> players;
    /**
     * 此时场上的所有子弹
     */
    private List<Bullet> bullets;
    /**
     * 此时场上的所有怪物
     */
    private List<Monster> monsters;
    /**
     * 此时场上的所有动画
     */
    private List<Animation> animations;
    /**
     * 当前地图编号
     */
    private Integer mapType;

}
