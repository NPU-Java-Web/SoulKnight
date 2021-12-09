package org.example.server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ServerApplicationTest {
    @Test
    public void test1() {
        int num1 = 2 * 3 * 4 * 5;
        int num2 = 120;
        Assertions.assertEquals(num1, num2);
    }
}
