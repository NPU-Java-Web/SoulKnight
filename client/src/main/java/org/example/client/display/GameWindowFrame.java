package org.example.client.display;


import javax.swing.*;
import java.awt.*;

public class GameWindowFrame extends JFrame {
    private int width;
    private int height;
    private String windowTitle;

    public GameWindowFrame(int windowWidth, int windowHeight, String title)
    {
        width = windowWidth;
        height = windowHeight;
        windowTitle = title;
        createWindow();
    }

    //创建窗口
    private void createWindow()
    {
        setSize(width, height);
        setTitle(windowTitle);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g)
    {
        g.setColor(Color.BLUE);//设置画笔为黑色
        g.fillRect(0, 0, width, height);//填充整个窗口为蓝色
        g.setColor(Color.WHITE);
        g.drawString("Test", 100, 100);
    }
}
