package dev.kamilpolak.rocketgame.ecs;

import java.util.Comparator;

public abstract class EntitySystem implements Comparable<EntitySystem> {
    private final int priority;
    private boolean enabled = true;
    private Engine engine = null;

    public static final Comparator<EntitySystem> PRIORITY_COMPARATOR = Comparator
            .comparing(EntitySystem::getPriority)
            .reversed();

    public EntitySystem(int priority) {
        this.priority = priority;
    }

    public EntitySystem() {
        this(0);
    }

    public int getPriority() {
        return priority;
    }

    public void enable() {
        enabled = true;
    }

    public void disable() {
        enabled = false;
    }

    abstract void update(float deltaTime);

    public boolean isEnabled() {
        return enabled;
    }

    final void addToEngine(Engine engine) {
        if(this.engine != null) {
            throw new IllegalArgumentException("System has already been added to an engine");
        }
        this.engine = engine;
    }

    final void removeFromEngine() {
        if(this.engine == null) {
            throw new IllegalArgumentException("System has not been added to an engine");
        }
        this.engine = null;
    }

    public Engine getCurrentEngine() {
        return engine;
    }

    @Override
    public int compareTo(EntitySystem entitySystem) {
        return PRIORITY_COMPARATOR.compare(this, entitySystem);
    }
}
