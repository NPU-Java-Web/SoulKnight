package org.example.common.model.animation.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.common.model.animation.Animation;

/**
 * 此特效类为爆炸
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Explosion extends Animation {
    public static final int TYPE = 2;

    public Explosion(int x, int y) {
        super(TYPE, "", 1, x, y);
    }
}
