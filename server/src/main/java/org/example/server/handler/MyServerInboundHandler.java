package org.example.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.example.server.ServerCore;

@Slf4j
public class MyServerInboundHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
        Channel channel = ctx.channel();
//        log.info("服务器收到了客户端传来的消息：" + msg);
        boolean succes = ServerCore.messageQueue.offer((String) msg);
        if (!succes) {
            log.warn("服务器的队列已满，无法放入。消息内容为" + msg);
        }
//        System.err.println("quanju"+ServerCore.world.getGlobalInfo());
        channel.writeAndFlush(ServerCore.world.getGlobalInfo()+"\n");
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + " 上线了");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + " 下线了");
    }

}