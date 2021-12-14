package org.example.client.display;

import org.example.client.GameStartCore;
import org.example.common.config.GameConfig;
import org.example.common.config.player.PlayerFactory;
import org.example.common.entity.Player;
import org.example.common.protocal.Result;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private int width;
    private int height;
    private String windowTitle;
    public static final int fps = 50;
    private final GameStartCore gameStartCore;
    private Thread gameRenderThread;
    private Result result;
    private Graphics tempGraphics;
    public MainPanel mainPanel;


    public GamePanel(MainPanel mainPanel) {
        // 设置请求焦点
        requestFocusInWindow();
        //打开GameStartCore，开启calculate和display线程，将人物信息不间断发送出去
        GameStartCore gameStartCore = new GameStartCore(PlayerFactory.makePlayer(
                GameConfig.PlayerType.Classic, "1", 500, 500, 0.0));
        this.gameStartCore = gameStartCore;
        gameStartCore.start();
        //传入mainPanel
        this.mainPanel = mainPanel;
        // 清除布局管理
        setLayout(null);
        setBackground(new Color(83, 163, 238));
        //打开游戏panel的读取键盘hashmap的线程并改变player变量，并且按帧率进行页面刷新repaint
        gameRenderThread = new Thread(new GameRenderThread(gameStartCore, this), "render");
        gameRenderThread.start();

    }


    @Override
    public void paintComponent(Graphics g) {
//    super.paintComponent(g);//清屏
//    this.setBackground(Color.WHITE); //设置面板的背景色
        Image img = this.createImage(1000, 1000);
        //从gameStartCore中读取serve层传入的信息
        Result temp = gameStartCore.getFrames().poll();
        if (temp != null) {
            result = temp;
        }
        tempGraphics = img.getGraphics();
        clear(tempGraphics);
        drawPlayers(tempGraphics);
        //将内存画布绘制到窗口
        g.drawImage(img, 0, 0, null);
    }


    public void clear(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);
    }


    public void drawPlayers(Graphics graphics) {

        //用result中的信息来渲染
        if(result!=null) {
            for (Player item : result.getPlayers()) {
                drawPlayer(item.getX(), item.getY(), graphics);
            }
        }
        //直接用player里面的信息渲染
        //drawPlayer(gameStartCore.getPlayer().getX(),gameStartCore.getPlayer().getY(), graphics);
    }


    public void drawPlayer(int x, int y, Graphics graphics) {
        //应为用result中的信息来画图
        graphics.drawImage(GameConfig.player, x, y, null);
    }


}
