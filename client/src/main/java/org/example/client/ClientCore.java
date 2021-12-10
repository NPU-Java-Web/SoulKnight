package org.example.client;

import org.example.client.calculate.gamestart.GameInput;

public class ClientCore {


    public static void Start(){
        GameInput gameInput = new GameInput();
        gameInput.init();

        String playerId= "1";
        boolean gameStart= true;

        if(gameStart == true){
            GameStartCore gameStartCore = new GameStartCore(playerId);
            gameStartCore.start();
        }





    }

}