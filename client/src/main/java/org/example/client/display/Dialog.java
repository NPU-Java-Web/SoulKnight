package org.example.client.display;

import org.example.client.ClientCore;
import org.example.client.calculate.service.StaticInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 *
 * 本类描述游戏结束弹出的对话框
 *
 */
public class Dialog extends JDialog{
    private JLabel jlabel_one, jl02, jl03, jl04, jima01, jima02, jima03, jima04, jima05;
    private JButton jb02, jb03, jb04, jb05, jButton;
    private JRadioButton jr01, jr02, jr03, jr04, jr05;
    private JCheckBox jcb1, jcb2, jcb3, jcb4;

    public Dialog(JFrame jFrame,int type)
    {
        super(jFrame,true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        // 设置布局管理器为无
        setLayout(null);
        // 设置对话框不可更改大小
        setResizable(false);

        if(type == 1)
        {
            //设置弹窗
            showSetting(jFrame);
        }
        else if(type == 2)
        {
            //说明弹窗
            showInstruction(jFrame);

        }
        else if(type == 3)
        {
            showpause(jFrame);
        }

        setVisible(true);
    }

    public void showSetting(JFrame jFrame)
    {
        setTitle("设置");
        setBounds(jFrame.getBounds().x + 200, jFrame.getBounds().y + 200, 600, 600);

        // 音效标签
        jl02 = new JLabel("声音选项");
        jl02.setFont(new Font("acefont-family", Font.BOLD, 15));
        jl02.setBounds(10, 80, 100, 20);
        add(jl02);

        // 音效选项
        jcb1 = new JCheckBox("背景声音");
        jcb1.setBounds(20, 120, 80, 20);
        if (PlaySound.b[0]) {
            jcb1.setSelected(true);
        }
        add(jcb1);
        jcb1.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {

                PlaySound.b[0] = !PlaySound.b[0];

                if (!PlaySound.b[0]) {

                    ClientCore.playSound.stop();

                }
                else {
                    ClientCore.playSound.start();
                }
            }
        });

        setBounds(jFrame.getBounds().x + 200, jFrame.getBounds().y + 200, 600, 600);
        jb02 = new JButton("返回主菜单");
        jb02.setBounds(220, 300, 100, 50);
        add(jb02);
        jb02.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnMainPanel();
            }
        });

    }

    public void showInstruction(JFrame jFrame)
    {
        setTitle("游戏说明");
        setBounds(jFrame.getBounds().x + 200, jFrame.getBounds().y + 200, 1000, 600);

        String text = "<html>元气骑士游戏说明<br>test<html>";
        jlabel_one = new JLabel(text);
        jlabel_one.setFont(new Font("KaiTi", Font.BOLD, 30));
        // 设置前景颜色
        jlabel_one.setForeground(Color.black);
        jlabel_one.setBounds(40, 0, 400, 350);
        add(jlabel_one);

        setBounds(jFrame.getBounds().x + 200, jFrame.getBounds().y + 200, 1000, 600);
        jb02 = new JButton("返回主菜单");
        jb02.setBounds(220, 300, 100, 50);
        add(jb02);
        jb02.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnMainPanel();
            }
        });
    }

    public void showpause(JFrame jFrame)
    {
        setTitle("暂停");

        setBounds(jFrame.getBounds().x + 200, jFrame.getBounds().y + 200, 600, 600);
        jb02 = new JButton("退出游戏");
        jb02.setBounds(100, 200, 100, 50);
        add(jb02);
        jb02.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "确认退出?", "确认", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    // 退出
                    System.exit(0);
                }
            }
        });

        jb03 = new JButton("返回游戏");
        jb03.setBounds(100, 100, 100, 50);
        add(jb03);
        jb03.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //GamePanel.gameRenderThread.start();
                dispose();
            }
        });
    }

    public void returnMainPanel()
    {
                StaticInfo.isrunning = false;

                Point p = ClientCore.mainPanel.getLocation();
                ClientCore.mainPanel.dispose();
                ClientCore.Start();
                ClientCore.mainPanel.setLocation(p);

                //ClientCore.mainPanel.remove(StaticInfo.gamePanel);
                // StaticInfo.gamePanel = null;
    }


}
