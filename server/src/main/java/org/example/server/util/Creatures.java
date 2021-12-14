package org.example.server.util;

import org.example.common.model.bullet.Bullet;
import org.example.common.model.monster.Monster;
import org.example.common.model.player.Player;
import org.example.common.protocal.Result;
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

    private List<Player> players;

    private List<Monster> monsters;

    private List<Bullet> bullets;

    public void initialize() {
        players = null;
        bullets = null;
        monsters = null;

        players = playerService.list();
        bullets = bulletService.list();
        monsters = monsterService.list();

    }

    public void bulletsCauseHarm() {
        //对于每一颗子弹
        for (Bullet bullet : bullets) {
            //先从怪物列表里遍历
            for (Monster monster : monsters) {
                if (getDistance(bullet, monster) <= bullet.getRadius()) {
                    monsterService.beingHurt(monster, bullet.getPower());
                    bulletService.remove(bullet);
                    return;
                }
            }
            //再从玩家列表里遍历
            for (Player player : players) {
                if (getDistance(bullet, player) <= bullet.getRadius()) {
                    playerService.beingHurt(player, bullet.getPower());
                    bulletService.remove(bullet);
                    return;
                }
            }
        }
    }

    public void bulletsFlying() {
        bulletService.updateLocation();
    }

    public Result getResult() {
        return new Result(players, bullets, monsters);
    }

    public static double getDistance(Bullet bullet, Monster monster) {
        return Math.sqrt((bullet.getX() - monster.getX()) * (bullet.getX() - monster.getX())
                + (bullet.getY() - monster.getY()) * (bullet.getY() - monster.getY()));
    }

    public static double getDistance(Bullet bullet, Player player) {
        return Math.sqrt((bullet.getX() - player.getX()) * (bullet.getX() - player.getX())
                + (bullet.getY() - player.getY()) * (bullet.getY() - player.getY()));
    }

}

