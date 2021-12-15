package org.example.client.calculate.service;

import org.example.client.GameStartCore;
import org.example.client.display.GamePanel;
import org.example.client.display.MainPanel;

/**
 * 将所需地址静态存储
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
