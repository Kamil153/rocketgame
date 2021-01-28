package dev.kamilpolak.rocketgame.ecs;

import java.util.EventListener;

public interface IEntityListener extends EventListener {
    void componentAdded(Class<? extends IComponent> componentClass, Entity entity);
    void componentRemoved(Class<? extends IComponent> componentClass, Entity entity);
}
