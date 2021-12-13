package org.example.common.keyListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;


public class GameInput implements KeyListener {
    private static HashMap<Integer, Boolean> keys;
    public final static int KEY_COUNTS = 300;

    public void init()
    {
        keys = new HashMap<Integer, Boolean>(KEY_COUNTS);
        for(int i = 0; i < KEY_COUNTS; i++)
        {
            keys.put(i, false);
        }
    }

    @Override
    public void keyPressed(KeyEvent key)
    {
        keys.put(key.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent key)
    {
        keys.put(key.getKeyCode(), false);
    }

    @Override
    public void keyTyped(KeyEvent key)
    {

    }

    public static boolean getKeyDown(int keyCode)
    {
        return keys.get(keyCode);
    }
}
