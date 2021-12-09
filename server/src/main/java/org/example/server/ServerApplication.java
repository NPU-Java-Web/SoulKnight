package org.example.server;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServerApplication {

    public static void main(String[] args) {

        ServerCore serverCore = new ServerCore();
        serverCore.start();

    }
}
