package org.example.client.display;

import org.example.client.GameStartCore;

public class StaticInfo {

    public static GameStartCore gameStartCore;

    public static void setGameStartCore(GameStartCore gameStartCore){
        StaticInfo.gameStartCore = gameStartCore;
    }

}
