package org.example.common.config;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class GameConfig {

    //背景图片
    public static ImageIcon background = new ImageIcon(System.getProperty("user.dir")+"\\TankBattle\\client\\src\\main\\resources\\background.jpg");
    public static Image player = Toolkit.getDefaultToolkit().createImage("client/src/main/resources/player.png");

    public static int fps = 30;

}
