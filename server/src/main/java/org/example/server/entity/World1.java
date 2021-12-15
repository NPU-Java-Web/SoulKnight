package org.example.server.entity;

import org.example.common.config.level.Level1;
import org.springframework.stereotype.Component;


@Component
public class World1 extends World {
    public World1() {
        super(Level1.NUMBER,
                Level1.SIDE_LENGTH,
                Level1.TERRAIN,
                Level1.INITIAL_MONSTERS);
    }
}
