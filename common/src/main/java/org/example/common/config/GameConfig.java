package org.example.common.config;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class GameConfig {

    //背景图片
    public static ImageIcon background = new ImageIcon(System.getProperty("user.dir")+"\\TankBattle\\client\\src\\main\\resources\\background.jpg");
    public static Image player = Toolkit.getDefaultToolkit().createImage("client/src/main/resources/player.png");
    public static String sound = System.getProperty("user.dir")+"\\client\\src\\main\\resources\\帝国进行曲.wav";
    public static final int FPS = 40;
    public static final int SLEEP_TIME = 25;
}
