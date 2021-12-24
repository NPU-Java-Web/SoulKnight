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

/**
 * 子弹服务
 *
 * @author 廖菁璞
 */
@Slf4j
@Service
public class BulletService {

    /**
     * 子弹DAO
     */
    @Autowired
    private BulletDAO bulletDAO;

    /**
     * 保存子弹信息
     *
     * @param bullets 需要保存的子弹列表
     */
    public void save(List<Bullet> bullets) {
        for (Bullet bullet : bullets) {
            if (!Verification.verifyLocation(bullet.getX(), bullet.getY())) {
                log.warn("请求中的子弹位置无效：" + bullet);
                return;
            }
            bulletDAO.insert(bullet);
        }
    }

    /**
     * 获取当前所有子弹信息
     *
     * @return List
     */
    public List<Bullet> list() {
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

    /**
     * 删除指定的子弹
     *
     * @param bullet 想要删除的子弹
     */
    public void remove(Bullet bullet) {
        bulletDAO.delete(bullet);
    }

    /**
     * “一键”更新子弹的位置
     */
    public void updateLocation() {
        Set<String> keys = bulletDAO.getAllBulletKeys();
        //对于每个子弹都要更新
        for (String key : keys) {
            try {
                //还原出子弹的信息
                Bullet bullet = bulletDAO.selectByKey(key);
                if (bullet != null) {
                    //用系统当前时间减去子弹发射的时间，得到时间差，除以1000就可以以秒为单位
                    double elapsedTime = (System.currentTimeMillis() - bullet.getCreateTime()) / 1000.0;
                    //算一下x方向走的距离
                    int deltaX = (int) (bullet.getSpeed() * elapsedTime * Math.cos(bullet.getAngle()));
                    //算一下y方向走的距离
                    int deltaY = (int) (bullet.getSpeed() * elapsedTime * Math.sin(bullet.getAngle()));
                    //获取新的x
                    int newX = bullet.getX() + deltaX;
                    //获取新的y
                    int newY = bullet.getY() + deltaY;
                    //如果子弹的新位置是有效的
                    if (Verification.verifyLocation(newX, newY)) {
                        //将子弹信息更新
                        bullet.setX(newX);
                        bullet.setY(newY);
                        bulletDAO.updateLocationById(bullet);
                        //如果子弹信息是无效的
                    } else {
                        //把子弹删掉
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
