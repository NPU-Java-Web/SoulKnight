package org.example.server.util;

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

@Component
public class Creatures {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private BulletService bulletService;

    @Autowired
    private MonsterService monsterService;

    @Autowired
    private AnimationService animationService;

    private List<Player> players;

    private List<Monster> monsters;

    private List<Bullet> bullets;

    private List<Animation> animations;

    public void initialize() {
        players = null;
        bullets = null;
        monsters = null;
        animations=null;

        players = playerService.list();
        bullets = bulletService.list();
        monsters = monsterService.list();
        animations=animationService.list();

    }

    public void bulletsCauseHarm() {
        //对于每一颗子弹
        for (Bullet bullet : bullets) {
            //先从怪物列表里遍历
            for (Monster monster : monsters) {
                if (bullet.getPlayerId().startsWith("m")) {
                    continue;
                }
                if (getDistance(bullet, monster) <= bullet.getRadius()) {
                    animationService.save(new Explosion(monster.getX(),monster.getY()));
                    monsterService.beingHurt(monster, bullet.getPower());
                    bulletService.remove(bullet);
                    return;
                }
            }

            //再从玩家列表里遍历
            for (Player player : players) {
                if (bullet.getPlayerId().equals(player.getPlayerId())) {
                    continue;
                }
                if (getDistance(bullet, player) <= bullet.getRadius()) {
                    animationService.save(new Explosion(player.getX(),player.getY()));
                    playerService.beingHurt(player, bullet.getPower());
                    bulletService.remove(bullet);
                    return;
                }
            }
        }
    }

    public void monstersAttack() {
        if (players.size() < 1) {
            return;
        }
        for (Monster monster : monsters) {
            Player player = players.get(0);
            if (getDistance(monster, player) < monster.getVisibility()) {
                monsterService.walkToPlayer(monster, player);
                monsterService.tryStraightLaunch(monster, player);
                monsterService.tryAllAroundLaunch(monster);
            }
        }
    }

    public void bulletsFlying() {
        bulletService.updateLocation();
    }

    public void AnimationsPlay(){
        animationService.play(animations);
    }

    public Result getResult() {
        return new Result(players, bullets, monsters, animations, ServerCore.level.getNumber());
    }

    public void addPortal(Portal portal){
        animations.add(portal);
    }

    public static double getDistance(Bullet bullet, Monster monster) {
        return Math.sqrt((bullet.getX() - monster.getX()) * (bullet.getX() - monster.getX())
                + (bullet.getY() - monster.getY()) * (bullet.getY() - monster.getY()));
    }

    public static double getDistance(Bullet bullet, Player player) {
        return Math.sqrt((bullet.getX() - player.getX()) * (bullet.getX() - player.getX())
                + (bullet.getY() - player.getY()) * (bullet.getY() - player.getY()));
    }

    public static double getDistance(Monster monster, Player player) {
        return Math.sqrt((monster.getX() - player.getX()) * (monster.getX() - player.getX())
                + (monster.getY() - player.getY()) * (monster.getY() - player.getY()));
    }

}

