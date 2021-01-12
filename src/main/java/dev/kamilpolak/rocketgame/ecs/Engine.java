package dev.kamilpolak.rocketgame.ecs;

import java.util.*;

public class Engine {
    private final Set<Entity> entities = new HashSet<>();
    private final Map<Class<? extends Component>, Set<Entity>> componentEntityMap = new HashMap<>();

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
}
