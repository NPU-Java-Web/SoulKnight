package org.example.client;


import org.example.client.calculate.service.StaticInfo;
import org.example.client.display.MainPanel;
import org.example.client.display.PlaySound;

import javax.swing.*;

/**
 * 此类为前端界面的启动类
 * 以启动jframe
 */
import org.example.common.config.GameConfig;

import java.util.Stack;

/**
 * <p>客户端核心类主要用于启动客户端对应线程</p>
 *
 * @see MainPanel
 * @see PlaySound
 */

public class ClientCore {

    //主界面对象
    public static volatile MainPanel mainPanel;
    //声音对象
    public static PlaySound playSound;

    public static void Start() {

        mainPanel = new MainPanel();
        StaticInfo.mainPanel = mainPanel;
        //监听关闭窗体按钮
        mainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置可视
        mainPanel.setVisible(true);
        //设置不可拉伸
        mainPanel.setResizable(false);
        //居中显示
        mainPanel.setLocationRelativeTo(null);//在屏幕中居中显示

        if (playSound == null) {
            playSound = new PlaySound();
            playSound.open(GameConfig.sound);
            playSound.play();
            playSound.loop();
            playSound.start();
        }

    }

}
