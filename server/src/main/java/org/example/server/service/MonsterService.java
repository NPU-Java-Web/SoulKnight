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
import org.example.server.controller.RefreshThread;
import org.example.server.util.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * 怪物服务
 *
 * @author 廖菁璞
 */
@Slf4j
@Service
public class MonsterService {

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
     * 确保场上有怪物，避免“无怪可打”
     */
    public void initialize() {
        //如果场上本来就有怪物
        if (monsterDAO.isRemain()) {
            //这个方法就不用往后运行了
            return;
        }
        //如果场上没有怪物，那么就尝试刷出SINGLE_GENERATION个怪物
        final int SINGLE_GENERATION = 5;
        //从InitialMonsters里面取就行了
        Queue<Monster> initialMonsters = ServerCore.level.getInitialMonsters();
        for (int i = 0; i < SINGLE_GENERATION; i++) {
            //用poll方法，是为了取出来的同时，把怪物从队列里删掉，避免反复取出同一个怪物
            Monster monster = initialMonsters.poll();
            if (monster != null) {
                //插入怪物
                monsterDAO.insert(monster);
            } else {
                //这个关卡真的没怪物了，还是引导玩家进入下一关吧
                RefreshThread.enterNextLevel = true;
            }

        }
    }

    /**
     * 清空数据库（这个方法仅在开局用一次）
     */
    public void flushDB() {
        monsterDAO.flushDB();
    }

    /**
     * 获取当前所有怪物信息
     *
     * @return List
     */
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

    /**
     * 对指定怪物造成指定伤害
     *
     * @param monster    指定怪物
     * @param difference 指定伤害
     */
    public void beingHurt(Monster monster, int difference) {
        //如果伤害比怪物当前血量还多
        if (difference >= monster.getBlood()) {
            //怪物就直接死了
            monsterDAO.delete(monster);
        } else {
            //对怪物进行扣血
            monsterDAO.subtractBlood(monster, difference);
        }
    }

    /**
     * 怪物尝试对玩家进行直线发射
     *
     * @param monster 指定怪物
     * @param player  指定玩家
     */
    public void tryStraightLaunch(Monster monster, Player player) {
        //如果直线发射的技能CD还没到，也就是刚发射完没过多久呢，就不发射了。
        if (monsterDAO.isAggressive(monster)) {
            //这段代码是为了怪物发射子弹的角度，angle就是算出来的角度
            int x = player.getX() - monster.getX();
            int y = player.getY() - monster.getY();
            double l = Math.sqrt(Math.pow(Math.abs(x), 2) + Math.pow(Math.abs(y), 2));
            double angle;
            if (y > 0) {
                angle = Math.acos(x / l);
            } else {
                angle = 2 * Math.PI - Math.acos(x / l);
            }
            //制造一颗子弹
            Bullet bullet = BulletFactory.makeBullet(4, monster.getMonsterId(), monster.getX(), monster.getY(), angle);
            if (bullet != null) {
                //插入子弹
                bulletDAO.insert(bullet);
            }
            //设置冷却时间
            final long COOLING_TIME = 2;
            monsterDAO.setLaunchCoolingTime(monster, COOLING_TIME);
        }
    }

    /**
     * 指定怪物走向指定玩家
     *
     * @param monster 指定怪物
     * @param player  指定玩家
     */
    public void walkToPlayer(Monster monster, Player player) {
        //下面这段代码是用于计算目标角度的
        int x = player.getX() - monster.getX();
        int y = player.getY() - monster.getY();
        double l = Math.sqrt(Math.pow(Math.abs(x), 2) + Math.pow(Math.abs(y), 2));
        double angle;
        if (y > 0) {
            angle = Math.acos(x / l);
        } else {
            angle = 2 * Math.PI - Math.acos(x / l);
        }
        //为了易于和“没发现玩家”的速度区分，这里用一个系数
        final double FACTOR = 0.5;
        int deltaX = (int) (monster.getSpeed() * FACTOR * Math.cos(angle));
        int deltaY = (int) (monster.getSpeed() * FACTOR * Math.sin(angle));
        int newX = monster.getX() + deltaX;
        int newY = monster.getY() + deltaY;
        //如果新位置是有效的
        if (Verification.verifyLocation(newX, newY)) {
            //就把怪物的新位置进行更新
            monster.setX(newX);
            monster.setY(newY);
            monsterDAO.updateLocationById(monster);
        }
    }

    /**
     * 查询场上是否存在怪物
     *
     * @return 是否存在怪物
     */
    public boolean remainMonsters() {
        return monsterDAO.getAllKeys().size() > 0;
    }

    /**
     * 指定怪物尝试放大招
     *
     * @param monster 指定怪物
     */
    public void tryAllAroundLaunch(Monster monster) {
        //如果大招还没冷却好，就不能放大招。冷却好了才能放大招。
        if (monsterDAO.readyForUltimate(monster)) {
            //获取大招生成的子弹
            List<Bullet> bullets = MonsterShoot.shoot(monster);
            //插入这些子弹
            for (Bullet bullet : bullets) {
                if (bullet != null) {
                    bulletDAO.insert(bullet);
                }
            }
            //开启怪物的大招冷却
            monsterDAO.setUltimateCoolingTime(monster, monster.getCD());
        }
    }

    /**
     * 指定怪物随机走动
     *
     * @param monster 指定怪物
     */
    public void walkRandomly(Monster monster) {
        //获取当前怪物的朝向
        double angle = monsterDAO.getAngle(monster);
        //为了方便和“发现玩家状态”的走动速度进行区分
        final double FACTOR = 0.5;
        int deltaX = (int) (monster.getSpeed() * FACTOR * Math.cos(angle));
        int deltaY = (int) (monster.getSpeed() * FACTOR * Math.sin(angle));
        int newX = monster.getX() + deltaX;
        int newY = monster.getY() + deltaY;
        //如果位置是有效的
        if (Verification.verifyLocation(newX, newY)) {
            //更新怪物的位置
            monster.setX(newX);
            monster.setY(newY);
            monsterDAO.updateLocationById(monster);
            //如果位置是无效的，说明撞到墙了
        } else {
            //让怪物换个方向走，别朝着墙走
            monsterDAO.changeAngle(monster);
        }
    }


}
