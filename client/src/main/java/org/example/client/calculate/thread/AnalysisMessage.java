package org.example.client.calculate.thread;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.example.client.ClientCore;
import org.example.common.protocal.Result;

/**
 * 这个类从【接收队列】里取东西，并且解析成相应的列表，设置到ClientCore上面，供显示模块显示
 */
@Slf4j
public class AnalysisMessage implements Runnable {
    private final ClientCore clientCore;

    public AnalysisMessage(ClientCore clientCore) {
        this.clientCore = clientCore;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String message = ClientCore.receiveQueue.take();
                if (clientCore.isStart()) {
                    Result result = JSON.parseObject(message, Result.class);
                    boolean success = clientCore.getFrames().offer(result);
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
