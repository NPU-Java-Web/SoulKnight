package org.example.server.service;

import lombok.extern.slf4j.Slf4j;
import org.example.common.model.animation.Animation;
import org.example.common.model.animation.entity.Explosion;
import org.example.server.dao.AnimationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 动画服务
 *
 * @author 廖菁璞
 */
@Slf4j
@Service
public class AnimationService {

    /**
     * 动画DAO
     */
    @Autowired
    AnimationDAO animationDAO;

    /**
     * 获取所有动画信息
     *
     * @return List
     */
    public List<Animation> list() {
        List<Animation> result = new ArrayList<>();
        Set<String> keys = animationDAO.getAllExplosionKeys();
        for (String key : keys) {
            Animation animation;
            try {
                animation = animationDAO.selectByKey(key);
                if (animation != null) {
                    result.add(animation);
                }
            } catch (NumberFormatException e) {
                log.error("在根据key获取animation对象时出现异常" + e.getCause());
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 让动画播放下一帧
     *
     * @param animations 想要更新的动画列表
     */
    public void play(List<Animation> animations) {
        for (Animation animation : animations) {
            animationDAO.increaseStateById(animation);
        }
    }

    /**
     * 保存新的动画
     *
     * @param explosion 爆炸动画（只有爆炸动画有多个阶段，需要保存）
     */
    public void save(Explosion explosion) {
        animationDAO.insert(explosion);
    }

}
