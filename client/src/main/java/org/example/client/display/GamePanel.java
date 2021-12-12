package org.example.client.display;


import org.example.client.GameStartCore;
import org.example.client.funtion.Data;
import org.example.common.protocal.Result;

import javax.swing.*;
import java.awt.*;

/**TODO
 * 因为游戏菜单栏和游戏主界面有两人单独开发，所以该类也新建了窗口用与暂时开发调试，后续合并时再删除
 */

public class GamePanel extends JFrame {

    private int width;
    private int height;
    private String windowTitle;
    public static final int fps = 10;
    private final GameStartCore gameStartCore;
    private Thread gameRenderThread;
    private Result result;
    private Graphics tempGraphics;

    public GamePanel(int windowWidth, int windowHeight, String title, GameStartCore gameStartCore)
    {
        this.width = windowWidth;
        this.height = windowHeight;
        this.windowTitle = title;
        this.gameStartCore = gameStartCore;
        createWindow();
        gameRenderThread = new Thread(new GameRenderThread(gameStartCore,this),"render");
        gameRenderThread.start();
    }

    private void createWindow()
    {
        setSize(width, height);
        setTitle(windowTitle);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    @Override
    public void paint(Graphics g)
    {
        /**双缓冲防止屏幕闪烁*/
        Image img = this.createImage(width, height);//在内存里创建一个和窗口长宽一样的图片(画布)
        tempGraphics = img.getGraphics();//获得画布的Graphics
        clear(tempGraphics);
        //result = gameStartCore.getFrames().poll();
        drawPlayers(tempGraphics);
        //将内存画布绘制到窗口
        g.drawImage(img, 0, 0, null);
    }
    @Override
    public void repaint(){
        paint(getGraphics());
    }

    public void clear(Graphics graphics){
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);
    }


    public void drawPlayers(Graphics graphics){
        for(var item : result.getPlayers()){
            drawPlayer(item.getX(), item.getY(),graphics);
        }
        drawPlayer(500,500, graphics);
    }
    public void drawPlayer(int x, int y, Graphics graphics){
        graphics.drawImage(Data.player, x, y, null);
    }



}
