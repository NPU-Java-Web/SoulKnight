package org.example.server.service;

import lombok.extern.slf4j.Slf4j;
import org.example.common.model.animation.Animation;
import org.example.common.model.player.Player;
import org.example.server.dao.AnimationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class AnimationService {

    @Autowired
    AnimationDAO animationDAO;

    public synchronized List<Animation> list() {
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
}