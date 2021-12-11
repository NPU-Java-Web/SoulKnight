package org.example.server;

import lombok.extern.slf4j.Slf4j;
import org.example.server.thread.CalculateThread;
import org.example.server.thread.RefreshThread;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@Slf4j
//@SpringBootApplication
//@EnableAsync
public class ServerApplication {

    public static void main(String[] args) {
//        SpringApplication.run(ServerApplication.class,args);
//        context.close();

        ServerCore serverCore = new ServerCore();
        serverCore.start();

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ThreadConfig.class);
        CalculateThread service2 = context.getBean(CalculateThread.class);
        service2.run();

        RefreshThread service1 = context.getBean(RefreshThread.class);
        service1.run();

    }
}
