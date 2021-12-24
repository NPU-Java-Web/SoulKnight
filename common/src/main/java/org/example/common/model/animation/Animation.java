package org.example.common.model.animation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 动画实体类
 *
 * @author 廖菁璞
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Animation {

    /**
     * 动画种类
     */
    protected Integer animationType;

    /**
     * 动画ID
     */
    protected String animationId;

    /**
     * 动画当前所属的状态（第几帧）
     */
    protected Integer state;

    /**
     * X坐标
     */
    protected Integer x;
    /**
     * Y坐标
     */
    protected Integer y;

}
