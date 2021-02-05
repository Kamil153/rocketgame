package dev.kamilpolak.rocketgame.systems;

import dev.kamilpolak.rocketgame.ecs.Entity;
import dev.kamilpolak.rocketgame.ecs.EntitySystem;
import dev.kamilpolak.rocketgame.ecs.IChangeListener;
import dev.kamilpolak.rocketgame.ecs.Query;

import java.util.HashSet;
import java.util.Set;

abstract public class IteratingSystem extends EntitySystem implements IChangeListener {
    Query query;
    Set<Entity> entities = new HashSet<>();

    public IteratingSystem(int priority, Query query) {
        super(priority);
        this.query = query;
    }

    @Override
    public void entityAdded(Entity entity) {
        if(query.check(entity)) {
            entities.add(entity);
        }
    }

    @Override
    public void entityRemoved(Entity entity) {
        entities.remove(entity);
    }

    @Override
    public void entityMutated(Entity entity) {
        if(query.check(entity)) {
            entities.add(entity);
        }
        else {
            entities.remove(entity);
        }
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
    public void preUpdate(float deltaTime) {
        for(Entity entity: entities) {
            updateEntity(deltaTime, entity);
        }
    }

    protected void update(float deltaTime) {

    }

    abstract protected void updateEntity(float deltaTime, Entity entity);
}
