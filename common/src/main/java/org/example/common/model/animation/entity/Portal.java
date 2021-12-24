package org.example.common.model.animation.entity;

import org.example.common.model.animation.Animation;

/**
 * 传送门动画
 */
public class Portal extends Animation {
    public static final int TYPE = 1;

    public Portal(int x, int y) {
        super(TYPE, "", 0, x, y);
    }
}
