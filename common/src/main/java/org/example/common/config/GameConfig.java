package org.example.common.config;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * 配置文件
 */
public class GameConfig {

    /**背景图片*/
    public static ImageIcon backgroundMenu = new ImageIcon(System.getProperty("user.dir")+"\\client\\src\\main\\resources\\image.jpg");
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
    //怪物（1-8）
    public static Image Monster1 = Toolkit.getDefaultToolkit().createImage("client/src/main/resources/heros/Monster-1.png");
    public static ImageIcon MonsterShow1 = new ImageIcon(System.getProperty("user.dir")+"\\TankBattle\\client\\src\\main\\resources\\heros\\Monster-1.png");

    public static Image Monster2 = Toolkit.getDefaultToolkit().createImage("client/src/main/resources/heros/Monster-2.png");

    public static Image Monster3 = Toolkit.getDefaultToolkit().createImage("client/src/main/resources/heros/Monster-3.png");

    public static Image Monster4 = Toolkit.getDefaultToolkit().createImage("client/src/main/resources/heros/Monster-4.png");

    public static Image Monster5 = Toolkit.getDefaultToolkit().createImage("client/src/main/resources/heros/Monster-5.png");

    public static Image Monster6 = Toolkit.getDefaultToolkit().createImage("client/src/main/resources/heros/boss1.png");

    public static Image Monster7 = Toolkit.getDefaultToolkit().createImage("client/src/main/resources/heros/boss2.png");

    public static Image Monster8 = Toolkit.getDefaultToolkit().createImage("client/src/main/resources/heros/boss3.png");
    public static ImageIcon MonsterShow8 = new ImageIcon(System.getProperty("user.dir")+"\\TankBattle\\client\\src\\main\\resources\\heros\\boss8.png");
    //传送门
    public static Image portal = Toolkit.getDefaultToolkit().createImage("client/src/main/resources/portal/Portal.png");
    //爆炸（1-9）
    public static Image boom1 = Toolkit.getDefaultToolkit().createImage("client/src/main/resources/boom/p1.png");

    public static Image boom2 = Toolkit.getDefaultToolkit().createImage("client/src/main/resources/boom/p2.png");

    public static Image boom3 = Toolkit.getDefaultToolkit().createImage("client/src/main/resources/boom/p3.png");

    public static Image boom4 = Toolkit.getDefaultToolkit().createImage("client/src/main/resources/boom/p4.png");

    public static Image boom5 = Toolkit.getDefaultToolkit().createImage("client/src/main/resources/boom/p5.png");

    public static Image boom6 = Toolkit.getDefaultToolkit().createImage("client/src/main/resources/boom/p6.png");

    public static Image boom7 = Toolkit.getDefaultToolkit().createImage("client/src/main/resources/boom/p7.png");

    public static Image boom8 = Toolkit.getDefaultToolkit().createImage("client/src/main/resources/boom/p8.png");

    public static Image boom9 = Toolkit.getDefaultToolkit().createImage("client/src/main/resources/boom/p9.png");
    //子弹图片(1-8)
    public static Image bullet1 = Toolkit.getDefaultToolkit().createImage("client/src/main/resources/bullet/bullet1.png");

    public static Image bullet2 = Toolkit.getDefaultToolkit().createImage("client/src/main/resources/bullet/bullet2.png");

    public static Image bullet3 = Toolkit.getDefaultToolkit().createImage("client/src/main/resources/bullet/bullet3.png");

    public static Image bullet4 = Toolkit.getDefaultToolkit().createImage("client/src/main/resources/bullet/bullet4.png");

    public static Image bullet5 = Toolkit.getDefaultToolkit().createImage("client/src/main/resources/bullet/bullet5.png");

    public static Image bullet6 = Toolkit.getDefaultToolkit().createImage("client/src/main/resources/bullet/bullet6.png");

    public static Image bullet7 = Toolkit.getDefaultToolkit().createImage("client/src/main/resources/bullet/bullet7.png");

    public static Image bullet8 = Toolkit.getDefaultToolkit().createImage("client/src/main/resources/bullet/bullet8.png");


    public static ImageIcon bulletShow1 = new ImageIcon(System.getProperty("user.dir")+"\\TankBattle\\client\\src\\main\\resources\\bullet\\bullet1.png");
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
        public static final int CLASSIC = 1;
        //重装人物
        public static final int ARMOR = 2;
        //轻型人物
        public static final int ASSASSIN = 3;

    }

    public class BulletType
    {
        public static final int CLASSIC = 1;
        //重装子弹
        public static final int ARMOR = 2;
        //轻型子弹
        public static final int ASSASSIN = 3;
        //彩色子弹
        public static final int COLORFUL = 4;
        //浅蓝色子弹
        public static final int LIGHT_BLUE = 5;
        //紫色子弹
        public static final int PURPLE = 6;
        //深蓝色子弹
        public static final int DARK_BLUE = 7;


    }

    public class MonsterType
    {
        //怪物一号
        public static final int MONSTER_ONE = 1;

        public static final int MONSTER_TWO = 2;

        public static final int MONSTER_THREE = 3;

        public static final int MONSTER_FOUR = 4;

        public static final int MONSTER_FIVE = 5;

        public static final int MONSTER_SIX = 6;

        public static final int MONSTER_SEVEN = 7;

        public static final int MONSTER_EIGHT = 8;
    }
}
