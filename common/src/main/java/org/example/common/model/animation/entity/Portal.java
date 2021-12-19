package org.example.common.model.animation.entity;

import org.example.common.model.animation.Animation;

/**
 * 此特效类为传送门
 */
public class Portal extends Animation {
    public static final int TYPE = 1;

    public Portal(int x, int y) {
        super("",TYPE, null, x, y);
    }
}
