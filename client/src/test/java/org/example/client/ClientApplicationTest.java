package org.example.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClientApplicationTest {
    @Test
    public void test1() {
        int num1 = 1 + 2 + 3 + 4;
        int num2 = 10;
        Assertions.assertEquals(num1, num2);
    }
}
