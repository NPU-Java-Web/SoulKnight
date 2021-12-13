package org.example.client.calculate.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.example.client.GameStartCore;

/**
 * 这个类读取服务器发过来的消息，往【接收队列】里放
 */
@Slf4j
public class MyClientInboundHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
        boolean success = GameStartCore.receiveQueue.offer((String) msg);
        if (!success) {
            log.warn("客户端从服务器收到了信息，但是无法往receiveQueue里放消息，消息是" + msg);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + " 服务器连接上啦");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + " 服务器断开啦");
    }

}