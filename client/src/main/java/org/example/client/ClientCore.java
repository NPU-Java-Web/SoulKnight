package org.example.client;

import lombok.Data;
import org.example.client.calculate.CalculationMain;
import org.example.client.display.DisplayMain;
import org.example.common.entity.Bullet;
import org.example.common.entity.Level;
import org.example.common.entity.Monster;
import org.example.common.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Data
public class ClientCore {
    //地图信息
    private volatile Level level;

    //以下是个人信息，需要进行显示
    private volatile int type;
    private String playerId;
    private volatile int x;
    private volatile int y;
    private volatile double angle;
    private volatile int speed;
    private volatile int blood;
    private volatile int score;

    //以下是全局信息，需要进行显示（players列表里有自己的信息，这和上面重复了，所以请跳过列表中自己的信息，自己的信息以上面的为准）
    private volatile List<Player> players;
    private volatile List<Bullet> bullets;
    private volatile List<Monster> monsters;
    //表示游戏是否已经开始
    private volatile boolean start;

    //以下是用于和服务器通信的队列，不进行显示
    public static BlockingQueue<String> sendQueue;
    public static BlockingQueue<String> receiveQueue;

    public ClientCore(String playerId) {
        //level只能为1
        this.level = new Level(1);
        //playerType只能为1
        Player player = new Player(1, playerId, 0, 0, 0.0);
        this.type = player.getPlayerType();
        this.playerId = player.getPlayerId();
        this.x = player.getX();
        this.y = player.getY();
        this.angle = player.getAngle();
        this.speed = player.getSpeed();
        this.blood = player.getBlood();
        this.score = player.getScore();

        this.players = new ArrayList<>();
        this.bullets = new ArrayList<>();
        this.monsters = new ArrayList<>();
        this.start = false;
        sendQueue = new ArrayBlockingQueue<>(50);
        receiveQueue = new ArrayBlockingQueue<>(50);

    }

    public void start() {
        Thread calculate = new Thread(new CalculationMain(this), "calculate");
        Thread display = new Thread(new DisplayMain(this), "display");

        calculate.start();
        display.start();
    }
    //mcl
}
