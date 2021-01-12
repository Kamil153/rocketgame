package dev.kamilpolak.rocketgame.ecs;


public abstract class EntitySystem {
    private final int priority;


    public EntitySystem(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    abstract void update(float deltaTime);

}
