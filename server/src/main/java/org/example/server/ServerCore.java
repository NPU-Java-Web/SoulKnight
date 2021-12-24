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
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import org.example.common.config.level.Level;
import org.example.common.config.level.Level1;
import org.example.common.model.bullet.Bullet;
import org.example.common.model.player.Player;
import org.example.common.protocol.Connect;
import org.example.common.protocol.Order;
import org.example.common.protocol.Result;
import org.example.server.handler.MyServerInboundHandler;
import org.example.server.thread.RefreshThread;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 存储游戏实时信息
 *
 * @author 廖菁璞
 */
@Component
public class ServerCore {
    /**
     * 地图信息
     */
    public static Level level;
    /**
     * 当前时刻全局信息
     */
    public static volatile String GlobalInfo;
    /**
     * 玩家信息消息队列
     */
    public static BlockingQueue<Player> playerQueue;
    /**
     * 子弹信息消息队列
     */
    public static BlockingQueue<Bullet> bulletQueue;
    /**
     * 命令信息消息队列
     */
    public static BlockingQueue<Order> orderQueue;

    /**
     * 构造方法
     */
    public ServerCore() {
        level = new Level1();
        GlobalInfo = JSON.toJSONString(new Result(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), level.getNumber()));
        playerQueue = new LinkedBlockingQueue<>(50);
        bulletQueue = new LinkedBlockingQueue<>(30);
        orderQueue = new LinkedBlockingQueue<>(30);
    }

    /**
     * 服务器启动方法
     */
    public void start() {
        EventLoopGroup bossGroup = new NioEventLoopGroup(4);
        EventLoopGroup workerGroup = new NioEventLoopGroup(4);
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup);
            b.channel(NioServerSocketChannel.class);
            b.childHandler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel ch) {
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast(new LineBasedFrameDecoder(32768));
                    pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                    pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
                    pipeline.addLast(new MyServerInboundHandler());
                }
            });
            ChannelFuture f = b.bind(Connect.PORT).sync();
            if (f.isSuccess()) {
                System.out.println("服务器启动成功!");
            }

            //服务器已经启动成功，开启新的线程对收到的各种信息进行计算
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ThreadConfig.class);
            RefreshThread refreshThread = context.getBean(RefreshThread.class);
            refreshThread.run();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
