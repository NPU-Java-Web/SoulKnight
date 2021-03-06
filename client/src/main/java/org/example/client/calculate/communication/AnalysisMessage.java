package org.example.client.calculate.communication;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.example.client.GameStartCore;
import org.example.client.calculate.service.StaticInfo;
import org.example.common.protocol.Result;

/**
 * 这个类从【接收队列】里取东西，并且解析成相应的列表，设置到{@code GameStartCore}上面，供显示模块显示
 *
 * @see Runnable
 * @see GameStartCore
 * @see Result
 */
@Slf4j
public class AnalysisMessage implements Runnable {
    private final GameStartCore gameStartCore;

    public AnalysisMessage(GameStartCore gameStartCore) {
        this.gameStartCore = gameStartCore;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (!StaticInfo.isrunning) {
                    break;
                }
                String message = GameStartCore.receiveQueue.take();
                if (gameStartCore.isStart()) {
                    Result result = JSON.parseObject(message, Result.class);
                    boolean success = gameStartCore.getFrames().offer(result);
                    if (!success) {
                        log.warn("frames队列已满，服务器传回来的一帧被丢弃，内容是" + result);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
