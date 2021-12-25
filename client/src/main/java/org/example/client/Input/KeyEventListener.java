package org.example.client.Input;


import org.example.client.ClientCore;
import org.example.client.calculate.service.StaticInfo;
import org.example.client.display.Dialog;
import org.example.client.display.GamePanel;
import org.example.client.display.MainPanel;
import org.example.common.config.GameConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * 此类为主菜单的监听线程，以从总的键盘响应的hashmap中读取对应信息
 *
 * @see Runnable
 * @see MainPanel
 */
public class KeyEventListener implements Runnable {
    private boolean exited = false;
    /**
     * 每次绘制间隔时间
     */
    private int interval;
    private MainPanel mainPanel;
    private boolean flag = true;


    public KeyEventListener() {
        interval = 100;
    }

    @Override
    public void run() {

        while (!exited) {
            if (GameInput.getKeyDown(KeyEvent.VK_DOWN) || GameInput.getKeyDown(KeyEvent.VK_UP)) {

                if (GameInput.getKeyDown(KeyEvent.VK_DOWN)) {
                    if (ClientCore.mainPanel.getFouseIndex() == 1) {
                        ClientCore.mainPanel.setFouseIndex(2);
                    } else if (ClientCore.mainPanel.getFouseIndex() == 2) {
                        ClientCore.mainPanel.setFouseIndex(3);
                    } else if (ClientCore.mainPanel.getFouseIndex() == 3) {
                        ClientCore.mainPanel.setFouseIndex(4);
                    } else if (ClientCore.mainPanel.getFouseIndex() == 4) {
                        ClientCore.mainPanel.setFouseIndex(1);
                    }
                }
                if (GameInput.getKeyDown(KeyEvent.VK_UP)) {
                    if (ClientCore.mainPanel.getFouseIndex() == 1) {
                        ClientCore.mainPanel.setFouseIndex(4);
                    } else if (ClientCore.mainPanel.getFouseIndex() == 2) {
                        ClientCore.mainPanel.setFouseIndex(1);
                    } else if (ClientCore.mainPanel.getFouseIndex() == 3) {
                        ClientCore.mainPanel.setFouseIndex(2);
                    } else if (ClientCore.mainPanel.getFouseIndex() == 4) {
                        ClientCore.mainPanel.setFouseIndex(3);
                    }
                }
                // 如果指到label_start
                if (ClientCore.mainPanel.getFouseIndex() == 1) {
                    ClientCore.mainPanel.label_start.setForeground(Color.red);
                    ClientCore.mainPanel.label_setting.setForeground(Color.white);
                    ClientCore.mainPanel.label_instruction.setForeground(Color.white);
                    ClientCore.mainPanel.label_quit.setForeground(Color.white);
                }
                if (ClientCore.mainPanel.getFouseIndex() == 2) {
                    // label_setting
                    ClientCore.mainPanel.label_start.setForeground(Color.white);
                    ClientCore.mainPanel.label_setting.setForeground(Color.red);
                    ClientCore.mainPanel.label_instruction.setForeground(Color.white);
                    ClientCore.mainPanel.label_quit.setForeground(Color.white);
                }
                if (ClientCore.mainPanel.getFouseIndex() == 3) {
                    //label_instruction
                    ClientCore.mainPanel.label_start.setForeground(Color.white);
                    ClientCore.mainPanel.label_setting.setForeground(Color.white);
                    ClientCore.mainPanel.label_instruction.setForeground(Color.red);
                    ClientCore.mainPanel.label_quit.setForeground(Color.white);
                }
                if (ClientCore.mainPanel.getFouseIndex() == 4) {
                    //label_quit
                    ClientCore.mainPanel.label_start.setForeground(Color.white);
                    ClientCore.mainPanel.label_setting.setForeground(Color.white);
                    ClientCore.mainPanel.label_instruction.setForeground(Color.white);
                    ClientCore.mainPanel.label_quit.setForeground(Color.red);
                }


            }
            //监听回车键
            if (GameInput.getKeyDown(KeyEvent.VK_ENTER)) {
                //若焦点在开始游戏，则进行游戏面板的渲染

                if (ClientCore.mainPanel.getFouseIndex() == 1) {
                    GameInput.setKeys(KeyEvent.VK_ENTER);
                    if (GameConfig.playerId.equals("")) {
                        Object[] options = {"OK ", "CANCEL "};
                        JOptionPane.showOptionDialog(null, "您还没有输入玩家Id，请前往设置->输入Id", "提示", JOptionPane.DEFAULT_OPTION,
                                JOptionPane.WARNING_MESSAGE, null, options, options[0]);

                    } else {
                        StaticInfo.isrunning = true;
                        GamePanel newGamePanel = new GamePanel(ClientCore.mainPanel);
                        MainPanel.gamePanel = newGamePanel;
                        StaticInfo.gamePanel = newGamePanel;
                        ClientCore.mainPanel.add(newGamePanel);
                        // 设置游戏面板界面大小
                        newGamePanel.setSize(1000, 1000);

                        ClientCore.mainPanel.remove(ClientCore.mainPanel.label_setting);
                        ClientCore.mainPanel.remove(ClientCore.mainPanel.label_quit);
                        ClientCore.mainPanel.remove(ClientCore.mainPanel.label_instruction);
                        ClientCore.mainPanel.remove(ClientCore.mainPanel.label_start);
                    }


                }

                if (ClientCore.mainPanel.getFouseIndex() == 2) {
                    //因为新建一个dialog线程就会卡在这里，无法读到松开件键盘的事件，所以在这里手动添加指令，使得enter恢复使用
                    GameInput.setKeys(KeyEvent.VK_ENTER);
                    new org.example.client.display.Dialog(ClientCore.mainPanel, 1);

                }

                if (ClientCore.mainPanel.getFouseIndex() == 3) {
                    GameInput.setKeys(KeyEvent.VK_ENTER);
                    new Dialog(ClientCore.mainPanel, 2);
                }

                if (ClientCore.mainPanel.getFouseIndex() == 4) {
                    GameInput.setKeys(KeyEvent.VK_ENTER);
                    int result = JOptionPane.showConfirmDialog(null, "确认退出?", "确认", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    if (result == JOptionPane.OK_OPTION) {
                        // 退出
                        System.exit(0);
                    }

                }
            }
            try {
                //稳定fps
                Thread.sleep(interval);
            } catch (Exception e) {
                break;
            }
        }
    }
}
