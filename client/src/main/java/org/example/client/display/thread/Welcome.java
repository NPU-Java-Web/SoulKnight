package org.example.client.display.thread;

import org.example.common.entity.Player;

import java.util.Scanner;

public class Welcome implements Runnable{
    private Player player;

    @Override
    public void run() {
        Scanner in=new Scanner(System.in);
        System.out.print("您的名字：");
        String playerId = in.nextLine();
        Integer playerType = 1;
        player = new Player(playerType, playerId, 0, 0, 0.0);
    }
    public Player getPlayer(){
        return player;
    }
}
