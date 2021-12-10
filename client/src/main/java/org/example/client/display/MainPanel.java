package org.example.client.display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 此类为界面主体jframe
 * 即主菜单
 */
public class MainPanel extends JFrame {

    private JLabel back, label01, label02, label03, label04, label05, label06,label07,label08;
    private PlaySound p;
    private MainPanel m;// 本类对象
    private int fouseIndex;
    public MainPanel()
    {
        super("TankBattle");

        setSize(1000,1000);

        setLayout(null);

        setBackground();

        setinterface();//设置界面

        adapter();

        m = this;
    }

    public void setBackground()
    {

    }

    public void setinterface()
    {
        // 设置标签
        label01 = new JLabel("开始游戏");
        // 设置字体 风格 字号
        label01.setFont(new Font("acefont-family", Font.BOLD, 50));
        // 设置前景颜色，即字体颜色
        label01.setForeground(Color.red);
        // 设置位置
        label01.setBounds(320, 340, 400, 120);

        label02 = new JLabel("设置");
        label02.setFont(new Font("acefont-family", Font.BOLD, 50));
        label02.setBounds(320, 430, 200, 120);


        this.fouseIndex = 1;
        add(label01);
        add(label02);
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
                            fouseIndex = 1;
                        }
                    }
                    if(key == KeyEvent.VK_UP)
                    {
                        if(fouseIndex == 1)
                        {
                            fouseIndex = 2;
                        }
                        else if(fouseIndex == 2)
                        {
                            fouseIndex = 1;
                        }
                    }
                    // 如果箭头指到了01上
                    if (fouseIndex == 1) {
                        label01.setForeground(Color.red);
                        label02.setForeground(Color.black);
                    }
                    if (fouseIndex == 2) {
                        // 否则指到了02上
                        label01.setForeground(Color.black);
                        label02.setForeground(Color.red);
                    }
                }

            }
        });
    }


}
