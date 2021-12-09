package org.example.client;

import java.util.Scanner;

public class ClientApplication {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("请输入您的玩家ID：");
        String playerId = in.nextLine();
        if (playerId.isBlank()) {
            System.err.println("玩家ID无效。");
            System.exit(-1);
        }
        ClientCore clientCore = new ClientCore(playerId);
        clientCore.start();
    }
}
