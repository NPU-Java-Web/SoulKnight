package org.example.client.display;

import lombok.extern.slf4j.Slf4j;
import org.example.client.ClientCore;
import org.example.client.GameStartCore;
import org.example.client.calculate.service.GameDataProcess;
import org.example.client.calculate.service.StaticInfo;
import org.example.common.config.GameConfig;
import org.example.common.config.level.Level;
import org.example.common.config.level.Level1;
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
    public static Result result;
    private Graphics tempGraphics;
    public MainPanel mainPanel;
    private int maptype;
    private int maptypebefore = 1;
    public static boolean isrunning = true;
    private int[] monsterBlood;
    private int[] playerBlood;
    private String playerId = GameConfig.playerId;


    public GamePanel(MainPanel mainPanel) {
        StaticInfo.isrunning = true;
        // 设置请求焦点
        requestFocusInWindow();
        //打开GameStartCore，开启calculate和display线程，将人物信息不间断发送出去
        GameStartCore gameStartCore = new GameStartCore(PlayerFactory.makePlayer(
                GameConfig.PlayerType.CLASSIC, playerId, 500, 700, 0.0),isrunning);
        StaticInfo.setGameStartCore(gameStartCore);
        this.gameStartCore = gameStartCore;
        gameStartCore.start();
        //传入mainPanel
        this.mainPanel = mainPanel;
        //存储玩家和怪物的最大血量
        monsterBlood = new int[100];
        playerBlood = new int[100];
        //设置最大蓝量为100
        GameConfig.playerStrength = 100;
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


        Image img = this.createImage(1000, 1000);
        tempGraphics = img.getGraphics();
        //从gameStartCore中读取serve层传入的信息
        Result temp = gameStartCore.getFrames().poll();
        if (temp != null) {
            result = temp;
        }


        drawMap(tempGraphics);
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

    public void drawMap(Graphics graphics){
        //读取当前的地图种类
        if(result!=null){
            //如果地图样式为1
            if(StaticInfo.gameStartCore.getLevel() instanceof Level1)
            {
                clear(tempGraphics, GameConfig.gamebackground1);
            }
            //如果地图样式为2
            else if(maptypebefore == 2)
            {
                clear(tempGraphics, GameConfig.gamebackground2);
            }
            //如果地图样式为3
            else if(maptypebefore == 3)
            {
                clear(tempGraphics, GameConfig.gamebackground3);
            }
        }
    }


    public void drawPlayers(Graphics graphics) {

        //用result中的信息来渲染
        if(result!=null) {
            for (Player item : result.getPlayers()) {
                //log.info("("+item.getX()+","+item.getY()+")"+this.hashCode());
                drawPlayer(item, graphics,width,item.getPlayerType());
            }
        }
        //直接用player里面的信息渲染
        //drawPlayer(gameStartCore.getPlayer().getX(),gameStartCore.getPlayer().getY(), graphics);
    }


    public void drawPlayer(Player player, Graphics graphics,int width,int playertype) {
        int flag = 0;
        if(player.getBlood()>playerBlood[flag])
        {
            playerBlood[flag] = player.getBlood();
        }
        //如果当前人物为玩家，则进行特殊的血量渲染
        if(player.getPlayerId().equals(playerId))
        {
            //画血量
            int widthplayer = player.getBlood()*300/playerBlood[flag];
            graphics.setColor(new Color(161, 7, 7));
            graphics.fillRect(10,10,widthplayer,8);

            //画蓝量
            int widthStrength = new Double(GameConfig.playerStrength*300/100).intValue() ;
            graphics.setColor(new Color(2, 62, 206));
            graphics.fillRect(10,20,widthStrength,8);
        }
        int newWidth =   player.getBlood()*30/playerBlood[flag];
        //判断是否死亡
        if(player.getBlood()<=0)
        {
            if(GameConfig.flag){
                GameConfig.flag = false;
                new Dialog(ClientCore.mainPanel,6);

            }
        }
        if(playertype == 1)
        {
            graphics.drawImage(GameConfig.player,
                    player.getX()-GameConfig.player.getWidth(GameConfig.playerShow.getImageObserver())/2,
                    player.getY()-GameConfig.player.getHeight(GameConfig.playerShow.getImageObserver())/2,
                    null);
            graphics.setColor(new Color(125, 16, 16));
            graphics.fillRect(player.getX()+10,player.getY()-10,width,8);
        }
        if(playertype == 2)
        {
            graphics.drawImage(GameConfig.player2,
                    player.getX()-GameConfig.player2.getWidth(GameConfig.playerShow2.getImageObserver())/2,
                    player.getY()-GameConfig.player2.getHeight(GameConfig.playerShow2.getImageObserver())/2,
                    null);
            graphics.setColor(new Color(125, 16, 16));
            graphics.fillRect(player.getX()+10,player.getY()-10,width,8);
        }
        if(playertype == 3)
        {
            graphics.drawImage(GameConfig.player3,
                    player.getX()-GameConfig.player3.getWidth(GameConfig.playerShow3.getImageObserver())/2,
                    player.getY()-GameConfig.player3.getHeight(GameConfig.playerShow3.getImageObserver())/2,
                    null);
            graphics.setColor(new Color(125, 16, 16));
            graphics.fillRect(player.getX()+10,player.getY()-10,width,8);
        }


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
        graphics.drawImage(GameConfig.bullet1,
                x-GameConfig.bullet1.getWidth(GameConfig.bulletShow1.getImageObserver())/2,
                y-GameConfig.bullet1.getHeight(GameConfig.bulletShow1.getImageObserver())/2,
                null);
    }

    public void drawMonsters(Graphics graphics){
        int flag = 0;
        if(result!=null){
            for (Monster item : result.getMonsters()) {
                if(item.getBlood()>monsterBlood[flag])
                {
                    monsterBlood[flag] = item.getBlood();
                }

                int width =   item.getBlood()*30/monsterBlood[flag];
                drawMonster(item.getX(),item.getY(),graphics,width);
                flag++;
            }
        }
    }

    public void drawMonster(int x, int y, Graphics graphics,int width){
        graphics.drawImage(GameConfig.Monster1,
                x-GameConfig.Monster1.getWidth(GameConfig.MonsterShow1.getImageObserver())/2,
                y-GameConfig.Monster1.getHeight(GameConfig.MonsterShow1.getImageObserver())/2,
                null);
        graphics.setColor(new Color(156, 40, 40));
        graphics.fillRect(x+10,y-10,width,8);

    }


}
