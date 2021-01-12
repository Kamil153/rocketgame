package dev.kamilpolak.rocketgame.ecs;

import java.util.HashMap;

public class Entity {
    private final HashMap<Class<?>, Component> components = new HashMap<>();

    public Entity() { }

    public <T extends Component> T getComponent(Class<T> componentClass) {
        return componentClass.cast(components.get(componentClass));
    }

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
