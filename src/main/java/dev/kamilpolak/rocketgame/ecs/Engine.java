package dev.kamilpolak.rocketgame.ecs;

import java.util.*;

public class Engine implements EntityListener {

    private final Set<Entity> entities = new HashSet<>();
    private final Set<EntitySystem> systems = new TreeSet<>();
    private final Set<Entity> addedEntities = new HashSet<>();
    private final Set<Entity> removedEntities = new HashSet<>();
    private final Set<Entity> mutatedEntities = new HashSet<>();

    public void addEntity(Entity entity) {
        entities.add(entity);
        for(Component component: entity.getComponents()) {
            addedEntities.add(entity);
            entity.addListener(this);
        }
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
        for(Component component: entity.getComponents()) {
            removedEntities.add(entity);
            entity.removeListener(this);
        }
    }

    @Override
    public void componentAdded(Class<? extends Component> componentClass, Entity entity) {
        mutatedEntities.add(entity);
    }

    @Override
    public void componentRemoved(Class<? extends Component> componentClass, Entity entity) {
        removedEntities.add(entity);
    }

    public void addSystem(EntitySystem system) {
        systems.add(system);
    }

    public void removeSystem(EntitySystem system) {
        systems.remove(system);
    }

    public void update(float deltaTime) {
        systems.forEach(system -> system.runUpdate(deltaTime));
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
