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
 * 服务器返回信息给客户端的格式
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private List<Player> players;
    private List<Bullet> bullets;
    private List<Monster> monsters;
    private List<Animation> animations;
    private Integer mapType;

}
