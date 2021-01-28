package dev.kamilpolak.rocketgame.ecs;

import java.util.*;

public class Engine implements IEntityListener {

    private final Set<Entity> entities = new HashSet<>();
    private final Set<EntitySystem> systems = new TreeSet<>();
    private final Set<Entity> addedEntities = new HashSet<>();
    private final Set<Entity> removedEntities = new HashSet<>();
    private final Set<Entity> mutatedEntities = new HashSet<>();
    private final Collection<IChangeListener> entityChangeListeners = new ArrayList<>();
    // TODO: fix entities not knowing about changes before update

    public void addEntity(Entity entity) {
        entities.add(entity);
        addedEntities.add(entity);
        entity.addListener(this);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
        addedEntities.remove(entity);
        mutatedEntities.remove(entity);
        removedEntities.add(entity);
        entity.removeListener(this);
    }

    @Override
    public void componentAdded(Class<? extends IComponent> componentClass, Entity entity) {
        mutatedEntities.add(entity);
    }

    @Override
    public void componentRemoved(Class<? extends IComponent> componentClass, Entity entity) {
        mutatedEntities.add(entity);
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
                system.update(deltaTime);
            }
        });
        addedEntities.clear();
        removedEntities.clear();
        mutatedEntities.clear();
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

    public Set<Entity> getAddedEntities() {
        return Collections.unmodifiableSet(addedEntities);
    }

    public Set<Entity> getRemovedEntities() {
        return Collections.unmodifiableSet(removedEntities);
    }

    public Set<Entity> getMutatedEntities() {
        return Collections.unmodifiableSet(mutatedEntities);
    }
}
