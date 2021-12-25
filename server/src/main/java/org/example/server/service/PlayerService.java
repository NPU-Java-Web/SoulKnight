package org.example.server.service;

import lombok.extern.slf4j.Slf4j;
import org.example.common.model.player.Player;
import org.example.server.dao.PlayerDAO;
import org.example.server.util.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 玩家服务
 *
 * @author 廖菁璞
 */
@Service
@Slf4j
public class PlayerService {

    /**
     * 玩家DAO
     */
    @Autowired
    private PlayerDAO playerDAO;

    /**
     * 如果是已有玩家就更新其位置，如果是新玩家就初始化其信息
     *
     * @param players List
     */
    public void saveOrUpdate(List<Player> players) {
        for (Player player : players) {
            //避免玩家进入墙里，在客户端验证位置的基础上，进行二次验证
            if (!Verification.verifyLocation(player.getX(), player.getY())) {
                log.warn("请求中的玩家位置无效：" + player);
                return;
            }
            //如果是已有玩家
            if (playerDAO.exists(player.getPlayerId())) {
                //更新其位置
                playerDAO.updateLocationById(player);
                //如果是新玩家
            } else {
                //初始化其信息
                playerDAO.insert(player);
            }
        }
    }

    /**
     * 获取所有玩家信息
     *
     * @return List
     */
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

    /**
     * 指定玩家收到指定伤害
     *
     * @param player     指定玩家
     * @param difference 指定伤害
     */
    public void beingHurt(Player player, int difference) {
        //为了避免把玩家的血量扣成负数，所以取子弹伤害和当前血量的较小者
        playerDAO.subtractBlood(player, Math.min(player.getBlood(), difference));
    }

}
