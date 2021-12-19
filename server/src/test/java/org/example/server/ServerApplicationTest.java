package org.example.server;

import org.example.common.model.bullet.BulletFactory;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;


public class ServerApplicationTest {
    @Test
    public void test1() {

//        Jedis jedis = new Jedis("localhost", 6379);
//        System.out.println(jedis.hmget());
//        jedis.close();
        System.err.println(BulletFactory.makeBullet(3,"hadytjda89",84,5,8.9));
    }
}
