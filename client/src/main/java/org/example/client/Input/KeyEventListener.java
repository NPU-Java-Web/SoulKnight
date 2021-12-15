package org.example.client.Input;


import org.example.client.ClientCore;
import org.example.client.calculate.service.StaticInfo;
import org.example.client.display.Dialog;
import org.example.client.display.GamePanel;
import org.example.client.display.MainPanel;
import org.example.common.keyListener.GameInput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * 此类为主菜单的监听线程，以从总的键盘响应的hashmap中读取对应信息
 */
public class KeyEventListener implements Runnable {
    private boolean exited = false;
    /**每次绘制间隔时间*/
    private int interval;
    private MainPanel mainPanel;
    private boolean flag = true;


    public KeyEventListener()
    {
        interval = 100;
    }

    @Override
    public void run()
    {

        while (!exited)
        {
            if(GameInput.getKeyDown(KeyEvent.VK_DOWN) || GameInput.getKeyDown(KeyEvent.VK_UP))
            {

                if(GameInput.getKeyDown(KeyEvent.VK_DOWN))
                {
                    if(ClientCore.mainPanel.getFouseIndex() == 1)
                    {
                        ClientCore.mainPanel.setFouseIndex(2);
                    }
                    else if(ClientCore.mainPanel.getFouseIndex() == 2)
                    {
                        ClientCore.mainPanel.setFouseIndex(3);
                    }
                    else if(ClientCore.mainPanel.getFouseIndex() == 3)
                    {
                        ClientCore.mainPanel.setFouseIndex(4);
                    }
                    else if(ClientCore.mainPanel.getFouseIndex() == 4)
                    {
                        ClientCore.mainPanel.setFouseIndex(1);
                    }
                }
                if(GameInput.getKeyDown(KeyEvent.VK_UP))
                {
                    if(ClientCore.mainPanel.getFouseIndex() == 1)
                    {
                        ClientCore.mainPanel.setFouseIndex(4);
                    }
                    else if(ClientCore.mainPanel.getFouseIndex() == 2)
                    {
                        ClientCore.mainPanel.setFouseIndex(1);
                    }
                    else if(ClientCore.mainPanel.getFouseIndex() == 3)
                    {
                        ClientCore.mainPanel.setFouseIndex(2);
                    }
                    else if(ClientCore.mainPanel.getFouseIndex() == 4)
                    {
                        ClientCore.mainPanel.setFouseIndex(3);
                    }
                }
                // 如果指到label_start
                if (ClientCore.mainPanel.getFouseIndex() == 1) {
                    ClientCore.mainPanel.label_start.setForeground(Color.red);
                    ClientCore.mainPanel.label_setting.setForeground(Color.white);
                    ClientCore.mainPanel.label_instruction.setForeground(Color.white);
                    ClientCore.mainPanel.label_quit.setForeground(Color.white );
                }
                if (ClientCore.mainPanel.getFouseIndex() == 2) {
                    // label_setting
                    ClientCore.mainPanel.label_start.setForeground(Color.white);
                    ClientCore.mainPanel.label_setting.setForeground(Color.red);
                    ClientCore.mainPanel.label_instruction.setForeground(Color.white);
                    ClientCore.mainPanel.label_quit.setForeground(Color.white );
                }
                if(ClientCore.mainPanel.getFouseIndex() == 3)
                {
                    //label_instruction
                    ClientCore.mainPanel.label_start.setForeground(Color.white);
                    ClientCore.mainPanel.label_setting.setForeground(Color.white);
                    ClientCore.mainPanel.label_instruction.setForeground(Color.red);
                    ClientCore.mainPanel.label_quit.setForeground(Color.white );
                }
                if(ClientCore.mainPanel.getFouseIndex() == 4)
                {
                    //label_quit
                    ClientCore.mainPanel.label_start.setForeground(Color.white);
                    ClientCore.mainPanel.label_setting.setForeground(Color.white);
                    ClientCore.mainPanel.label_instruction.setForeground(Color.white);
                    ClientCore.mainPanel.label_quit.setForeground(Color.red );
                }


            }//
            //System.out.println(GameInput.getKeyDown(KeyEvent.VK_ENTER));
            //监听回车键
            if(GameInput.getKeyDown(KeyEvent.VK_ENTER)) {
                //若焦点在开始游戏，则进行游戏面板的渲染

                if(ClientCore.mainPanel.getFouseIndex() == 1)
                {


//                               GameStartCore gameStartCore = new GameStartCore(new Player(1,"1",500,500,0.0),mainPanel);
//                               gameStartCore.start();
                    StaticInfo.isrunning = true;
                    GamePanel newGamePanel = new GamePanel(ClientCore.mainPanel);
                    MainPanel.gamePanel = newGamePanel;
                    StaticInfo.gamePanel = newGamePanel;
                    ClientCore.mainPanel.add(newGamePanel);
                    newGamePanel.setSize(1000, 1000);// 设置游戏面板界面大小
//                               Thread.sleep(1000);
//                               System.out.println("qqqqqqqq");
//                               GamePanel window = new GamePanel(1000, 1000, "Battle Game",mainPanel,gameStartCore);

                    //add(window);
                    // window.setSize(1000,1000);
                    ClientCore.mainPanel.remove(ClientCore.mainPanel.label_setting);
                    ClientCore.mainPanel.remove(ClientCore.mainPanel.label_quit);
                    ClientCore.mainPanel.remove(ClientCore.mainPanel.label_instruction);
                    ClientCore.mainPanel.remove(ClientCore.mainPanel.label_start);


//                        new Dialog(mainPanel,3);
//                           GamePanel gamePanel = new GamePanel(mainPanel);
//                            mainPanel.add(gamePanel);
//                            gamePanel.setSize(1000, 1000);// 设置游戏面板界面大小
//                         //移除组件
//                            remove(label_start);
//                            remove(label_instruction);
//                            remove(label_quit);
//                            remove(label_setting);

                }

                if (ClientCore.mainPanel.getFouseIndex() == 2) {
                    new org.example.client.display.Dialog(ClientCore.mainPanel, 1);
                }

                if (ClientCore.mainPanel.getFouseIndex() == 3) {
                    new Dialog(ClientCore.mainPanel, 2);
                }

                if (ClientCore.mainPanel.getFouseIndex() == 4) {
                    int result = JOptionPane.showConfirmDialog(null, "确认退出?", "确认", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    if (result == JOptionPane.OK_OPTION) {
                        // 退出
                        System.exit(0);
                    }

                }
            }
            try
            {
                /**稳定fps*/
                Thread.sleep(interval);
            }
            catch (Exception e)
            {
                break;
            }
        }
    }
}
