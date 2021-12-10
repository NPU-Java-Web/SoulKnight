package org.example.server.service;

import org.example.common.entity.Monster;
import org.example.server.ServerCore;
import org.example.server.dao.MonsterDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class MonsterService {

    @Autowired
    private MonsterDAO monsterDAO;

    public void createInitialMonsters() {
        for (Monster monster : ServerCore.world.getInitialMonsters()) {
            monsterDAO.insert(monster);
        }
    }

    public List<Monster> list() {
        List<Monster> result = new ArrayList<>();
        Set<String> keys = monsterDAO.getAllMonsterKeys();
        for (String key : keys) {
            Monster monster = monsterDAO.selectByKey(key);
            result.add(monster);
        }
        return result;
    }

}
