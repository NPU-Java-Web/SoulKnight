package org.example.client.display;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.example.client.ClientCore;
import org.example.client.GameStartCore;
import org.example.client.calculate.service.StaticInfo;
import org.example.common.config.GameConfig;
import org.example.common.model.player.Player;
import org.example.common.protocal.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Stack;

/**
 *
 * 本类描述游戏结束弹出的对话框
 *
 */
@Slf4j

public class Dialog extends JDialog {
    private JLabel jlabel_one, jl02, jl03, jl04, jima01, jima02, jima03, jima04, jima05;
    private JButton jb02, jb03, jb04, jb05, jb06, jButton;
    private JRadioButton jr01, jr02, jr03, jr04, jr05;
    private JCheckBox jcb1, jcb2, jcb3, jcb4;
    private boolean isStop = false;
    private String score;

    public Dialog(JFrame jFrame, int type) {
        super(jFrame, true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        // 设置布局管理器为无
        setLayout(null);
        // 设置对话框不可更改大小
        setResizable(false);

        if (type == 1) {
            //设置弹窗
            showSetting(jFrame);
        } else if (type == 2) {
            //说明弹窗
            showInstruction(jFrame);

        } else if (type == 3) {   //暂停弹窗
            showpause(jFrame);
        } else if (type == 4) {   //游戏内说明弹窗
            showInstructionInGame(jFrame);
        } else if (type == 5) {   //游戏内设置弹窗
            showSettingInGame(jFrame);
        } else if (type == 6) {
            //失败弹窗
            showfalse(jFrame);
        } else if (type == 7) {
            //成功弹窗
            showsuccess(jFrame);
        }

        setVisible(true);
    }

    /**
     * @param jFrame jframe
     */
//显示设置弹窗
    public void showSetting(JFrame jFrame) {

        setTitle("设置");
        setBounds(jFrame.getBounds().x + 200, jFrame.getBounds().y + 200, 600, 600);
        jl03 = new JLabel("请输入玩家Id");
        jl03.setFont(new Font("acefont-family", Font.BOLD, 15));
        jl03.setBounds(100, 160, 100, 20);
        add(jl03);
        // 创建一个单行输入框
        JTextField textField = new JTextField();
        // 设置输入框允许编辑
        textField.setEditable(true);
        textField.setText("");
        textField.setBounds(100, 200, 220, 40);
        // 设置输入框的长度为11个字符
        textField.setColumns(11);

        add(textField);

        setBounds(jFrame.getBounds().x + 200, jFrame.getBounds().y + 200, 600, 600);
        jb04 = new JButton("确认Id");
        jb04.setBounds(350, 200, 80, 40);
        add(jb04);
        jb04.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameConfig.playerId = textField.getText();
            }
        });

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

                } else {
                    ClientCore.playSound.start();
                }
            }
        });

        setBounds(jFrame.getBounds().x + 200, jFrame.getBounds().y + 200, 600, 600);
        jb02 = new JButton("返回主菜单");
        jb02.setBounds(190, 450, 200, 50);
        add(jb02);
        jb02.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnMainPanel();
            }
        });

        //人物选择
        // 读入积分
        try {
            BufferedReader fr = new BufferedReader(new FileReader("client/src/main/resources/score.txt"));
            this.score = fr.readLine();
            int money = Integer.parseInt(score);
            fr.close();
        } catch (IOException e) {
            System.out.println("读取失败");
        }
        // 显示积分
        jlabel_one = new JLabel("积分：" + score);
        jlabel_one.setFont(new Font("acefont-family", Font.BOLD, 15));
        jlabel_one.setBounds(200, 300, 100, 20);
        add(jlabel_one);

        //角色1
        jima01 = new JLabel(GameConfig.playerShow);
        jima01.setBounds(50, 300, 100, 30);
        add(jima01);

        jr01 = new JRadioButton("游侠");
        jr01.setBounds(50, 350, 100, 20);
        add(jr01);
        if (GameConfig.playerType == 1) {
            jr01.setSelected(true);
        }
        jr01.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                GameConfig.playerType = 1;
                jr01.setSelected(true);
                jr02.setSelected(false);
                jr03.setSelected(false);
            }
        });
        //角色2
        jima02 = new JLabel(GameConfig.playerShow);
        jima02.setBounds(150, 300, 100, 30);
        add(jima02);

        jr02 = new JRadioButton("战士");
        jr02.setBounds(150, 350, 100, 20);
        add(jr02);
        if (GameConfig.playerType == 2) {
            jr02.setSelected(true);
        }
        jr02.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                GameConfig.playerType = 2;
                jr02.setSelected(true);
                jr01.setSelected(false);
                jr03.setSelected(false);
            }
        });
        //角色3
        jima03 = new JLabel(GameConfig.playerShow);
        jima03.setBounds(250, 300, 100, 30);
        add(jima03);

        jr03 = new JRadioButton("刺客");
        jr03.setBounds(250, 350, 100, 20);
        add(jr03);
        if (GameConfig.playerType == 3) {
            jr03.setSelected(true);
        }
        jr03.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                GameConfig.playerType = 3;
                jr03.setSelected(true);
                jr01.setSelected(false);
                jr02.setSelected(false);
            }
        });


    }

    /**
     * @param jFrame jframe
     */
    //显示设置弹窗
    public void showSettingInGame(JFrame jFrame) {
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

                } else {
                    ClientCore.playSound.start();
                }
            }
        });

        setBounds(jFrame.getBounds().x + 200, jFrame.getBounds().y + 200, 600, 600);
        jb02 = new JButton("返回游戏菜单");
        jb02.setBounds(220, 300, 200, 50);
        add(jb02);
        jb02.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnMainPanel();
            }
        });

    }

    /**
     * @param jFrame jframe
     */
