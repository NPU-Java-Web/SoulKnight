package org.example.client.display;

import org.example.client.GameStartCore;
import org.example.common.entity.Player;
import org.example.common.protocal.Result;
import org.w3c.dom.Node;

import javax.swing.*;
import java.awt.*;

public class NewGamePanel extends JPanel {

    private int width;
    private int height;
    private String windowTitle;
    public static final int fps = 40;
    private final GameStartCore gameStartCore;
    private Thread gameRenderThread;
    private Result result;
    private Graphics tempGraphics;
    public MainPanel mainPanel;
    public NewGamePanel(MainPanel mainPanel)
    {
       GameStartCore gameStartCore = new GameStartCore(new Player(1,"1",500,500,0.0));
       this.gameStartCore = gameStartCore;
       gameStartCore.start();
        this.mainPanel = mainPanel;
        setLayout(null);// 清除布局管理
        setBackground(new Color(83, 163, 238));
    }



}
