package org.example.client.display;


import org.example.client.GameStartCore;

public class GameRenderThread implements Runnable{

    private boolean exited = false;//游戏是否退出
    private int interval;//每次绘制隔多久
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
            gamePanel.repaint();
            try
            {
                Thread.sleep(interval);//间隔一定时间渲染一次，来实现稳定fps
            }
            catch (Exception e)
            {
                break;
            }
        }
    }


}
