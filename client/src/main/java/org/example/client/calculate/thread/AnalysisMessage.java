package org.example.client.calculate.thread;

import com.alibaba.fastjson.JSON;
import org.example.client.ClientCore;
import org.example.common.protocal.Result;

/**
 * 这个类从【接收队列】里取东西，并且解析成相应的列表，设置到ClientCore上面，供显示模块显示
 */
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
                    clientCore.setPlayers(result.getPlayers());
                    clientCore.setBullets(result.getBullets());
                    clientCore.setMonsters(result.getMonsters());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
