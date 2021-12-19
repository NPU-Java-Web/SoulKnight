package org.example.common.model.animation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Animation {
    private String aniationId;
    /**
     * 种类
     */
    protected Integer type;

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
