package org.example.common.config;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * 配置文件
 */
public class GameConfig {

    //背景图片
    public static ImageIcon background = new ImageIcon(System.getProperty("user.dir")+"\\TankBattle\\client\\src\\main\\resources\\background.jpg");
    //玩家图片
    public static Image player = Toolkit.getDefaultToolkit().createImage("client/src/main/resources/player.png");

    public static Image gamebackground1 = Toolkit.getDefaultToolkit().createImage("client/src/main/resources/map/map1.jpg");

    public static Image gamebackground2 = Toolkit.getDefaultToolkit().createImage("client/src/main/resources/map/map2.jpg");

    public static Image gamebackground3 = Toolkit.getDefaultToolkit().createImage("client/src/main/resources/map/map3.jpg");
    //背景音乐路径
    public static String sound = System.getProperty("user.dir")+"\\client\\src\\main\\resources\\帝国进行曲.wav";
    //游戏帧率（从gameStartCore读取result的速度）
    public static final int FPS = 200;
    //client向serve发送数据的速度（越小表示发送越快）
    public static final int SLEEP_TIME = 30;

    public enum PlayerType
    {
        Classic;
    }
}
