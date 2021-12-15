package org.example.server.service;

import lombok.extern.slf4j.Slf4j;
import org.example.common.model.bullet.Bullet;
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

    public synchronized void save(Bullet bullet) {
        if (!Verification.verifyLocation(bullet.getX(), bullet.getY())) {
            log.warn("请求中的玩家位置无效：" + bullet);
            return;
        }
        bulletDAO.insert(bullet);
    }

    public synchronized List<Bullet> list() {
        List<Bullet> result = new ArrayList<>();

        Set<String> keys = bulletDAO.getAllBulletKeys();
        for (String key : keys) {
            Bullet bullet;
            try {
                bullet = bulletDAO.selectByKey(key);
                if (bullet != null) {
                    result.add(bullet);
                }
            } catch (NumberFormatException e) {
                log.error("在根据key获取bullet对象时出现异常" + e.getCause());
                e.printStackTrace();
            }

        }
        return result;
    }

    public synchronized void remove(Bullet bullet) {
        log.error("remove这里调用了删除子弹");
        bulletDAO.delete(bullet);
    }

    public synchronized void updateLocation() {
        Set<String> keys = bulletDAO.getAllBulletKeys();
        for (String key : keys) {
            try {
                Bullet bullet = bulletDAO.selectByKey(key);
                if (bullet != null) {
                    long elapsedTime = (System.currentTimeMillis() - bullet.getCreateTime()) / 1000;
                    int deltaX = (int) (bullet.getSpeed() * elapsedTime * Math.cos(bullet.getAngle()));
                    int deltaY = (int) (bullet.getSpeed() * elapsedTime * Math.sin(bullet.getAngle()));
                    int newX = bullet.getX() + deltaX;
                    int newY = bullet.getY() + deltaY;
                    if (Verification.verifyLocation(newX, newY)) {
                        bullet.setX(newX);
                        bullet.setY(newY);
                        bulletDAO.updateLocation(bullet);
                    } else {
                        log.error("upload这里调用了删除子弹");
                        bulletDAO.delete(bullet);
                    }
                }
            } catch (Exception e) {
                log.error("在更新bullet位置时出现异常" + e.getCause());
                e.printStackTrace();
            }
        }
    }


}
