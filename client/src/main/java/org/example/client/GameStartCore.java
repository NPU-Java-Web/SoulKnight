package org.example.client;

import lombok.Data;
import org.example.client.calculate.CalculationMain;
import org.example.client.calculate.service.StaticInfo;
import org.example.client.display.DisplayMain;
import org.example.common.config.level.Level;
import org.example.common.config.level.Level1;
import org.example.common.model.player.Player;
import org.example.common.protocal.Result;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Data
public class GameStartCore {
    //地图信息
    private volatile Level level;

    private volatile Player player;

    //显示的所有内容都是从下面这个队列里面取（包括自己的信息），不要管上面的个人信息
    //如果发现Result里的players的playerId和上面一样，那这个人就是自己
    private BlockingQueue<Result> frames;
    //表示游戏是否已经开始
    private volatile boolean start;
    //以下是用于和服务器通信的队列，不进行显示
    public static BlockingQueue<String> sendQueue;
    public static BlockingQueue<String> receiveQueue;
    public  boolean isrunning;
    public GameStartCore(Player player,boolean isrunning) {
        //level只能为1
        this.level = new Level1();
        //playerType只能为1
        this.player = player;
        /*this.type = player.getPlayerType();
        this.playerId = player.getPlayerId();
        this.x = player.getX();
        this.y = player.getY();
        this.angle = player.getAngle();
        this.speed = player.getSpeed();
        this.blood = player.getBlood();
        this.score = player.getScore();*/

        this.frames = new LinkedBlockingQueue<>(5);
        this.start = false;
        sendQueue = new LinkedBlockingQueue<>(10);
        receiveQueue = new LinkedBlockingQueue<>(10);
        this.isrunning = isrunning;
    }

    public void start() {
        Thread calculate = new Thread(new CalculationMain(this), "calculate");
        Thread display = new Thread(new DisplayMain(this), "display");
        calculate.start();
        display.start();
    }
}
