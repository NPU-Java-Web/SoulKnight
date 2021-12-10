package org.example.client;

import lombok.extern.slf4j.Slf4j;
import org.example.client.display.thread.Welcome;
import org.example.common.entity.Player;

@Slf4j
public class ClientCore {

    public static void Start() {
        //新开一个线程，这个线程专门显示菜单页面
        Welcome welcome = new Welcome();
        Thread homepage = new Thread(welcome, "homepage");
        homepage.start();

        try {
            homepage.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Player player = welcome.getPlayer();
        log.info("玩家信息设置成功：" + player);

        GameStartCore gameStartCore = new GameStartCore(player);
        gameStartCore.start();

        //用户点击返回菜单界面就又会进入菜单界面渲染
        //用户直接退出，就关闭界面退出循环


    }

}