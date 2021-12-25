package org.example.server.service;

import lombok.extern.slf4j.Slf4j;
import org.example.server.dao.BulletDAO;
import org.example.server.dao.MonsterDAO;
import org.example.server.dao.PlayerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 命令服务
 *
 * @author 廖菁璞
 */
@Slf4j
@Service
public class OrderService {

    /**
     * 玩家DAO
     */
    @Autowired
    private PlayerDAO playerDAO;

    /**
     * 子弹DAO
     */
    @Autowired
    private BulletDAO bulletDAO;

    /**
     * 怪物DAO
     */
    @Autowired
    private MonsterDAO monsterDAO;

    /**
     * 重开游戏
     *
     * @param playerId 发起请求的玩家ID
     */
    public void restart(String playerId) {
        //删除这个玩家，这样下次再收到这个玩家信息的时候，按照新玩家的逻辑初始化
        playerDAO.deleteById(playerId);
        //清空缓存
        monsterDAO.flushDB();
    }


}
