package org.example.server.util;

import lombok.extern.slf4j.Slf4j;
import org.example.common.model.animation.Animation;
import org.example.common.model.animation.entity.Explosion;
import org.example.common.model.animation.entity.Portal;
import org.example.common.model.bullet.Bullet;
import org.example.common.model.monster.Monster;
import org.example.common.model.player.Player;
import org.example.common.protocal.Result;
import org.example.server.ServerCore;
import org.example.server.service.AnimationService;
import org.example.server.service.BulletService;
import org.example.server.service.MonsterService;
import org.example.server.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 汇集各种Service，处理复杂的业务逻辑
 *
 * @author 廖菁璞
 */
@Slf4j
@Component
public class Creatures {

    /**
     * 玩家服务
     */
    @Autowired
    private PlayerService playerService;

    /**
     * 子弹服务
     */
    @Autowired
    private BulletService bulletService;

    /**
     * 怪物服务
     */
    @Autowired
    private MonsterService monsterService;

    /**
     * 动画服务
     */
    @Autowired
    private AnimationService animationService;

    /**
     * 当前所有玩家
     */
    private List<Player> players;

    /**
     * 当前所有玩家
     */
    private List<Monster> monsters;

    /**
     * 当前所有子弹
     */
    private List<Bullet> bullets;

    /**
     * 当前所有动画
     */
    private List<Animation> animations;

    /**
     * 把上面几个列表重新获取，确保得到的是最新数据
     */
    public void initialize() {
        players = playerService.list();
        bullets = bulletService.list();
        monsters = monsterService.list();
        animations = animationService.list();

    }

    /**
     * 子弹“一键”造成伤害
     */
    public void bulletsCauseHarm() {
        //对于每一颗子弹
        for (Bullet bullet : bullets) {
            //先从怪物列表里遍历
            for (Monster monster : monsters) {
                //不要让怪物发出的子弹攻击到怪物
                if (bullet.getPlayerId().startsWith("m")) {
                    continue;
                }
                //如果怪物在人物子弹的杀伤半径内，则造成伤害
                if (getDistance(bullet, monster) <= bullet.getRadius()) {
                    //创建动画
                    animationService.save(new Explosion(monster.getX(), monster.getY()));
                    //造成伤害
                    monsterService.beingHurt(monster, bullet.getPower());
                    //移除子弹
                    bulletService.remove(bullet);
                    return;
                }
            }

            //再从玩家列表里遍历
            for (Player player : players) {
                //不要让玩家发射的子弹伤害到自己
                if (bullet.getPlayerId().equals(player.getPlayerId())) {
                    continue;
                }
                //如果除了自己的生物在人物子弹的杀伤半径内，则造成伤害
                if (getDistance(bullet, player) <= bullet.getRadius()) {
                    //创建动画
                    animationService.save(new Explosion(player.getX(), player.getY()));
                    //造成伤害
                    playerService.beingHurt(player, bullet.getPower());
                    //移除子弹
                    bulletService.remove(bullet);
                    return;
                }
            }
        }
    }

    /**
     * 怪物“一键”进行攻击
     */
    public void monstersAttack() {
        //没有玩家就不攻击了
        if (players.size() < 1) {
            return;
        }
        //每个怪物都要尝试进行攻击
        for (Monster monster : monsters) {
            boolean foundPlayer = false;
            //遍历玩家列表，争取找到能攻击的玩家
            for (Player player : players) {
                //如果玩家能被怪物看到
                if (getDistance(monster, player) < monster.getVisibility()) {
                    //怪物找到的玩家
                    foundPlayer = true;
                    //怪物走向玩家
                    monsterService.walkToPlayer(monster, player);
                    //怪物尝试对着玩家直线发射子弹
                    monsterService.tryStraightLaunch(monster, player);
                    //怪物尝试放大招
                    monsterService.tryAllAroundLaunch(monster);
                    break;
                }
            }
            //如果没有找到玩家
            if (!foundPlayer) {
                //怪物就会朝着一个方向走下去
                monsterService.walkRandomly(monster);
            }
        }
    }

    /**
     * 子弹“一键”更新自身位置
     */
    public void bulletsFlying() {
        bulletService.updateLocation();
    }

    /**
     * 动画“一键”播放
     */
    public void AnimationsPlay() {
        animationService.play(animations);
    }

    /**
     * 获取场面上所有信息，也就是说把当前这一帧返回
     *
     * @return Result
     */
    public Result getResult() {
        return new Result(players, bullets, monsters, animations, ServerCore.level.getNumber());
    }

    /**
     * 给地图加上传送门
     *
     * @param portal 指定传送门信息
     */
    public void addPortal(Portal portal) {
        animations.add(portal);
    }

    /**
     * 获取子弹和怪物之间的距离
     *
     * @param bullet  指定子弹
     * @param monster 指定怪物
     * @return 距离
     */
    public static double getDistance(Bullet bullet, Monster monster) {
        return Math.sqrt((bullet.getX() - monster.getX()) * (bullet.getX() - monster.getX())
                + (bullet.getY() - monster.getY()) * (bullet.getY() - monster.getY()));
    }

    /**
     * 获取子弹和玩家之间的距离
     *
     * @param bullet 指定子弹
     * @param player 指定玩家
     * @return 距离
     */
    public static double getDistance(Bullet bullet, Player player) {
        return Math.sqrt((bullet.getX() - player.getX()) * (bullet.getX() - player.getX())
                + (bullet.getY() - player.getY()) * (bullet.getY() - player.getY()));
    }

    /**
     * 获取怪物和玩家之间的距离
     *
     * @param monster 指定怪物
     * @param player  指定玩家
     * @return 距离
     */
    public static double getDistance(Monster monster, Player player) {
        return Math.sqrt((monster.getX() - player.getX()) * (monster.getX() - player.getX())
                + (monster.getY() - player.getY()) * (monster.getY() - player.getY()));
    }

}

