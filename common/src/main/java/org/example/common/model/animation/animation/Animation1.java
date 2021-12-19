package org.example.common.model.animation.animation;

import org.example.common.model.animation.Animation;

/**
 * 此特效类为传送门
 */
public class Animation1 extends Animation {
    public static final int type = 1;


    public Animation1(int x,int y){
       super(type,null,x,y);
    }
}
