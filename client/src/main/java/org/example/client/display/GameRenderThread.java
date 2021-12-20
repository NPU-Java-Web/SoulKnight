package org.example.client.display;


import lombok.extern.slf4j.Slf4j;
import org.example.client.ClientCore;
import org.example.client.GameStartCore;
import org.example.client.calculate.service.GameDataProcess;
import org.example.client.calculate.service.StaticInfo;
import org.example.common.config.GameConfig;
import org.example.common.config.level.Level1;
import org.example.common.keyListener.GameInput;

import java.awt.*;

/**
 * 此类为游戏页面的从键盘响应的hashmap中读取对应信息的线程
 * 此项目使用 1个线程获取所有键盘响应并存储进hashmap，用多个不同线程遍历读取对应键盘响应的hashmap的方法，实现
 * 不同界面响应不同方法
 */
@Slf4j
public class GameRenderThread implements Runnable{

    private boolean exited = false;
    /**每次绘制间隔时间*/
    private int interval;
    public GameStartCore gameStartCore;
    public GamePanel gamePanel;
    public boolean isunning;

    /**
     *
     * @param gameStartCore gamestartcore
     * @param gamePanel gamepanel
     * @param isrunning isrunnning
     */
    public GameRenderThread(GameStartCore gameStartCore, GamePanel gamePanel,boolean isrunning)
    {
        this.gameStartCore = gameStartCore;
        interval = 1000 / GamePanel.fps;
        this.gamePanel = gamePanel;
        this.isunning = isrunning;
    }

    /**
     *
     */
    @Override
    public void run()
    {

        while (!exited)
        {

            if(StaticInfo.isrunning)
            {
                GameDataProcess.ifSwitchMap();
                GameDataProcess.moveMyPlayer();
                gamePanel.repaint();
            }


            try
            {
                /**稳定fps*/
                Thread.sleep(interval);
            }
            catch (Exception e)
            {
                break;
            }
        }
    }



}
