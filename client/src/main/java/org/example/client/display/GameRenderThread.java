package org.example.client.display;


import org.example.client.GameStartCore;

public class GameRenderThread implements Runnable{

    private boolean exited = false;
    /**每次绘制间隔时间*/
    private int interval;
    public GameStartCore gameStartCore;
    public NewGamePanel gamePanel;

    public GameRenderThread(GameStartCore gameStartCore, NewGamePanel gamePanel)
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
            gameStartCore.getPlayer().onClick();
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
