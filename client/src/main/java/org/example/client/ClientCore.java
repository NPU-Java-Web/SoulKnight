package org.example.client;

import org.example.client.calculate.gamestart.GameInput;
import org.example.client.display.MainPanel;
import org.example.client.display.PlaySound;
import org.example.common.entity.Player;

import javax.swing.*;

/**
 * 此类为前端界面的启动类
 * 以启动jframe
 */
public class ClientCore {

    public static MainPanel mainPanel;//主界面对象
    public static PlaySound playSound;//声音对象

    public static void Start(){

        mainPanel = new MainPanel();
        //监听关闭窗体按钮
        mainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置可视
        mainPanel.setVisible(true);
        //设置不可拉伸
        mainPanel.setResizable(false);
        //居中显示
        mainPanel.setLocationRelativeTo(null);//在屏幕中居中显示

        if(playSound == null)
        {
            playSound = new PlaySound();
            playSound.open(System.getProperty("user.dir") + "\\client\\src\\main\\resources\\帝国进行曲.wav");
            playSound.play();
            playSound.loop();
            playSound.start();
        }
//                GameInput gameInput = new GameInput();
//
//                while (true){
//
//                    /**
//                     * Part1
//                     * 在此处调用菜单界面,下面这个playerId实际实在菜单界面用户写的
//                     */
//                    String playerId= "1";
//                    boolean gameStart= true;
//
//
//
//                    /**
//                     * Part2
//                     * 上式象征用户点击开始游戏了,然后这个while循环不会继续，因为已经进入if里的gameStartCore.start()了
//                     */

//        if (gameStart == true) {
        //为了调试方便，还是开着吧
        GameStartCore gameStartCore = new GameStartCore(new Player(1, "testplayer", 5, 5, 45.0));
        gameStartCore.setStart(true);
        gameStartCore.start();
//        }
//
//
//                    /**
//                     * Part3
//                     * 用户点击返回菜单界面就又会进入菜单界面渲染
//                     * 用户直接退出，就关闭界面退出循环
//                     */
//                }


    }

}
