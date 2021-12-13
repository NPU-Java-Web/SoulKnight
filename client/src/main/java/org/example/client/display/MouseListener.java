package org.example.client.display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

class MouseListener extends JComponent {
    private static final int DEFAULT_WIDTH = 1000;
    private static final int DEFAULT_HEIGHT = 1000;
    private static final int SIDELENGTH = 10;
    private ArrayList<Rectangle2D> squares;
    private Rectangle2D current;
    public MouseListener(){
        squares = new ArrayList<>();
        current = null;
        addMouseListener(new MouseHandler());
        addMouseMotionListener(new MouseMotionHandler());
    }

    @Override
    public Dimension getPreferredSize() { return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT); }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        for (Rectangle2D r : squares){
            g2.draw(r);
        }
    }

    public Rectangle2D find(Point2D p){
        for (Rectangle2D r : squares){
            if (r.contains(p)) {return r;}
        }
        return null;
    }

    public void add(Point2D p){
        double x = p.getX();
        double y = p.getY();
        current = new Rectangle2D.Double(x - SIDELENGTH / 2, y - SIDELENGTH / 2, SIDELENGTH, SIDELENGTH);
        squares.add(current);
        repaint();
    }

    public void remove(Rectangle2D s){
        if (s == null) {return;}
        if (s == current){current = null;}
        squares.remove(s);
        repaint();
    }

    private class MouseHandler extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent event){    //鼠标按下时调用
            current = find(event.getPoint());
            if (current == null) {add(event.getPoint());}
        }
        @Override
        public void mouseClicked(MouseEvent event){   //鼠标点击时调用，点击两次则移除矩形
            current = find(event.getPoint());
            if (current != null && event.getClickCount() >= 2) {remove(current);}
        }
    }

    private class MouseMotionHandler implements MouseMotionListener {
        @Override
        public void mouseMoved(MouseEvent event){    //鼠标移动时调用
            if (find(event.getPoint()) == null) {setCursor(Cursor.getDefaultCursor());}
            else {setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));}
        }

        @Override
        public void mouseDragged(MouseEvent event){  //鼠标按住拖动时调用
            if (current != null){
                int x = event.getX();
                int y = event.getY();
                current.setFrame(x - SIDELENGTH / 2, y - SIDELENGTH / 2, SIDELENGTH, SIDELENGTH);
                repaint();
            }
        }
    }
}

