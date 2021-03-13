package dev.kamilpolak.rocketgame.ecs;

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
        Engine engine = getCurrentEngine();
        entities.addAll(engine.queryEntities(query));
        engine.addEntityChangeListener(this);
    }

    @Override
    protected void removedFromEngine() {
        super.removedFromEngine();
        entities.clear();
        getCurrentEngine().removeEntityChangeListener(this);
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
