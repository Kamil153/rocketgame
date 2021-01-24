package dev.kamilpolak.rocketgame.ecs;

import java.util.*;

public class Engine {
    private final Set<Entity> entities = new HashSet<>();
    private final Map<Class<? extends Component>, Set<Entity>> componentEntityMap = new HashMap<>();
    private final Set<EntitySystem> systems = new TreeSet<>();
    private final Map<Query, Set<Entity>> queriedEntities = new HashMap<>();
    private final Map<Class<? extends Component>, Set<Query>> queryByComponent = new HashMap<>();

    public void addEntity(Entity entity) {
        entities.add(entity);
        for(Component component: entity.getComponents()) {
            componentEntityMap.putIfAbsent(component.getClass(), new HashSet<>());
            componentEntityMap.get(component.getClass()).add(entity);
        }
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
        for(Component component: entity.getComponents()) {
            componentEntityMap.get(component.getClass()).remove(entity);
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

    public Set<Entity> activateQuery(Query query) {
        if(!queriedEntities.containsKey(query)) {
            for(var component: query.getRelevantComponents()) {
                queryByComponent.putIfAbsent(component, new HashSet<>());
                queryByComponent.get(component).add(query);
            }
            Set<Entity> initialEntities = new HashSet<>();
            for(Entity entity: entities) {
                if(query.check(entity)) {
                    initialEntities.add(entity);
                }
            }
            queriedEntities.put(query, initialEntities);
            return Collections.unmodifiableSet(initialEntities);
        }
        return queriedEntities.get(query);
    }
}
