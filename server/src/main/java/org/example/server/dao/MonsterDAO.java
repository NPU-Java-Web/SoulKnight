package org.example.server.dao;

import org.example.common.model.monster.Monster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * 怪物DAO
 *
 * @author 廖菁璞
 */
@Repository
public class MonsterDAO {
    /**
     * Redis连接池
     */
    @Autowired
    private JedisPool jedisPool;

    /**
     * 怪物的前准
     */
    private static final String PREFIX = "monster:";

    /**
     * 普通技能（直线发射）冷却时间的前缀
     */
    private static final String CD_PREFIX = "cooling:";

    /**
     * 大招（向四周发射）冷却时间的前缀
     */
    private static final String ULTIMATE_PREFIX = "ultimate:";

    /**
     * 怪物朝向的前缀
     */
    private static final String ORIENTATION_PREFIX = "face:";

    /**
     * 根据monsterId还原出怪物
     *
     * @param monsterId monsterId
     * @return Monster
     */
    public Monster selectById(String monsterId) {
        String key = PREFIX + monsterId;
        return selectByKey(key);
    }

    /**
     * 根据缓存中的key还原出怪物
     *
     * @param key 缓存中的key
     * @return Monster
     */
    public Monster selectByKey(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            if (!jedis.exists(key)) {
                return null;
            }
            Map<String, String> map = jedis.hgetAll(key);
            Monster monster = new Monster();
            monster.setMonsterType(Integer.parseInt(map.get("monsterType")));
            monster.setMonsterId(map.get("monsterId"));
            monster.setX(Integer.parseInt(map.get("x")));
            monster.setY(Integer.parseInt(map.get("y")));
            monster.setAngle(Double.parseDouble(map.get("angle")));
            monster.setSpeed(Integer.parseInt(map.get("speed")));
            monster.setBlood(Integer.parseInt(map.get("blood")));
            monster.setCD(Integer.parseInt(map.get("CD")));
            monster.setVisibility(Integer.parseInt(map.get("visibility")));
            monster.setReward(Integer.parseInt(map.get("reward")));
            return monster;
        }
    }


    /**
     * 插入指定怪物
     *
     * @param monster 指定怪物
     */
    public void insert(Monster monster) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = PREFIX + monster.getMonsterId();
            if (jedis.exists(key)) {
                throw new RuntimeException("怪物已存在，无法插入同名怪物。");
            }
            jedis.hset(key, "monsterType", monster.getMonsterType().toString());
            jedis.hset(key, "monsterId", monster.getMonsterId());
            jedis.hset(key, "x", monster.getX().toString());
            jedis.hset(key, "y", monster.getY().toString());
            jedis.hset(key, "angle", monster.getAngle().toString());
            jedis.hset(key, "speed", monster.getSpeed().toString());
            jedis.hset(key, "blood", monster.getBlood().toString());
            jedis.hset(key, "CD", monster.getCD().toString());
            jedis.hset(key, "visibility", monster.getVisibility().toString());
            jedis.hset(key, "reward", monster.getReward().toString());
        }
    }

    /**
     * 获取所有key
     *
     * @return Set
     */
    public Set<String> getAllKeys() {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.keys("monster*");
        }
    }

    /**
     * 删除缓存中的所有数据（仅在开局时运行一次）
     */
    public void flushDB() {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.flushDB();
        }
    }

    /**
     * 删除指定怪物
     *
     * @param monster 指定怪物
     */
    public void delete(Monster monster) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = PREFIX + monster.getMonsterId();
            jedis.del(key);
        }
    }

    /**
     * 给指定怪物扣血
     *
     * @param monster    指定怪物
     * @param difference 扣除的血量
     */
    public void subtractBlood(Monster monster, int difference) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = PREFIX + monster.getMonsterId();
            jedis.hincrBy(key, "blood", -difference);
        }
    }

    /**
     * 判断普通技能（直线发射）是否已经冷却好，如果isAggressive返回true代表可以发射
     *
     * @param monster 指定怪物
     * @return 是否可以发射
     */
    public boolean isAggressive(Monster monster) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = CD_PREFIX + monster.getMonsterId();
            return !jedis.exists(key);
        }
    }

    /**
     * 设置普通技能的冷却时间
     *
     * @param monster 指定怪物
     * @param seconds 指定冷却时间（普通技能）
     */
    public void setLaunchCoolingTime(Monster monster, long seconds) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = CD_PREFIX + monster.getMonsterId();
            jedis.set(key, "冷却中");
            jedis.expire(key, seconds);
        }
    }

    /**
     * 更新指定怪物的位置
     *
     * @param monster 指定怪物
     */
    public void updateLocationById(Monster monster) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = PREFIX + monster.getMonsterId();
            jedis.hset(key, "x", monster.getX().toString());
            jedis.hset(key, "y", monster.getY().toString());
        }
    }

    /**
     * 判断大招（四周发射）是否已经冷却好，如果readyForUltimate返回true代表可以发射
     *
     * @param monster 指定怪物
     * @return 是否可以发射
     */
    public boolean readyForUltimate(Monster monster) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = ULTIMATE_PREFIX + monster.getMonsterId();
            return !jedis.exists(key);
        }
    }

    /**
     * 设置大招的冷却时间
     *
     * @param monster      指定怪物
     * @param milliseconds 指定冷却时间（大招）
     */
    public void setUltimateCoolingTime(Monster monster, long milliseconds) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = ULTIMATE_PREFIX + monster.getMonsterId();
            jedis.set(key, "冷却中");
            jedis.pexpire(key, milliseconds);
        }
    }

    /**
     * 查询场上是否还剩余怪物
     *
     * @return 是否剩余怪物
     */
    public boolean isRemain() {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.keys("monster*").size() > 0;
        }
    }

    /**
     * 获取指定怪物当前面向的角度
     *
     * @param monster 指定怪物
     * @return 角度
     */
    public double getAngle(Monster monster) {
        try (Jedis jedis = jedisPool.getResource()) {
            String angle = jedis.get(ORIENTATION_PREFIX + monster.getMonsterId());
            if (angle != null) {
                return Double.parseDouble(angle);
            }
            return 0;
        }
    }

    /**
     * 改变怪物面向的角度
     *
     * @param monster 指定怪物
     */
    public void changeAngle(Monster monster) {
        try (Jedis jedis = jedisPool.getResource()) {
            Random random = new Random();
            jedis.set(ORIENTATION_PREFIX + monster.getMonsterId(), String.valueOf(random.nextDouble() * 3.1416 * 2));
        }
    }


}
