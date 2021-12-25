package org.example.client;

import org.example.common.config.level.Level;
import org.example.common.config.level.Level1;
import org.example.common.config.level.Level2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClientApplicationTest {
    @Test
    public void test1() {
        int num1 = 1 + 2 + 3 + 4;
        int num2 = 10;
        Assertions.assertEquals(num1, num2);
        System.out.println(getClass());
    }

    @Test
    public void testPoint(){
        int x = 750;
        int y = 600;
        Level level = new Level2();
        System.out.println(level.getTerrain()[x][y]);
    }
}
