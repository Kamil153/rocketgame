package dev.kamilpolak.rocketgame.ecs;

import java.util.EventListener;

public interface EntityListener extends EventListener {
    void componentAdded(Component component, Entity entity);
    void componentRemoved(Component component, Entity entity);
}
