package org.example.server.service;

import lombok.extern.slf4j.Slf4j;
import org.example.common.model.bullet.Bullet;
import org.example.common.model.bullet.BulletFactory;
import org.example.common.model.monster.Monster;
import org.example.common.model.player.Player;
import org.example.server.ServerCore;
import org.example.server.dao.BulletDAO;
import org.example.server.dao.MonsterDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class MonsterService {

    @Autowired
    private BulletDAO bulletDAO;

    @Autowired
    private MonsterDAO monsterDAO;

    public void initializeMonsters() {
        monsterDAO.deleteAll();
        for (Monster monster : ServerCore.level.getInitialMonsters()) {
            monsterDAO.insert(monster);
        }
    }

    public List<Monster> list() {
        List<Monster> result = new ArrayList<>();
        Set<String> keys = monsterDAO.getAllKeys();
        for (String key : keys) {
            Monster monster;
            try {
                monster = monsterDAO.selectByKey(key);
                if (monster != null) {
                    result.add(monster);
                }
            } catch (NumberFormatException e) {
                log.error("在根据key获取monster对象时出现异常" + e.getCause());
                e.printStackTrace();
            }
        }
        return result;
    }

    public void beingHurt(Monster monster, int difference) {
        if (difference >= monster.getBlood()) {
            monsterDAO.delete(monster);
        } else {
            monsterDAO.subtractBlood(monster, difference);
        }
    }

    public void tryStraightLaunch(Monster monster, Player player) {
        if (monsterDAO.isAggressive(monster)){
            int x = player.getX()-monster.getX();
            int y = player.getY()-monster.getY();
            double l = Math.sqrt(Math.pow(Math.abs(x), 2) + Math.pow(Math.abs(y), 2));
            double angle;
            if (y > 0) {
                angle = Math.acos(x / l);
            } else {
                angle = 2 * Math.PI - Math.acos(x / l);
            }
            Bullet bullet = BulletFactory.makeBullet(1, monster.getMonsterId(), monster.getX(), monster.getY(), angle);
            bulletDAO.insert(bullet);
            monsterDAO.setCoolingTime(monster,5);
        }
    }

}
