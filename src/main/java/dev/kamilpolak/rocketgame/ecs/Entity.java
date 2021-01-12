package dev.kamilpolak.rocketgame.ecs;

import java.util.HashMap;

public class Entity {
    private final HashMap<Class<?>, Component> components = new HashMap<>();

    public Entity() { }

    public void addComponent(Component component) {
        components.put(component.getClass(), component);
    }

    public void removeComponent(Class<?> componentClass) {
        components.remove(componentClass);
    }

    public void removeComponent(Component component) {
        removeComponent(component.getClass());
    }
}
