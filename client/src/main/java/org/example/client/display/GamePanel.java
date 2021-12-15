package org.example.client.display;

import lombok.extern.slf4j.Slf4j;
import org.example.client.ClientCore;
import org.example.client.GameStartCore;
import org.example.client.calculate.service.StaticInfo;
import org.example.common.config.GameConfig;
import org.example.common.model.bullet.Bullet;
import org.example.common.model.monster.Monster;
import org.example.common.model.player.PlayerFactory;
import org.example.common.model.player.Player;
import org.example.common.protocal.Result;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@Slf4j
public class GamePanel extends JPanel {

    private int width;
    private int height;
    private String windowTitle;
    public static final int fps = 50;
    private final GameStartCore gameStartCore;
    public static Thread gameRenderThread;
    private Result result;
    private Graphics tempGraphics;
    public MainPanel mainPanel;
    private int maptype;
    private int maptypebefore = 1;
    public static boolean isrunning = true;

    public GamePanel(MainPanel mainPanel) {
        // 设置请求焦点
        requestFocusInWindow();
        //打开GameStartCore，开启calculate和display线程，将人物信息不间断发送出去
        GameStartCore gameStartCore = new GameStartCore(PlayerFactory.makePlayer(
                GameConfig.PlayerType.Classic, "1", 500, 500, 0.0),isrunning);
        StaticInfo.setGameStartCore(gameStartCore);
        this.gameStartCore = gameStartCore;
        gameStartCore.start();
        //传入mainPanel
        this.mainPanel = mainPanel;
        // 清除布局管理
        setLayout(null);
        setBackground(new Color(83, 163, 238));
        adapter();
        //打开游戏panel的读取键盘hashmap的线程并改变player变量，并且按帧率进行页面刷新repaint
        gameRenderThread = new Thread(new GameRenderThread(gameStartCore, this,isrunning), "render");
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
        //读取当前的地图种类
        if(result!=null){
            maptype = result.getMapType();
            //如果地图改变
            if(maptype != maptypebefore)
            {
                maptypebefore = maptype;
            }

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
        }
        drawPlayers(tempGraphics);
        drawBullets(tempGraphics);
        drawMonsters(tempGraphics);
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
                log.info("("+item.getX()+","+item.getY()+")"+this.hashCode());
                drawPlayer(item.getX(), item.getY(), graphics);
            }
        }
        //直接用player里面的信息渲染
        //drawPlayer(gameStartCore.getPlayer().getX(),gameStartCore.getPlayer().getY(), graphics);
    }


    public void drawPlayer(int x, int y, Graphics graphics) {
        graphics.drawImage(GameConfig.player, x, y, null);
    }
    //esc键盘绑定
    public void adapter()
    {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
                {
                    new Dialog(ClientCore.mainPanel,3);
                }
            }
        });
    }


    public void drawBullets(Graphics graphics) {
        if(result!=null) {
            for (Bullet item : result.getBullets()) {
                drawBullet(item.getX(), item.getY(), graphics);
            }
        }
    }

    public void drawBullet(int x, int y, Graphics graphics){
        graphics.drawImage(GameConfig.bullet1, x, y, null);
    }

    public void drawMonsters(Graphics graphics){
        if(result!=null){
            for (Monster item : result.getMonsters()) {
                drawMonster(item.getX(),item.getY(),graphics);
            }
        }
    }

    public void drawMonster(int x, int y, Graphics graphics){
        graphics.drawImage(GameConfig.Monster1,x,y,null);
    }


}
