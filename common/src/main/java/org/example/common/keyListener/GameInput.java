package org.example.common.keyListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

/**
 * 此类为添加所有的键盘监听，将所有添加到一个hashmap中，不同界面调用不同线程
 */
public class GameInput implements KeyListener {
    public static HashMap<Integer, Boolean> keys;
    public final static int KEY_COUNTS = 300;

    public void init() {
        keys = new HashMap<Integer, Boolean>(KEY_COUNTS);
        for (int i = 0; i < KEY_COUNTS; i++) {
            keys.put(i, false);
        }
    }

    @Override
    public void keyPressed(KeyEvent key) {
        keys.put(key.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent key) {
        keys.put(key.getKeyCode(), false);
    }

    @Override
    public void keyTyped(KeyEvent key) {

    }

    public static boolean getKeyDown(int keyCode) {
        return keys.get(keyCode);
    }

    public static void setKeys(int keycode) {
        keys.put(keycode, false);
    }
}
