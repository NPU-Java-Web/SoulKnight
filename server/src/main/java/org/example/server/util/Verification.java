package org.example.server.util;

import org.example.server.ServerCore;

/**
 * 验证位置有效性的工具类
 *
 * @author 廖菁璞
 */
public class Verification {
    /**
     * 验证位置有效性，避免超出地图、进入墙里
     *
     * @param x x坐标
     * @param y y坐标
     * @return 是否有效
     */
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

    /**
     * 验证是否进入了第一关的传送门
     *
     * @param x x坐标
     * @param y y坐标
     * @return 是否进入了传送门
     */
    public static boolean atTransferArea1(Integer x, Integer y) {
        if (x < 412 || x > 582) {
            return false;
        }
        if (y < 0 || y > 100) {
            return false;
        }
        return true;
    }

    /**
     * 验证是否进入了第二关的传送门
     *
     * @param x x坐标
     * @param y y坐标
     * @return 是否进入了传送门
     */
    public static boolean atTransferArea2(Integer x, Integer y) {
        if (x < 0 || x > 100) {
            return false;
        }
        if (y < 470 || y > 580) {
            return false;
        }
        return true;
    }

    /**
     * 验证是否进入了第三关的传送门
     *
     * @param x x坐标
     * @param y y坐标
     * @return 是否进入了传送门
     */
    public static boolean atTransferArea3(Integer x, Integer y) {
        if (x < 0 || x > 100) {
            return false;
        }
        if (y < 470 || y > 580) {
            return false;
        }
        return true;
    }

}
