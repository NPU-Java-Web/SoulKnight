package org.example.common.model.animation.entity;

import org.example.common.model.animation.Animation;

/**
 * 此特效类为爆炸
 */
public class Explosion extends Animation {
    public static final int TYPE = 2;
    public Explosion(int state, int x, int y)
    {
        super("",TYPE,state,x,y);
    }
}
