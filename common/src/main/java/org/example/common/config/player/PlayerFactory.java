package org.example.common.config.player;

import lombok.NoArgsConstructor;
import org.example.common.config.GameConfig;
import org.example.common.entity.Player;

@NoArgsConstructor
public class PlayerFactory {

    public static Player makePlayer(GameConfig.PlayerType playerType, String playerId, Integer x, Integer y, Double angle){
        switch (playerType){
            case Classic:
                return new Player1(playerId,x,y,angle);
            default:
                return new Player1(playerId,x,y,angle);
        }
    }
}
