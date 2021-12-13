package org.example.server.service;

import lombok.extern.slf4j.Slf4j;
import org.example.common.entity.Player;
import org.example.server.dao.PlayerDAO;
import org.example.server.util.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class PlayerService {
    @Autowired
    private PlayerDAO playerDAO;

    public void saveOrUpdate(Player player) {
//        log.info("现在在尝试运行saveOrUpdate方法");
        if (!Verification.verifyLocation(player.getX(), player.getY())) {
            log.warn("请求中的玩家位置无效：" + player);
            return;
        }
        if (playerDAO.exists(player.getPlayerId())) {
            playerDAO.updateLocationById(player);
        } else {
            playerDAO.insert(player);
        }
    }

    public List<Player> list() {
        List<Player> result = new ArrayList<>();
        Set<String> keys = playerDAO.getAllPlayerKeys();
        for (String key : keys) {
            Player player;
            try {
                player = playerDAO.selectByKey(key);
                if (player != null) {
                    result.add(player);
                }
            } catch (NumberFormatException e) {
                log.error("在根据key获取player对象时出现异常" + e.getCause());
                e.printStackTrace();
            }
        }
        return result;
    }

    public void beingHurt(Player player, int difference) {
        playerDAO.subtractBlood(player, Math.min(player.getBlood(), difference));
    }

}
