package dev.kamilpolak.rocketgame.ecs;

import java.util.*;

public class Engine {

    private final Set<Entity> entities = new HashSet<>();
    private final Map<Class<? extends Component>, Set<Entity>> componentEntityMap = new HashMap<>();
    private final Set<EntitySystem> systems = new TreeSet<>();
    private final Map<Query, Set<Entity>> queriedEntities = new HashMap<>();
    private final Map<Class<? extends Component>, Set<Query>> queryByComponent = new HashMap<>();
    private final Set<Entity> addedEntities = new HashSet<>();
    private final Set<Entity> removedEntities = new HashSet<>();
    private final Set<Entity> mutatedEntities = new HashSet<>();

    public void addEntity(Entity entity) {
        entities.add(entity);
        for(Component component: entity.getComponents()) {
            componentEntityMap.putIfAbsent(component.getClass(), new HashSet<>());
            componentEntityMap.get(component.getClass()).add(entity);
            addedEntities.add(entity);
        }
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
        for(Component component: entity.getComponents()) {
            componentEntityMap.get(component.getClass()).remove(entity);
            removedEntities.add(entity);
        }
    }

    public void addSystem(EntitySystem system) {
        systems.add(system);
    }

    public void removeSystem(EntitySystem system) {
        systems.remove(system);
    }

    public void update(float deltaTime) {
        systems.forEach(system -> system.runUpdate(deltaTime));
    }

    public Set<Entity> getEntities() {
        return Collections.unmodifiableSet(entities);
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
