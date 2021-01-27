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

    @Override
    final public void update(float deltaTime) {
        for(Entity entity: getCurrentEngine().getAddedEntities()) {
            if(query.check(entity)) {
                entities.add(entity);
            }
        }
        for(Entity entity: getCurrentEngine().getRemovedEntities()) {
            entities.remove(entity);
        }
        for(Entity entity: getCurrentEngine().getMutatedEntities()) {
            if(query.check(entity)) {
                entities.add(entity);
            }
            else {
                entities.remove(entity);
            }
        }
        for(Entity entity: entities) {
            updateEntity(deltaTime, entity);
        }
    }

    abstract protected void updateEntity(float deltaTime, Entity entity);
}
