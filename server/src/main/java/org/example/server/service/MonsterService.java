package org.example.server.service;

import lombok.extern.slf4j.Slf4j;
import org.example.common.model.bullet.Bullet;
import org.example.common.model.bullet.BulletFactory;
import org.example.common.model.monster.Monster;
import org.example.common.model.monster.MonsterShoot;
import org.example.common.model.player.Player;
import org.example.server.ServerCore;
import org.example.server.dao.BulletDAO;
import org.example.server.dao.MonsterDAO;
import org.example.server.thread.RefreshThread;
import org.example.server.util.Creatures;
import org.example.server.util.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class MonsterService {

    @Autowired
    private BulletDAO bulletDAO;

    @Autowired
    private MonsterDAO monsterDAO;

    public synchronized void initialize() {
        if (monsterDAO.isRemain()){
            return;
        }
        final int SINGLE_GENERATION=5;
        Queue<Monster> initialMonsters = ServerCore.level.getInitialMonsters();
       for (int i=0;i<SINGLE_GENERATION;i++) {
           Monster monster = initialMonsters.poll();
           if (monster!=null){
               monsterDAO.insert(monster);
           }else {
               RefreshThread.enterNextLevel=true;
           }

       }
    }

    public synchronized void flushDB(){
        monsterDAO.flushDB();
    }

    public synchronized List<Monster> list() {
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

    public synchronized void beingHurt(Monster monster, int difference) {
        if (difference >= monster.getBlood()) {
            monsterDAO.delete(monster);
        } else {
            monsterDAO.subtractBlood(monster, difference);
        }
    }

    public synchronized void tryStraightLaunch(Monster monster, Player player) {
        if (monsterDAO.isAggressive(monster)) {
            int x = player.getX() - monster.getX();
            int y = player.getY() - monster.getY();
            double l = Math.sqrt(Math.pow(Math.abs(x), 2) + Math.pow(Math.abs(y), 2));
            double angle;
            if (y > 0) {
                angle = Math.acos(x / l);
            } else {
                angle = 2 * Math.PI - Math.acos(x / l);
            }
            Bullet bullet = BulletFactory.makeBullet(4, monster.getMonsterId(), monster.getX(), monster.getY(), angle);
            if (bullet != null) {
                bulletDAO.insert(bullet);
            }
            monsterDAO.setLaunchCoolingTime(monster, 2);
        }
    }

    public synchronized void walkToPlayer(Monster monster, Player player) {
        int x = player.getX() - monster.getX();
        int y = player.getY() - monster.getY();
        double l = Math.sqrt(Math.pow(Math.abs(x), 2) + Math.pow(Math.abs(y), 2));
        double angle;
        if (y > 0) {
            angle = Math.acos(x / l);
        } else {
            angle = 2 * Math.PI - Math.acos(x / l);
        }
        final double FACTOR = 0.5;
        int deltaX = (int) (monster.getSpeed() * FACTOR * Math.cos(angle));
        int deltaY = (int) (monster.getSpeed() * FACTOR * Math.sin(angle));
        int newX = monster.getX() + deltaX;
        int newY = monster.getY() + deltaY;
        if (Verification.verifyLocation(newX, newY)) {
            monster.setX(newX);
            monster.setY(newY);
            monsterDAO.updateLocationById(monster);
        }
    }

    public synchronized boolean remainMonsters() {
        return monsterDAO.getAllKeys().size() > 0;
    }

    public synchronized void tryAllAroundLaunch(Monster monster) {
        if (monsterDAO.readyForUltimate(monster)) {
//            for (int i = 0; i < 10; i++) {
//                double angle = Math.toRadians(36 * i);
//                Bullet bullet = BulletFactory.makeBullet(5, monster.getMonsterId(), monster.getX(), monster.getY(), angle);
//                if (bullet != null) {
//                    bulletDAO.insert(bullet);
//                }
//            }
            List<Bullet> bullets= MonsterShoot.shoot(monster);
            if (bullets!=null){
                for (Bullet bullet:bullets){
                    if (bullet != null) {
                        bulletDAO.insert(bullet);
                    }
                }
            }
            monsterDAO.setUltimateCoolingTime(monster, 3);
        }
    }

    public synchronized void walkRandomly(Monster monster) {
        Random random = new Random();
        double angle = random.nextDouble() * 3.14159 * 2;
        final double FACTOR = 0.8;
        int deltaX = (int) (monster.getSpeed() * FACTOR * Math.cos(angle));
        int deltaY = (int) (monster.getSpeed() * FACTOR * Math.sin(angle));
        int newX = monster.getX() + deltaX;
        int newY = monster.getY() + deltaY;
        if (Verification.verifyLocation(newX, newY)) {
            monster.setX(newX);
            monster.setY(newY);
            monsterDAO.updateLocationById(monster);
        }
    }


}
