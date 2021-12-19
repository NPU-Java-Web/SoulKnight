package org.example.client.calculate.service;

import org.example.client.GameStartCore;
import org.example.client.display.GamePanel;
import org.example.client.display.MainPanel;
import org.example.common.config.level.Level;

/**
 * 将所需对象地址静态存储
 */
public class StaticInfo {

    public static GameStartCore gameStartCore;

    public static void setGameStartCore(GameStartCore gameStartCore){
        StaticInfo.gameStartCore = gameStartCore;
    }

    public static Boolean isrunning = false;

    public static GamePanel gamePanel;

    public static MainPanel mainPanel;

}
