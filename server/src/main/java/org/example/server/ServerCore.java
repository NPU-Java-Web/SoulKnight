package org.example.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import org.example.common.protocal.Connect;
import org.example.server.entity.World;
import org.example.server.entity.World1;
import org.example.server.handler.MyServerInboundHandler;
import org.example.server.thread.CalculateThread;
import org.example.server.thread.RefreshThread;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class ServerCore {
    public static World world;
    public static BlockingQueue<String> messageQueue;

    public ServerCore() {
        world = new World1();
        messageQueue = new LinkedBlockingQueue<>(20);
    }

    public void start() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup);
            b.channel(NioServerSocketChannel.class);
            b.childHandler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel ch) {
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast(new LineBasedFrameDecoder(1024));
                    pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                    pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
                    pipeline.addLast(new MyServerInboundHandler());
                }
            });


            ChannelFuture f = b.bind(Connect.PORT).sync();
            if (f.isSuccess()) {
                System.out.println("服务器启动成功!");
            }

            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ThreadConfig.class);

            CalculateThread calculateThread = context.getBean(CalculateThread.class);
            calculateThread.run();

            Thread.sleep(1000);

            RefreshThread refreshThread = context.getBean(RefreshThread.class);
            refreshThread.run();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
