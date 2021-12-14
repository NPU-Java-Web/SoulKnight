package org.example.server.entity;

import com.alibaba.fastjson.JSON;
import org.example.common.config.level.Level1;
import org.example.common.protocal.Result;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class World1 extends World {
    public World1() {
        super(Level1.NUMBER,
                Level1.SIDE_LENGTH,
                Level1.TERRAIN,
                Level1.INITIAL_MONSTERS,
                JSON.toJSONString(new Result(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),Level1.NUMBER)));
    }
}
