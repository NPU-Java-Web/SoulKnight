package org.example.server;

/**
 * 服务器启动类
 *
 * @author 廖菁璞
 */
public class ServerApplication {

    /**
     * 启动方法
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        ServerCore serverCore = new ServerCore();
        serverCore.start();
    }
}
