package org.example.client.display;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import java.io.File;


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
    //控制声音播放
    public static boolean[] b = new boolean[]{true, true, true, true};


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
    public void playByBite(){
        //AudioInputStream:音频输入流,是具有指定音频格式和长度的输入流。
        AudioInputStream audioInputStream = null;

        //SourceDataLine:源数据行,是可以写入数据的数据行
        SourceDataLine auline = null;

        try {
            //AudioSystem:该类充当取样音频系统资源的入口点
            audioInputStream = AudioSystem.getAudioInputStream(file);

            //AudioFormat: 是在声音流中指定特定数据安排的类
            AudioFormat format = audioInputStream.getFormat();

            //该构造方法：根据指定信息构造数据行的信息对象，这些信息包括单个音频格式
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format, AudioSystem.NOT_SPECIFIED);

            auline = (SourceDataLine) AudioSystem.getLine(info);
            //该方法：打开具有指定格式的行，这样可使行获得所有所需的系统资源并变得可操作。
            auline.open(format);

            auline.start();

            int nBytesRead = 0 ;

            //缓冲大小
            byte[] abData = new byte[512];

            while(nBytesRead != -1) {

                nBytesRead = audioInputStream.read(abData , 0 , abData.length);

                if(nBytesRead >= 0){
                    auline.write(abData, 0, nBytesRead);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }finally{

            auline.drain();

            auline.close();
        }

    }

    /**
     * 停止播放
     */
    //暂停音频播放
    public void stop() {
        clip.stop();
    }

    /**
     * 开始播放
     */
    //播放音频
    public void start() {
        clip.start();
    }

    /**
     * 回放背景音乐设置
     */
    //回放
    public void loop() {
        clip.loop(20);
    }

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
}
