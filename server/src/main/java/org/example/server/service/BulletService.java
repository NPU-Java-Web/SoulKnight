package org.example.server.service;

import lombok.extern.slf4j.Slf4j;
import org.example.common.entity.Bullet;
import org.example.server.dao.BulletDAO;
import org.example.server.util.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class BulletService {
    @Autowired
    private BulletDAO bulletDAO;

    public void save(Bullet bullet) {
        if (!Verification.verifyLocation(bullet.getX(), bullet.getY())) {
            log.warn("请求中的玩家位置无效：" + bullet);
            return;
        }
        bulletDAO.insert(bullet);
    }

    public List<Bullet> list() {
        List<Bullet> result = new ArrayList<>();
        Set<String> keys = bulletDAO.getAllBulletKeys();
        for (String key : keys) {
            Bullet bullet = bulletDAO.selectByKey(key);
            result.add(bullet);
        }
        return result;
    }


}
