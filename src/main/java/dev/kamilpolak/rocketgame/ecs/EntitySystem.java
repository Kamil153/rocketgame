package dev.kamilpolak.rocketgame.ecs;

import java.util.Comparator;

public abstract class EntitySystem implements Comparable<EntitySystem> {
    private final int priority;

    public static final Comparator<EntitySystem> PRIORITY_COMPARATOR = Comparator
            .comparing(EntitySystem::getPriority)
            .reversed();

    public EntitySystem(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    abstract void update(float deltaTime);

    @Override
    public int compareTo(EntitySystem entitySystem) {
        return PRIORITY_COMPARATOR.compare(this, entitySystem);
    }
}
