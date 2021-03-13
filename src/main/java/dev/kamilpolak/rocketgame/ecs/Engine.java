package dev.kamilpolak.rocketgame.ecs;

import java.util.*;

public class Engine implements IEntityListener {

    private final Set<Entity> entities = new HashSet<>();
    private final Set<EntitySystem> systems = new TreeSet<>();
    private final Collection<IChangeListener> entityChangeListeners = new ArrayList<>();

    public void addEntity(Entity entity) {
        entities.add(entity);
        entity.addListener(this);
        entityChangeListeners.forEach(listener -> listener.entityAdded(entity));
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
        entity.removeListener(this);
        entityChangeListeners.forEach(listener -> listener.entityRemoved(entity));
    }

    @Override
    public void componentAdded(Class<? extends IComponent> componentClass, Entity entity) {
        entityChangeListeners.forEach(listener -> listener.entityMutated(entity));
    }

    @Override
    public void componentRemoved(Class<? extends IComponent> componentClass, Entity entity) {
        entityChangeListeners.forEach(listener -> listener.entityMutated(entity));
    }

    public void addSystem(EntitySystem system) {
        systems.add(system);
        system.addToEngine(this);
    }

    public void removeSystem(EntitySystem system) {
        systems.remove(system);
        system.removeFromEngine();
    }

    public void update(float deltaTime) {
        systems.forEach(system -> {
            if(system.isEnabled()) {
                system.runUpdate(deltaTime);
            }
        });
    }

    public Set<Entity> getEntitiesView() {
        return Collections.unmodifiableSet(entities);
    }

    public Set<Entity> queryEntities(Query query) {
        Set<Entity> result = new HashSet<>();
        for(Entity entity: entities) {
            if(query.check(entity)) {
                result.add(entity);
            }
        }
        return result;
    }

    public void addEntityChangeListener(IChangeListener listener) {
        entityChangeListeners.add(listener);
    }

    public void removeEntityChangeListener(IChangeListener listener) {
        entityChangeListeners.remove(listener);
    }
}
