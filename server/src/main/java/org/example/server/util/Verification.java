package org.example.server.util;

import org.example.server.ServerCore;

public class Verification {
    public static boolean verifyLocation(Integer x, Integer y) {
        int sideLength = ServerCore.level.getSideLength();
        if (x < 0 || x >= sideLength) {
            return false;
        }
        if (y < 0 || y >= sideLength) {
            return false;
        }
        if (ServerCore.level.getTerrain()[x][y]) {
            return false;
        }
        return true;
    }

    public static boolean atTransferArea1(Integer x, Integer y) {
        if (x < 412 || x > 582) {
            return false;
        }
        if (y < 0 || y > 100) {
            return false;
        }
        return true;
    }

    public static boolean atTransferArea2(Integer x, Integer y) {
        if (x < 0 || x > 100) {
            return false;
        }
        if (y < 470 || y > 580) {
            return false;
        }
        return true;
    }

}
