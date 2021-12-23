package org.example.server.handler;

import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.example.common.model.bullet.Bullet;
import org.example.common.model.player.Player;
import org.example.common.protocal.Order;
import org.example.server.ServerCore;

@Slf4j
public class MyServerInboundHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
        Channel channel = ctx.channel();
        //log.info("服务器收到了客户端传来的消息：" + msg);
        String message = (String) msg;
        if (message.contains("playerType")) {
            Player player = JSON.parseObject(message, Player.class);
            boolean success = ServerCore.playerQueue.offer(player);
            if (!success) {
                log.warn("playerQueue已满，客户端发来的消息被丢弃" + player);
            }
        } else if (message.contains("bulletType")) {
            Bullet bullet = JSON.parseObject(message, Bullet.class);
            boolean success = ServerCore.bulletQueue.offer(bullet);
            if (!success) {
                log.warn("bulletQueue已满，客户端发来的消息被丢弃" + bullet);
            }
        } else if (message.contains("command")) {
            Order order = JSON.parseObject(message, Order.class);
            boolean success = ServerCore.orderQueue.offer(order);
            if (!success) {
                log.warn("orderQueue已满，客户端发来的消息被丢弃" + order);
            }
        } else {
            log.warn("无法解析消息内容，无法解析的消息内容为" + message);
        }
        channel.writeAndFlush(ServerCore.GlobalInfo + "\n");
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