//显示说明弹窗
    public void showInstruction(JFrame jFrame) {
        setTitle("游戏说明");
        setBounds(jFrame.getBounds().x + 200, jFrame.getBounds().y + 200, 600, 600);

        String text = "<html>元气骑士游戏说明<br>test<html>";
        jlabel_one = new JLabel(text);
        jlabel_one.setFont(new Font("KaiTi", Font.BOLD, 30));
        // 设置前景颜色
        jlabel_one.setForeground(Color.black);
        jlabel_one.setBounds(40, 0, 400, 350);
        add(jlabel_one);

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

    /**
     * @param jFrame jframe
     */
    public void showInstructionInGame(JFrame jFrame) {
        setTitle("游戏说明");
        setBounds(jFrame.getBounds().x + 200, jFrame.getBounds().y + 200, 600, 600);

        String text = "<html>元气骑士游戏说明<br>test<html>";
        jlabel_one = new JLabel(text);
        jlabel_one.setFont(new Font("KaiTi", Font.BOLD, 30));
        // 设置前景颜色
        jlabel_one.setForeground(Color.black);
        jlabel_one.setBounds(40, 0, 400, 350);
        add(jlabel_one);

        setBounds(jFrame.getBounds().x + 200, jFrame.getBounds().y + 200, 600, 600);
        jb02 = new JButton("返回游戏菜单");
        jb02.setBounds(220, 300, 200, 50);
        add(jb02);
        jb02.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    /**
     * @param jFrame jframe
     */
//显游戏内菜单弹窗
    public void showpause(JFrame jFrame) {
        setTitle("菜单");

        setBounds(jFrame.getBounds().x + 200, jFrame.getBounds().y + 200, 600, 600);
        jb02 = new JButton("退出游戏");
        jb02.setBounds(140, 420, 100, 50);
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
        jb03.setBounds(140, 70, 100, 50);
        add(jb03);
        jb03.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isStop = false;
                //GamePanel.gameRenderThread.start();
                StaticInfo.isrunning = true;
                dispose();
            }
        });

        jb04 = new JButton("暂停游戏");
        jb04.setBounds(140, 140, 100, 50);
        add(jb04);
        jb04.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isStop = true;
                pause();
            }
        });

        jb04 = new JButton("游戏帮助");
        jb04.setBounds(140, 210, 100, 50);
        add(jb04);
        jb04.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHelp(jFrame);
            }
        });

        jb05 = new JButton("游戏设置");
        jb05.setBounds(140, 280, 100, 50);
        add(jb05);
        jb05.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSet(jFrame);
            }
        });

        jb06 = new JButton("重新开始");
        jb06.setBounds(140, 350, 100, 50);
        add(jb06);
        jb06.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isStop) {
                    Object[] options = {"OK ", "CANCEL "};
                    JOptionPane.showOptionDialog(null, "您已暂停，无法重新开始", "提示", JOptionPane.DEFAULT_OPTION,
                            JOptionPane.WARNING_MESSAGE, null, options, options[0]);

                } else {
                    Order order = new Order(GameConfig.playerId, "restart");
                    String message = JSON.toJSONString(order);

                    boolean success = GameStartCore.sendQueue.offer(message);
                    if (!success) {
                        log.warn("队列已满，客户端无法把重新开始加入队列，消息是" + message);
                    }
                }
            }
        });
    }
    //显游戏失败弹窗

    /**
     * @param jFrame jframe
     */
    public void showfalse(JFrame jFrame) {
        setTitle("失败");
        setBounds(jFrame.getBounds().x + 200, jFrame.getBounds().y + 200, 600, 600);

        jb03 = new JButton("返回游戏");
        jb03.setBounds(140, 70, 100, 50);
        add(jb03);
        jb03.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isStop = false;
                //GamePanel.gameRenderThread.start();
                StaticInfo.isrunning = true;
                GameConfig.flag = true;
                dispose();
            }
        });

        jb06 = new JButton("重新开始");
        jb06.setBounds(140, 140, 100, 50);
        add(jb06);
        jb06.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isStop) {
                    Object[] options = {"OK ", "CANCEL "};
                    JOptionPane.showOptionDialog(null, "您已暂停，无法重新开始", "提示", JOptionPane.DEFAULT_OPTION,
                            JOptionPane.WARNING_MESSAGE, null, options, options[0]);

                } else {
                    Order order = new Order(GameConfig.playerId, "restart");
                    String message = JSON.toJSONString(order);

                    boolean success = GameStartCore.sendQueue.offer(message);
                    if (!success) {
                        log.warn("队列已满，客户端无法把重新开始加入队列，消息是" + message);
                    }
                }
            }
        });

        jb02 = new JButton("退出游戏");
        jb02.setBounds(140, 210, 100, 50);
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

    }
