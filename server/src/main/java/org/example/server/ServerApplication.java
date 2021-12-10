package org.example.server;

import lombok.extern.slf4j.Slf4j;
import org.example.server.thread.CalculateThread;
import org.example.server.thread.RefreshThread;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@Slf4j
@SpringBootApplication
@EnableAsync
public class ServerApplication {

    public static void main(String[] args) {
//        SpringApplication.run(ServerApplication.class,args);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ThreadConfig.class);
        CalculateThread service2 = context.getBean(CalculateThread.class);
        service2.run();

        RefreshThread service1 = context.getBean(RefreshThread.class);
        service1.run();


//        for (int i = 0; i < 10; i++) {
//            service.f1(); // 执行异步任务
//            service.f2();
//        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        context.close();

        ServerCore serverCore = new ServerCore();
        serverCore.start();

    }
}
