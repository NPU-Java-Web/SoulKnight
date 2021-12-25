package org.example.client.display;


import lombok.extern.slf4j.Slf4j;
import org.example.client.GameStartCore;
import org.example.client.calculate.service.GameDataProcess;
import org.example.client.calculate.service.StaticInfo;

/**
 * <p>此类为游戏页面的从键盘响应的hashmap中读取对应信息的线程</p>
 * <p>此项目使用 1个线程获取所有键盘响应并存储进hashmap，用多个不同线程遍历读取对应键盘响应的hashmap的方法，
 * 实现不同界面响应不同方法。保证线程安全并解决按键冲突</p>
 *
 * @see Runnable
 * @see GameStartCore
 * @see GamePanel
 */
@Slf4j
public class GameRenderThread implements Runnable {

    private boolean exited = false;
    /**
     * 每次绘制间隔时间
     */
    private int interval;
    public GameStartCore gameStartCore;
    public GamePanel gamePanel;
    public boolean isrunning;

    /**
     * @param gameStartCore gamestartcore
     * @param gamePanel     gamepanel
     * @param isrunning     isrunnning
     */
    public GameRenderThread(GameStartCore gameStartCore, GamePanel gamePanel, boolean isrunning) {
        this.gameStartCore = gameStartCore;
        interval = 1000 / GamePanel.fps;
        this.gamePanel = gamePanel;
        this.isrunning = isrunning;
    }

    /**
     *
     */
    @Override
    public void run() {
//
        while (!exited) {

            if (StaticInfo.isrunning) {
                //进行地图的选择
                GameDataProcess.ifSwitchMap();
                //判断是否移动人物
                GameDataProcess.moveMyPlayer();
                //进行页面渲染
                gamePanel.repaint();
            }


            try {
                /**稳定fps*/
                Thread.sleep(interval);
            } catch (Exception e) {
                break;
            }
        }
    }


}
