package org.example.client.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

/**
 * 此类为添加所有的键盘监听，将所有添加到一个hashmap中，不同界面调用不同线程
 */
public class GameInput implements KeyListener {
    public static HashMap<Integer, Boolean> keys;
    public final static int KEY_COUNTS = 300;

    /**
     * Desc:
     * <p>初始化hashmap</p>
     * @date 2021/12/19 10:53
     */
    public void init() {
        keys = new HashMap<Integer, Boolean>(KEY_COUNTS);
        for (int i = 0; i < KEY_COUNTS; i++) {
            keys.put(i, false);
        }
    }

    /**
     * Desc:
     * 将此键对应的hash表的值置为true
     * @param key 键盘按下事件
     * @date 2021/12/19 10:06
     */
    @Override
    public void keyPressed(KeyEvent key) {
        keys.put(key.getKeyCode(), true);
    }

    /**
     * Desc:
     * 将此键对应的hash表的值置为false
     * @param key 键盘按下事件
     * @date 2021/12/19 10:06
     */
    @Override
    public void keyReleased(KeyEvent key) {
        keys.put(key.getKeyCode(), false);
    }

    /**
     * Desc:
     * 出现键盘单击事件时会调用此方法
     * @param key 键盘单击事件
     * @date 2021/12/19 10:06
     */
    @Override
    public void keyTyped(KeyEvent key) {

    }

    /**
     * Desc:
     * @param keyCode 按键编号
     * @return 是否被按下
     * @date 2021/12/19 10:08
     */
    public static boolean getKeyDown(int keyCode) {
        return keys.get(keyCode);
    }
    /**
     * Desc:
     * @param keycode 按键编号
     * @date 2021/12/19 10:09
     */
    public static void setKeys(int keycode) {
        keys.put(keycode, false);
    }
}
