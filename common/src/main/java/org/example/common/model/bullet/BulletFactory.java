package org.example.common.model.bullet;


import lombok.NoArgsConstructor;

/**
 * 子弹工厂
 */
@NoArgsConstructor
public class BulletFactory {

    /**
     * Desc:
     * @param bulletType 子弹种类
     * @param playerId 子弹归属对象的id
     * @param x 子弹x坐标
     * @param y 子弹y坐标
     * @param angle 子弹角度
     * @return 对应消息的子弹
     * @date 2021/12/21 10:00
     */
    public static Bullet makeBullet(int bulletType, String playerId, Integer x, Integer y, Double angle) {
        try {
            Class<?> c = Class.forName("org.example.common.model.bullet.entity.Bullet" + bulletType);
            return (Bullet) c.getDeclaredConstructor(String.class, Integer.class, Integer.class, Double.class)
                    .newInstance(playerId, x, y, angle);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
