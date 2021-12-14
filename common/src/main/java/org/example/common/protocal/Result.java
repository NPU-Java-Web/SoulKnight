package org.example.common.protocal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.entity.Bullet;
import org.example.common.entity.Monster;
import org.example.common.entity.Player;

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
    private Integer mapType;
}
