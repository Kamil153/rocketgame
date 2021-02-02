package dev.kamilpolak.rocketgame.ecs;

public interface IEntitySystem extends Comparable<IEntitySystem> {
    int getPriority();
    void enable();
    void disable();
    boolean isEnabled();
    void update(float deltaTime);
    void addToEngine(Engine engine);
    void removeFromEngine();
    void addedToEngine();
    void removedFromEngine();
    Engine getCurrentEngine();
}
