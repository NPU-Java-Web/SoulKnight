package org.example.client.Input;

import lombok.extern.slf4j.Slf4j;
import org.example.client.calculate.service.GameDataProcess;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * <p>进行鼠标事件监听</p>
 *
 * @see MouseListener
 * @see MouseEvent
 */
@Slf4j
public class GameMouseListener implements MouseListener {
    /**
     * 鼠标释放
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * 鼠标按下
     */
    @Override
    public void mousePressed(MouseEvent e) {
    }

    /**
     * 鼠标离开组件
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**
     * 鼠标进入组件
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * 鼠标单击
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        log.info("clicked: (" + (e.getX() - 8) + "," + (e.getY() - 30) + ")");
        //子弹发射
        GameDataProcess.shootBullet(e);

    }
}
