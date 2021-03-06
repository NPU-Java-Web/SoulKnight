package org.example.common.model.player;

import lombok.NoArgsConstructor;
import org.example.common.config.GameConfig;
import org.example.common.model.player.entity.PLayer3;
import org.example.common.model.player.entity.Player1;
import org.example.common.model.player.entity.Player2;

/**
 * 创建人物 工厂模式
 */
@NoArgsConstructor
public class PlayerFactory {

    public static Player makePlayer(int playerType, String playerId, Integer x, Integer y, Double angle) {
        switch (playerType) {
            case GameConfig.PlayerType.CLASSIC:
                return new Player1(playerId, x, y, angle);
            case GameConfig.PlayerType.ARMOR:
                return new Player2(playerId, x, y, angle);
            case GameConfig.PlayerType.ASSASSIN:
                return new PLayer3(playerId, x, y, angle);
            default:
                return new Player1(playerId, x, y, angle);
        }
    }
}
