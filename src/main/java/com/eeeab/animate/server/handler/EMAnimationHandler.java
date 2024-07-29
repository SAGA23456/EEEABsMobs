package com.eeeab.animate.server.handler;

import com.eeeab.eeeabsmobs.EEEABMobs;
import com.eeeab.animate.server.animation.EMAnimatedEntity;
import com.eeeab.animate.server.animation.Animation;
import com.eeeab.animate.server.event.AnimationEvent;
import com.eeeab.animate.server.message.AnimationMessage;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.network.PacketDistributor;
import org.apache.commons.lang3.ArrayUtils;

public enum EMAnimationHandler {
    INSTANCE;

    public <T extends Entity & EMAnimatedEntity> void sendEMAnimationMessage(T entity, Animation animation) {
        if (!entity.level().isClientSide) {
            if (animation == null) {
                throw new IllegalArgumentException("animation is null");
            }
            entity.getAnimation().stop();
            entity.setAnimation(animation);
            entity.setAnimationTick(0);
            animation.start(entity.tickCount);
            EEEABMobs.NETWORK.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity), new AnimationMessage(entity.getId(), ArrayUtils.indexOf(entity.getAnimations(), animation)));
        }
    }

    public <T extends Entity & EMAnimatedEntity> void updateAnimations(T entity) {
        Animation animation = entity.getAnimation();
        if (animation == null) {
            entity.setAnimation(entity.getNoAnimation());
        } else {
            if (animation != entity.getNoAnimation()) {
                if (entity.getAnimationTick() == 0) {
                    AnimationEvent<T> event = new AnimationEvent.Start<>(entity, animation);
                    if (!MinecraftForge.EVENT_BUS.post(event)) {
                        this.sendEMAnimationMessage(entity, event.getAnimation());
                    }
                }
                if (entity.getAnimationTick() < animation.getDuration()) {
                    entity.setAnimationTick(entity.getAnimationTick() + 1);
                    MinecraftForge.EVENT_BUS.post(new AnimationEvent.Tick<>(entity, animation, entity.getAnimationTick()));
                }
                if (entity.getAnimationTick() == animation.getDuration()) {
                    animation.stop();
                    entity.setAnimation(entity.getNoAnimation());
                    entity.setAnimationTick(0);
                }
            }
        }
    }

}