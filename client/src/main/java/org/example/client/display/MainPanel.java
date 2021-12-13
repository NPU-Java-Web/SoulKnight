package org.example.client.display;

import org.example.client.function.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 此类为界面主体jframe
 * 即主菜单
 */
public class MainPanel extends JFrame {

    private JLabel back, label_start, label_setting, label_instruction, label_quit,label_title, label05, label06,label07,label08;
    private PlaySound p;
    private MainPanel mainPanel;// 本类对象
    private int fouseIndex;
    public MainPanel()
    {
        super("Battle");

        setSize(1000,1000);

        setLayout(null);

        setBackground();

        setinterface();//设置界面

        adapter();

        mainPanel = this;
    }

    public void setBackground()
    {
        // 设置背景标签
        back = new JLabel(Data.background);
        // 设置背景图片位置大小
        back.setBounds(0,0,getWidth(),getHeight());
        // 初始化一个内容面板
        JPanel j = (JPanel) getContentPane();
        // 面板设置透明
        j.setOpaque(false);
        // 设置背景
        getLayeredPane().add(back, new Integer(Integer.MIN_VALUE));

    }

    public void setinterface()
    {
        label_title = new JLabel("元气骑士");
        label_title.setFont(new Font("KaiTi", Font.BOLD, 80));
        label_title.setBounds(280,130,1000,200);
        label_title.setForeground(Color.black);
        // 设置标签
        label_start = new JLabel("开始游戏");
        // 设置字体 风格 字号
        label_start.setFont(new Font("KaiTi", Font.BOLD, 40));
        // 设置前景颜色，即字体颜色
        label_start.setForeground(Color.red);
        // 设置位置
        label_start.setBounds(360, 340, 400, 120);

        label_setting = new JLabel("设置");
        label_setting.setFont(new Font("KaiTi", Font.BOLD, 40));
        label_setting.setBounds(390, 430, 200, 120);

        label_instruction = new JLabel("说明");
        label_instruction.setFont(new Font("KaiTi", Font.BOLD, 40));
        label_instruction.setBounds(390, 520, 200, 120);

        label_quit = new JLabel("退出");
        label_quit.setFont(new Font("KaiTi", Font.BOLD, 40));
        label_quit.setBounds(390, 610, 400, 120);


        this.fouseIndex = 1;
        add(label_start);
        add(label_setting);
        add(label_instruction);
        add(label_quit);
        add(label_title);
    }

    public void adapter()
    {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                int key = e.getKeyCode();

                // 监听向上或向下按键

                if(key==KeyEvent.VK_DOWN || key==KeyEvent.VK_UP)
                {
                    if(key == KeyEvent.VK_DOWN)
                    {
                        if(fouseIndex == 1)
                        {
                            fouseIndex = 2;
                        }
                        else if(fouseIndex == 2)
                        {
                            fouseIndex = 3;
                        }
                        else if(fouseIndex == 3)
                        {
                            fouseIndex = 4;
                        }
                        else if(fouseIndex == 4)
                        {
                            fouseIndex = 1;
                        }
                    }
                    if(key == KeyEvent.VK_UP)
                    {
                        if(fouseIndex == 1)
                        {
                            fouseIndex = 4;
                        }
                        else if(fouseIndex == 2)
                        {
                            fouseIndex = 1;
                        }
                        else if(fouseIndex == 3)
                        {
                            fouseIndex = 2;
                        }
                        else if(fouseIndex == 4)
                        {
                            fouseIndex = 3;
                        }
                    }
                    // 如果指到label_start
                    if (fouseIndex == 1) {
                        label_start.setForeground(Color.red);
                        label_setting.setForeground(Color.white);
                        label_instruction.setForeground(Color.white);
                        label_quit.setForeground(Color.white );
                    }
                    if (fouseIndex == 2) {
                        // label_setting
                        label_start.setForeground(Color.white);
                        label_setting.setForeground(Color.red);
                        label_instruction.setForeground(Color.white);
                        label_quit.setForeground(Color.white );
                    }
                    if(fouseIndex == 3)
                    {
                        //label_instruction
                        label_start.setForeground(Color.white);
                        label_setting.setForeground(Color.white);
                        label_instruction.setForeground(Color.red);
                        label_quit.setForeground(Color.white );
                    }
                    if(fouseIndex == 4)
                    {
                        //label_quit
                        label_start.setForeground(Color.white);
                        label_setting.setForeground(Color.white);
                        label_instruction.setForeground(Color.white);
                        label_quit.setForeground(Color.red );
                    }


                }//
                //监听回车键
                if(key == KeyEvent.VK_ENTER)
                {
                    //若焦点在开始游戏，则进行游戏面板的渲染
                    if(fouseIndex == 1)
                    {


//                               GameStartCore gameStartCore = new GameStartCore(new Player(1,"1",500,500,0.0),mainPanel);
//                               gameStartCore.start();
                        NewGamePanel newGamePanel = new NewGamePanel(mainPanel);
                        add(newGamePanel);
                        newGamePanel.setSize(1000, 1000);// 设置游戏面板界面大小
//                               Thread.sleep(1000);
//                               System.out.println("qqqqqqqq");
//                               GamePanel window = new GamePanel(1000, 1000, "Battle Game",mainPanel,gameStartCore);

                               //add(window);
                              // window.setSize(1000,1000);
                               remove(label_setting);
                               remove(label_quit);
                               remove(label_instruction);
                               remove(label_start);





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

                    if(fouseIndex == 2)
                    {
                        new Dialog(mainPanel,1);
                    }

                    if(fouseIndex == 3)
                    {
                        new Dialog(mainPanel,2);
                    }

                    if(fouseIndex == 4)
                    {
                        int result = JOptionPane.showConfirmDialog(null, "确认退出?", "确认",JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
                        if (result == JOptionPane.OK_OPTION) {
                            // 退出
                            System.exit(0);
                        }

                    }
                }

            }
        });
    }


}