//返回主菜单

    public void returnMainPanel() {

        dispose();
//                Point p = ClientCore.mainPanel.getLocation();
//                ClientCore.mainPanel.dispose();
//                ClientCore.Start();
//                ClientCore.mainPanel.setLocation(p);

    }

    //暂停游戏
    public void pause() {
        StaticInfo.isrunning = false;
    }

    //游戏内帮助弹窗
    public void showHelp(JFrame jFrame) {
        new Dialog(jFrame, 4);
    }

    //游戏内设置弹窗
    public void showSet(JFrame jFrame) {
        new Dialog(jFrame, 5);
    }

    /**
     * @param jFrame jframe
     */
    public void showsuccess(JFrame jFrame) {
        setTitle("成功闯关！");
        setBounds(jFrame.getBounds().x + 200, jFrame.getBounds().y + 200, 600, 600);

        jb03 = new JButton("返回游戏");
        jb03.setBounds(140, 70, 100, 50);
        add(jb03);
        jb03.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isStop = false;
                //GamePanel.gameRenderThread.start();
                StaticInfo.isrunning = true;
                GameConfig.flag = true;
                dispose();
            }
        });

        jb06 = new JButton("重新开始");
        jb06.setBounds(140, 140, 100, 50);
        add(jb06);
        jb06.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isStop) {
                    Object[] options = {"OK ", "CANCEL "};
                    JOptionPane.showOptionDialog(null, "您已暂停，无法重新开始", "提示", JOptionPane.DEFAULT_OPTION,
                            JOptionPane.WARNING_MESSAGE, null, options, options[0]);

                } else {
                    Order order = new Order(GameConfig.playerId, "restart");
                    String message = JSON.toJSONString(order);

                    boolean success = GameStartCore.sendQueue.offer(message);
                    if (!success) {
                        log.warn("队列已满，客户端无法把重新开始加入队列，消息是" + message);
                    }
                }
            }
        });

        jb02 = new JButton("退出游戏");
        jb02.setBounds(140, 210, 100, 50);
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

    }
}
