package org.example.client.display;

import javax.swing.*;

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
    }


}
