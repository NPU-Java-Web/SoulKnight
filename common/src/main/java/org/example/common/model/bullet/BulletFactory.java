package org.example.common.model.bullet;


import lombok.NoArgsConstructor;

/**
 * 子弹工厂
 */
@NoArgsConstructor
public class BulletFactory {
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
