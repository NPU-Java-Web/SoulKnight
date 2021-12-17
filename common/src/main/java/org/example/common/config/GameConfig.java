package org.example.common.config;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * 配置文件
 */
public class GameConfig {

    /**背景图片*/
    public static ImageIcon background = new ImageIcon(System.getProperty("user.dir")+"\\TankBattle\\client\\src\\main\\resources\\background.jpg");
    //玩家图片（1）
    public static Image player = Toolkit.getDefaultToolkit().createImage("client/src/main/resources/heros/未标题-1.png");
    public static ImageIcon playerShow = new ImageIcon(System.getProperty("user.dir")+"\\TankBattle\\client\\src\\main\\resources\\heros\\未标题-1.png");
    //玩家图片（2）
    public static Image player2 = Toolkit.getDefaultToolkit().createImage("client/src/main/resources/heros/未标题-6.png");
    public static ImageIcon playerShow2 = new ImageIcon(System.getProperty("user.dir")+"\\TankBattle\\client\\src\\main\\resources\\heros\\未标题-6.png");
    //玩家图片（3）
    public static Image player3 = Toolkit.getDefaultToolkit().createImage("client/src/main/resources/heros/未标题-9.png");
    public static ImageIcon playerShow3 = new ImageIcon(System.getProperty("user.dir")+"\\TankBattle\\client\\src\\main\\resources\\heros\\未标题-9.png");
    //游戏背景图片
    public static Image gamebackground1 = Toolkit.getDefaultToolkit().createImage("client/src/main/resources/map/map1.jpg");

    public static Image gamebackground2 = Toolkit.getDefaultToolkit().createImage("client/src/main/resources/map/map2.jpg");

    public static Image gamebackground3 = Toolkit.getDefaultToolkit().createImage("client/src/main/resources/map/map3.jpg");
    //子弹图片(1)
    public static Image Monster1 = Toolkit.getDefaultToolkit().createImage("client/src/main/resources/heros/Monster-1.png");

    public static Image bullet1 = Toolkit.getDefaultToolkit().createImage("client/src/main/resources/bullet/bullet1.png");
    //背景音乐路径
    public static String sound = System.getProperty("user.dir")+"\\client\\src\\main\\resources\\帝国进行曲.wav";
    //游戏帧率（从gameStartCore读取result的速度）
    public static final int FPS = 300;
    //client向serve发送数据的速度（越小表示发送越快）
    public static final int SLEEP_TIME = 20;
    //玩家id
    public static String playerId="";
    //玩家种类
    public static int playerType = 1;
    //玩家蓝量
    public static double playerStrength = 0;

    public static boolean flag = true;
    public static class PlayerType
    {
        //经典人物
        public static final int Classic= 1;
        //重装人物
        public static final int Armor = 2;
        //轻型人物
        public static final int Assassin = 3;

    }

    public class BulletType
    {
        public static final int Classic= 1;
        //重装子弹
        public static final int Armor = 2;
        //轻型子弹
        public static final int Assassin = 3;
    }

    public enum MonsterType
    {
        //怪物一号
        ONE;
    }
}
