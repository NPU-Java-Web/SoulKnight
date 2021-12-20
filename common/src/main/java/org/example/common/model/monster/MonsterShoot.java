package org.example.common.model.monster;

import org.example.common.model.bullet.Bullet;
import org.example.common.model.monster.entity.*;

import java.util.List;
import java.util.Map;


public class MonsterShoot {

    public static List<Bullet> shoot(Monster monster){
        if(monster instanceof Monster1){
            return ((Monster1)monster).shoot();
        } else if (monster instanceof Monster2){
            return ((Monster2)monster).shoot();
        } else if (monster instanceof Monster3){
            return ((Monster3)monster).shoot();
        } else if (monster instanceof Monster4){
            return ((Monster4)monster).shoot();
        } else if (monster instanceof Monster5){
            return ((Monster5)monster).shoot();
        } else if (monster instanceof Monster6){
            return ((Monster6)monster).shoot();
        } else if (monster instanceof Monster7){
            return ((Monster7)monster).shoot();
        } else if (monster instanceof Monster8){
            return ((Monster8)monster).shoot();
        }
        return null;
        /*try {
            for(int i=1; i<=8; i++){
                Class<?> c = Class.forName("org.example.common.model.monster.entity.Monster" + i);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;*/
    }

}
