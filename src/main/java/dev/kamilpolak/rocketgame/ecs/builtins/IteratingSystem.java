package dev.kamilpolak.rocketgame.ecs.builtins;

import dev.kamilpolak.rocketgame.ecs.Entity;
import dev.kamilpolak.rocketgame.ecs.EntitySystem;
import dev.kamilpolak.rocketgame.ecs.Query;

import java.util.HashSet;
import java.util.Set;

abstract public class IteratingSystem extends EntitySystem {
    Query query;
    Set<Entity> entities = new HashSet<>();

    public IteratingSystem(int priority, Query query) {
        super(priority);
        this.query = query;
    }

    @Override
    protected void addedToEngine() {
        super.addedToEngine();
        entities.addAll(getCurrentEngine().queryEntities(query));
    }

    @Override
    protected void removedFromEngine() {
        super.removedFromEngine();
        entities.clear();
    }
}
