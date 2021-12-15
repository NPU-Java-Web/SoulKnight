package org.example.client.calculate.service;

import org.example.client.GameStartCore;
import org.example.common.config.level.Level;

public class StaticInfo {

    public static GameStartCore gameStartCore;

    public static Level gameLevel;

    public static void setGameStartCore(GameStartCore gameStartCore){
        StaticInfo.gameStartCore = gameStartCore;
    }

}
