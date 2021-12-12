package org.example.server.util;

import org.example.server.ServerCore;

public class Verification {
    public static boolean verifyLocation(Integer x, Integer y) {
        int sideLength = ServerCore.world.getSizeLength();
        if (x < 0 || x > sideLength) {
            return false;
        }
        if (y < 0 || y > sideLength) {
            return false;
        }
        if (ServerCore.world.getTerrain()[x][y]) {
            return false;
        }
        return true;
    }
}
