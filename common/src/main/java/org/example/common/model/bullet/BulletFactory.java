package org.example.common.model.bullet;


import lombok.NoArgsConstructor;

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


//    public static Bullet makeBullet(int bulletType){
//        switch (bulletType){
//            case GameConfig.BulletType.Classic:
//                return new Bullet1("1",0,0,0.0);
//            default:
//                return new Bullet1("1",0,0,0.0);
//        }
//    }
}
