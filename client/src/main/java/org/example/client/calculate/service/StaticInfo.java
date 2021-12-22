package org.example.client.calculate.service;

import org.example.client.GameStartCore;
import org.example.client.display.GamePanel;
import org.example.client.display.MainPanel;
import org.example.common.config.level.Level;

/**
 * 在启动后将关键类的对象存储该类的静态变量以便于调用
 *
 * @see GameStartCore
 * @see GamePanel
 * @see MainPanel
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
