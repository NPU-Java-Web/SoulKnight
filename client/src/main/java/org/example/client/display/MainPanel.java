package org.example.client.display;

import org.example.client.Input.GameMouseListener;
import org.example.client.Input.KeyEventListener;
import org.example.client.function.Data;
import org.example.common.keyListener.GameInput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * 此类为界面主体jframe
 * 即主菜单
 */
public class MainPanel extends JFrame {

    public JLabel back, label_start, label_setting, label_instruction, label_quit,label_title, label05, label06,label07,label08;
    private PlaySound p;
    private MainPanel mainPanel;// 本类对象
    private int fouseIndex;
    public MainPanel()
    {
        super("Battle");
        //添加鼠标监听
        addMouseListener(new GameMouseListener());

        setSize(1000,1000);

        setLayout(null);

        setBackground();

        setinterface();//设置界面
        //添加主菜单的监听
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
        //添加主菜单所有标签
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
        //添加读取所有键盘响应的hashmap
        GameInput gameInput = new GameInput();
        gameInput.init();
        this.addKeyListener(gameInput);
        //开启主菜单的监听线程以从mashmap中读取
        Thread thread = new Thread(new KeyEventListener(),"key");
        thread.start();
    }

    public int getFouseIndex(){
        //获取当前焦点
        return fouseIndex;
    }
    public void setFouseIndex(int fouseIndex){
        //设置当前焦点
        this.fouseIndex = fouseIndex;
    }

}
