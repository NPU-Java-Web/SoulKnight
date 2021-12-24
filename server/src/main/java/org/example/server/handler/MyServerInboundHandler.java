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

/**
 * 自定义入站消息处理方法
 *
 * @author 廖菁璞
 */
@Slf4j
public class MyServerInboundHandler extends SimpleChannelInboundHandler<Object> {

    /**
     * 收到可读事件后对消息进行处理
     *
     * @param ctx 用于获取Channel
     * @param msg 用于获取消息
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
        Channel channel = ctx.channel();
        String message = (String) msg;
        //如果收到的是玩家消息
        if (message.contains("playerType")) {
            Player player = JSON.parseObject(message, Player.class);
            boolean success = ServerCore.playerQueue.offer(player);
            if (!success) {
                log.warn("playerQueue已满，客户端发来的消息被丢弃" + player);
            }
            //如果收到的是子弹消息
        } else if (message.contains("bulletType")) {
            Bullet bullet = JSON.parseObject(message, Bullet.class);
            boolean success = ServerCore.bulletQueue.offer(bullet);
            if (!success) {
                log.warn("bulletQueue已满，客户端发来的消息被丢弃" + bullet);
            }
            //如果收到的是命令消息
        } else if (message.contains("command")) {
            Order order = JSON.parseObject(message, Order.class);
            boolean success = ServerCore.orderQueue.offer(order);
            if (!success) {
                log.warn("orderQueue已满，客户端发来的消息被丢弃" + order);
            }
            //如果收到的是无法解析的消息
        } else {
            log.warn("无法解析消息内容，无法解析的消息内容为" + message);
        }
        //返回当前的全局信息，以换行符作为两条消息的分隔符。（不加分隔符的话，对方就不知道一大串字符到哪里才算消息结束）
        channel.writeAndFlush(ServerCore.GlobalInfo + "\n");
    }

    /**
     * 如果收到了上线信息
     *
     * @param ctx 用于获取Channel
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + " 上线了");
    }

    /**
     * 如果收到了下线信息
     *
     * @param ctx 用于获取Channel
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + " 下线了");
    }

}