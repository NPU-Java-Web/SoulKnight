package org.example.server.service;

import lombok.extern.slf4j.Slf4j;
import org.example.server.dao.BulletDAO;
import org.example.server.dao.MonsterDAO;
import org.example.server.dao.PlayerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderService {

    @Autowired
    private PlayerDAO playerDAO;

    @Autowired
    private BulletDAO bulletDAO;

    @Autowired
    private MonsterDAO monsterDAO;

    public void restart(String playerId){
        playerDAO.deleteById(playerId);
        bulletDAO.deleteAll();
        monsterDAO.flushDB();
    }


}
