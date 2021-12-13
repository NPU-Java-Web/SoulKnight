package org.example.client.display;

import org.example.client.GameStartCore;
import org.example.common.config.GameConfig;
import org.example.common.entity.Player;
import org.example.common.keyListener.GameInput;
import org.example.common.protocal.Result;
import org.w3c.dom.Node;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private int width;
    private int height;
    private String windowTitle;
    public static final int fps = 40;
    private final GameStartCore gameStartCore;
    private Thread gameRenderThread;
    private Result result;
    private Graphics tempGraphics;
    public MainPanel mainPanel;


    public GamePanel(MainPanel mainPanel)
    {			requestFocusInWindow();// 设置请求焦点
       GameStartCore gameStartCore = new GameStartCore(new Player(1,"1",500,500,0.0));
       this.gameStartCore = gameStartCore;
       gameStartCore.start();
        this.mainPanel = mainPanel;
        setLayout(null);// 清除布局管理
        setBackground(new Color(83, 163, 238));

        gameRenderThread = new Thread(new GameRenderThread(gameStartCore,this),"render");
        gameRenderThread.start();
    }


    @Override
    public void paintComponent(Graphics g){
//    super.paintComponent(g);//清屏
//    this.setBackground(Color.WHITE); //设置面板的背景色
    Image img = this.createImage(1000, 1000);
    result = gameStartCore.getFrames().poll();
    tempGraphics = img.getGraphics();
    clear(tempGraphics);
    drawPlayers(tempGraphics);
    //将内存画布绘制到窗口
    g.drawImage(img, 0, 0, null);
    }


    public void clear(Graphics graphics){
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);
    }


    public void drawPlayers(Graphics graphics){
        /*for(var item : result.getPlayers()){
            drawPlayer(item.getX(), item.getY(),graphics);
        }*/
        drawPlayer(gameStartCore.getPlayer().getX(),gameStartCore.getPlayer().getY(), graphics);
    }


    public void drawPlayer(int x, int y, Graphics graphics){
        graphics.drawImage(GameConfig.player, x, y, null);
    }





}
