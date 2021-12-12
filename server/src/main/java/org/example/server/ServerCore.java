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
import org.example.common.protocal.Connect;
import org.example.server.entity.World;
import org.example.server.entity.World1;
import org.example.server.handler.MyServerInboundHandler;
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
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup);
        b.channel(NioServerSocketChannel.class);
        b.childHandler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(new LineBasedFrameDecoder(1024));
                pipeline.addLast(new StringDecoder());
                pipeline.addLast(new MyServerInboundHandler());
            }
        });

        try {
            ChannelFuture f = b.bind(Connect.PORT).sync();
            if (f.isSuccess()) {
                System.out.println("服务器启动成功!");
            }

//            Thread calculation = new Thread(calculateController, "calculation");
//            calculation.start();

//            Thread refresh = new Thread(refreshController, "refresh");
//            refresh.start();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
