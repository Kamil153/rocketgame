package dev.kamilpolak.rocketgame.ecs;

import java.util.*;

public class Engine {
    private final Set<Entity> entities = new HashSet<>();
    private final Map<Class<? extends Component>, Set<Entity>> componentEntityMap = new HashMap<>();
    private final Set<EntitySystem> systems = new TreeSet<>();

    public void addEntity(Entity entity) {
        entities.add(entity);
        for(Component component: entity.getComponents()) {
            Objects.requireNonNull(componentEntityMap.putIfAbsent(component.getClass(), new HashSet<>())).add(entity);
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
        return new HashSet<>(entities);
    }

    public Set<Entity> getEntities(IncludeConstraint constraint) {
        Set<Entity> result = new HashSet<>();
        for(Entity entity: entities) {
            if(constraint.check(entity)) {
                result.add(entity);
            }
        }
        return result;
    }
}
