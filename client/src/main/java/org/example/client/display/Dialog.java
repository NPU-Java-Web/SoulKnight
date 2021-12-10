package org.example.client.display;

import javax.swing.*;
import java.awt.*;

/**
 *
 * 本类描述游戏结束弹出的对话框
 *
 */
public class Dialog extends JDialog{
    private JLabel jl01, jl02, jl03, jl04, jima01, jima02, jima03, jima04, jima05;
    private JButton jb02, jb03, jb04, jb05, jButton;
    private JRadioButton jr01, jr02, jr03, jr04, jr05;
    private JCheckBox jcb1, jcb2, jcb3, jcb4;

    public Dialog(JFrame jFrame,int type)
    {
        super(jFrame,true);
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

        setVisible(true);
    }

    public void showSetting(JFrame jFrame)
    {
        setTitle("设置");
        setBounds(jFrame.getBounds().x + 200, jFrame.getBounds().y + 200, 600, 600);
    }

    public void showInstruction(JFrame jFrame)
    {
        setTitle("游戏说明");
        setBounds(jFrame.getBounds().x + 200, jFrame.getBounds().y + 200, 600, 600);
        String text = "<html>坦克大战游戏说明<br>test<html>";
        jl01 = new JLabel(text);
        jl01.setFont(new Font("KaiTi", Font.BOLD, 30));
        // 设置前景颜色
        jl01.setForeground(Color.black);
        jl01.setBounds(40, 0, 400, 350);
        add(jl01);
    }



}
