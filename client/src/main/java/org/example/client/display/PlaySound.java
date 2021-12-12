package org.example.client.display;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 *
 * 此类为声音启动类
 *
 **/
public class PlaySound {

    //音频文件
    private File file;
    //音频输入
    private AudioInputStream stream;
    //音频格式
    private AudioFormat format;
    //音频行信号
    private DataLine.Info info;
    //音频
    private Clip clip;
    public static boolean[] b = new boolean[]{true, true, true, true};//控制声音播放

    /**
     * 打开声音文件方法
     */
    public void open(String s) {
        file = new File(s);//音频文件对象
        try {
            stream = AudioSystem.getAudioInputStream(file);//音频输入流
            format = stream.getFormat();//音频格式对象

        } catch (UnsupportedAudioFileException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }
    /**
     * 建立播放音频的音频行
     */
    public void play() {
        info = new DataLine.Info(Clip.class, format);//音频行信息
        try {
            clip = (Clip) AudioSystem.getLine(info);//音频行
            clip.open(stream);//将音频数据读入音频行
        } catch (LineUnavailableException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    /**
     * 停止播放
     */
    public void stop() {
        clip.stop();//暂停音频播放
    }

    /**
     * 开始播放
     */
    public void start() {
        clip.start();//播放音频
    }

    /**
     * 回放背景音乐设置
     */
    public void loop() {
        clip.loop(20);//回放
    }
}
