package dev.kamilpolak.rocketgame.ecs;

public interface IChangeListener {
    void entityAdded(Entity entity);
    void entityRemoved(Entity entity);
    void entityMutated(Entity entity);
}
