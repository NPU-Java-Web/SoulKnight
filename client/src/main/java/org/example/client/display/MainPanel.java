package org.example.client.display;

import org.example.client.ClientCore;
import org.example.client.Input.GameMouseListener;
import org.example.client.Input.KeyEventListener;
import org.example.common.config.GameConfig;
import org.example.common.keyListener.GameInput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * <P>此类打开窗口并渲染主菜单界面主体</P>
 *
 * @see JFrame
 * @see JLabel
 * @see MainPanel
 */
public class MainPanel extends JFrame {

    public JLabel back, label_start, label_setting, label_instruction, label_quit, label_title, label05, label06, label07, label08;
    private PlaySound p;
    private MainPanel mainPanel;// 本类对象
    public static GamePanel gamePanel;
    private int fouseIndex;

    public static String playerId;

    public MainPanel() {
        super("Battle");
        //添加鼠标监听
        addMouseListener(new GameMouseListener());

        setSize(1000, 1000);

        setLayout(null);

        setBackground();

        setinterface();//设置界面
        //添加主菜单的监听
        adapter();

        mainPanel = this;
    }

    public void setBackground() {
        // 设置背景标签
        back = new JLabel(GameConfig.backgroundMenu);
        // 设置背景图片位置大小
        back.setBounds(0, 0, getWidth(), getHeight());
        // 初始化一个内容面板
        JPanel j = (JPanel) getContentPane();
        // 面板设置透明
        j.setOpaque(false);
        // 设置背景
        getLayeredPane().add(back, new Integer(Integer.MIN_VALUE));

    }

    public void setinterface() {

        label05 = new JLabel("联机版");
        label05.setFont(new Font("KaiTi", Font.BOLD, 60));
        label05.setBounds(350, 160, 1000, 200);
        label05.setForeground(Color.white);

        // 设置标签
        label_start = new JLabel("开始游戏");
        // 设置字体 风格 字号
        label_start.setFont(new Font("KaiTi", Font.BOLD, 40));
        // 设置前景颜色，即字体颜色
        label_start.setForeground(Color.white);
        // 设置位置
        label_start.setBounds(360, 340, 400, 120);

        label_setting = new JLabel("设置");
        label_setting.setFont(new Font("KaiTi", Font.BOLD, 40));
        label_setting.setBounds(390, 430, 200, 120);
        label_setting.setForeground(Color.white);
        label_instruction = new JLabel("说明");
        label_instruction.setFont(new Font("KaiTi", Font.BOLD, 40));
        label_instruction.setBounds(390, 520, 200, 120);
        label_instruction.setForeground(Color.white);
        label_quit = new JLabel("退出");
        label_quit.setFont(new Font("KaiTi", Font.BOLD, 40));
        label_quit.setBounds(390, 610, 400, 120);
        label_quit.setForeground(Color.white);

        this.fouseIndex = 1;
        add(label_start);
        add(label_setting);
        add(label_instruction);
        add(label_quit);
//        add(label_title);
        add(label05);
    }

    public void adapter() {
        //添加读取所有键盘响应的hashmap
        GameInput gameInput = new GameInput();
        gameInput.init();
        this.addKeyListener(gameInput);
        //开启主菜单的监听线程以从mashmap中读取
        Thread thread = new Thread(new KeyEventListener(), "key");
        thread.start();
        //添加游戏面板的esc键位绑定
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    new Dialog(ClientCore.mainPanel, 3);
                }
            }
        });
    }

    public int getFouseIndex() {
        //获取当前焦点
        return fouseIndex;
    }

    public void setFouseIndex(int fouseIndex) {
        //设置当前焦点
        this.fouseIndex = fouseIndex;
    }

}
