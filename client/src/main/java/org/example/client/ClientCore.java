package org.example.client;

import org.example.client.calculate.gamestart.GameInput;

public class ClientCore {


    public static void Start(){
        GameInput gameInput = new GameInput();

        while (true){

            /**
             * Part1
             * 在此处调用菜单界面,下面这个playerId实际实在菜单界面用户写的
             */
            String playerId= "1";
            boolean gameStart= true;



            /**
             * Part2
             * 上式象征用户点击开始游戏了,然后这个while循环不会继续，因为已经进入if里的gameStartCore.start()了
             */
            if(gameStart == true){
                GameStartCore gameStartCore = new GameStartCore(playerId);
                gameStartCore.start();
            }


            /**
             * Part3
             * 用户点击返回菜单界面就又会进入菜单界面渲染
             * 用户直接退出，就关闭界面退出循环
             */
        }


    }

}