package org.example.server.util;

import org.example.common.entity.Bullet;
import org.example.common.entity.Monster;
import org.example.common.entity.Player;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class RedisUtil {
    private static final AtomicInteger nextId = new AtomicInteger();

    public static String getPlayKey(Player player) {
        return "player:" + player.getPlayerId();
    }

    public static String getBulletKey() {
        return "bullet:" + nextId.incrementAndGet();
    }

    public static Player restorePlayerByMap(Map<String, String> map) {
        Player player = new Player();
        player.setPlayerType(Integer.parseInt(map.get("playerType")));
        player.setPlayerId(map.get("playerId"));
        player.setX(Integer.parseInt(map.get("x")));
        player.setY(Integer.parseInt(map.get("y")));
        player.setAngle(Double.parseDouble(map.get("angle")));
        player.setSpeed(Integer.parseInt(map.get("speed")));
        player.setBlood(Integer.parseInt(map.get("blood")));
        player.setScore(Integer.parseInt(map.get("score")));
        return player;
    }

    public static Bullet restoreBulletByMap(Map<String, String> map) {
        Bullet bullet = new Bullet();
        bullet.setBulletType(Integer.parseInt(map.get("bulletType")));
        bullet.setPlayerId(map.get("playerId"));
        bullet.setX(Integer.parseInt(map.get("x")));
        bullet.setY(Integer.parseInt(map.get("y")));
        bullet.setAngle(Double.parseDouble(map.get("angle")));
        bullet.setSpeed(Integer.parseInt(map.get("speed")));
        bullet.setRadius(Integer.parseInt(map.get("radius")));
        bullet.setPower(Integer.parseInt(map.get("power")));
        return bullet;
    }

    public static Monster restoreMonsterByMap(Map<String, String> map) {
        Monster monster = new Monster();
        monster.setMonsterType(Integer.parseInt(map.get("monsterType")));
        monster.setMonsterId(map.get("monsterId"));
        monster.setX(Integer.parseInt(map.get("x")));
        monster.setY(Integer.parseInt(map.get("y")));
        monster.setAngle(Double.parseDouble(map.get("angle")));
        monster.setSpeed(Integer.parseInt(map.get("speed")));
        monster.setBlood(Integer.parseInt(map.get("blood")));
        monster.setState(Integer.parseInt(map.get("state")));
        monster.setVisibility(Integer.parseInt(map.get("visibility")));
        monster.setReward(Integer.parseInt(map.get("reward")));
        return monster;
    }

}
