package org.example.common.model.player;

import lombok.NoArgsConstructor;
import org.example.common.config.GameConfig;
import org.example.common.model.player.entity.Player1;

@NoArgsConstructor
public class PlayerFactory {

    public static Player makePlayer(int playerType, String playerId, Integer x, Integer y, Double angle){
        switch (playerType){
            case GameConfig.PlayerType.Classic:
                return new Player1(playerId,x,y,angle);
            default:
                return new Player1(playerId,x,y,angle);
        }
    }
}
