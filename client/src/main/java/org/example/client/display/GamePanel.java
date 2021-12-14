package org.example.client.display;

import org.example.client.GameStartCore;
import org.example.common.config.GameConfig;
import org.example.common.model.player.PlayerFactory;
import org.example.common.model.player.Player;
import org.example.common.keyListener.GameInput;
import org.example.common.protocal.Result;
import org.w3c.dom.Node;

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
    private int maptype;
    private int maptypebefore = 1;

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
        //加载图片
        Image background1 = GameConfig.gamebackground1;
        Image background2 = GameConfig.gamebackground2;
        Image background3 = GameConfig.gamebackground3;

        Image img = this.createImage(1000, 1000);
        //从gameStartCore中读取serve层传入的信息
        Result temp = gameStartCore.getFrames().poll();

        if (temp != null) {
            result = temp;
        }
//        //读取当前的地图种类
//        maptype = result.getMaptype();
//        //如果地图改变
//        if(maptype != maptypebefore)
//        {
//            maptypebefore = maptype;
//        }

        tempGraphics = img.getGraphics();
        //如果地图样式为1
        if(maptypebefore == 1)
        {
            clear(tempGraphics,background1);
        }
        //如果地图样式为2
        else if(maptypebefore == 2)
        {
            clear(tempGraphics,background2);
        }
        //如果地图样式为3
        else if(maptypebefore == 3)
        {
            clear(tempGraphics,background3);
        }

        drawPlayers(tempGraphics);
        //将内存画布绘制到窗口
        g.drawImage(img, 0, 0, null);
    }


    public void clear(Graphics graphics,Image image) {
        //双缓存加载图片
        graphics.drawImage(image,0,0,1000,1000,this);
        //graphics.setColor(Color.WHITE);
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
