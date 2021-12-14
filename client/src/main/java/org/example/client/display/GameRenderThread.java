package org.example.client.display;


import org.example.client.GameStartCore;
import org.example.client.calculate.service.GameDataProcess;

/**
 * 此类为游戏页面的从键盘响应的hashmap中读取对应信息
 * 此项目使用 1个线程获取所有键盘响应并存储进hashmap，用多个不同线程遍历读取对应键盘响应的hashmap的方法，实现
 * 不同界面响应不同方法
 */
public class GameRenderThread implements Runnable{

    private boolean exited = false;
    /**每次绘制间隔时间*/
    private int interval;
    public GameStartCore gameStartCore;
    public GamePanel gamePanel;

    public GameRenderThread(GameStartCore gameStartCore, GamePanel gamePanel)
    {
        this.gameStartCore = gameStartCore;
        interval = 1000 / GamePanel.fps;
        this.gamePanel = gamePanel;
    }

    @Override
    public void run()
    {

        while (!exited)
        {
            //调用player方法改动其中的变量
            GameDataProcess.moveMyPlayer();
            gamePanel.repaint();
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
