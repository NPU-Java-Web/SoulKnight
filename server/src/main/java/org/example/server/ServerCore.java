package org.example.server;

import com.alibaba.fastjson.JSON;
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
import org.example.common.entity.Level;
import org.example.common.protocal.Connect;
import org.example.common.protocal.Result;
import org.example.server.handler.MyServerInboundHandler;
import org.example.server.thread.Calculation;
import org.example.server.thread.Refresh;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class ServerCore {
    public static Level level;
    public static volatile String GlobalInfo;
    public static BlockingQueue<String> messages;

    public ServerCore() {
        //只能设置level为1
        level = new Level(1);
        GlobalInfo = JSON.toJSONString(new Result(new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        messages = new ArrayBlockingQueue<>(100);

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

            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(20);
            config.setMaxIdle(10);
            JedisPool jedisPool = new JedisPool(config, "localhost", 6379);


            Thread calculation = new Thread(new Calculation(jedisPool), "calculation");
            calculation.start();

            Thread refresh = new Thread(new Refresh(jedisPool), "refresh");
            refresh.start();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
