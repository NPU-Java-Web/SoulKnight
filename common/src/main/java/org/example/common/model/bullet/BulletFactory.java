package org.example.common.model.bullet;


import lombok.NoArgsConstructor;
import org.example.common.config.GameConfig;
import org.example.common.model.bullet.Bullet;
import org.example.common.model.bullet.entity.Bullet1;

@NoArgsConstructor
public class BulletFactory {
    public static Bullet makeBullet(int bulletType, String playerId, Integer x, Integer y, Double angle){
        switch (bulletType){
            case GameConfig.BulletType.Classic:
                return new Bullet1(playerId,x,y,angle);
            default:
                return new Bullet1(playerId,x,y,angle);
        }
    }
    public static Bullet makeBullet(int bulletType){
        switch (bulletType){
            case GameConfig.BulletType.Classic:
                return new Bullet1("1",0,0,0.0);
            default:
                return new Bullet1("1",0,0,0.0);
        }
    }
}
