package org.example.client.calculate.thread;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.example.client.GameStartCore;
import org.example.client.calculate.service.StaticInfo;

/**
 * 这个类从【发送队列】里取东西，然后直接发给了服务器
 */
@Slf4j
public class UploadMessage implements Runnable {
    private final Channel channel;
    private final GameStartCore gameStartCore;
    public UploadMessage(Channel channel,GameStartCore gameStartCore) {
        this.channel = channel;
        this.gameStartCore = gameStartCore;
    }

    @Override
    public void run() {
        while (true) {
            if(!StaticInfo.isrunning)
            {
                break;
            }
            try {
                String message = GameStartCore.sendQueue.take();
                if (!message.contains("playerType") && !message.contains("bulletType")&&!message.contains("command")) {
                    log.warn("消息非法，客户端拒绝发送，消息为" + message);
                    continue;
                }
                channel.writeAndFlush(message+"\n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
