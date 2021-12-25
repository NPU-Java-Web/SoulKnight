package org.example.client.calculate.communication;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import org.example.client.GameStartCore;
import org.example.client.calculate.communication.MyClientInboundHandler;
import org.example.client.calculate.communication.AnalysisMessage;
import org.example.client.calculate.communication.UploadMessage;
import org.example.common.protocol.Connect;

import java.net.InetSocketAddress;

/**
 * 客户端计算主模块
 * <p>启动后开始运行客户端通信模块{@code AnalysisMessage}和{@code UploadMessage}。
 * 从服务端获取关键信息并将本地客户端信息传输到服务端
 * </p>
 *
 * @see java.lang.Runnable
 * @see GameStartCore
 * @see Bootstrap
 * @see UploadMessage
 * @see AnalysisMessage
 * @see Channel
 * @see NioEventLoopGroup
 */
public class CalculationMain implements Runnable {
    private final GameStartCore gameStartCore;

    public CalculationMain(GameStartCore gameStartCore) {
        this.gameStartCore = gameStartCore;
    }


    @Override
    public void run() {
        NioEventLoopGroup group = new NioEventLoopGroup(2);

        try {

            Bootstrap b = new Bootstrap();
            b.group(group);
            b.channel(NioSocketChannel.class);
            b.handler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel ch) {
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast(new LineBasedFrameDecoder(32768));
                    pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                    pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
                    pipeline.addLast(new MyClientInboundHandler());
                }
            });

            ChannelFuture future = b.connect(new InetSocketAddress(Connect.ADDRESS, Connect.PORT)).sync();
            Channel channel = future.channel();


            Thread uploadMessage = new Thread(new UploadMessage(channel, gameStartCore), "uploadMessage");
            uploadMessage.start();

            Thread analysisMessage = new Thread(new AnalysisMessage(gameStartCore), "analysisMessage");
            analysisMessage.start();
            channel.closeFuture().sync();
        } catch (Exception e) {
            group.shutdownGracefully();
            e.printStackTrace();
        }
    }

}
