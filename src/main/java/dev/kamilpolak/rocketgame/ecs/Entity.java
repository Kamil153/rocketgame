package dev.kamilpolak.rocketgame.ecs;

import java.util.Collection;
import java.util.HashMap;

public class Entity {
    private final HashMap<Class<? extends Component>, Component> components = new HashMap<>();

    public Entity() { }

    public <T extends Component> T getComponent(Class<T> componentClass) {
        return componentClass.cast(components.get(componentClass));
    }

    public void addComponent(Component component) {
        components.put(component.getClass(), component);
    }

    public void removeComponent(Class<? extends Component> componentClass) {
        components.remove(componentClass);
    }

    public void removeComponent(Component component) {
        removeComponent(component.getClass());
    }

    public boolean hasComponent(Class<? extends Component> componentClass) {
        return components.containsKey(componentClass);
    }

    public Collection<Component> getComponents() {
        return components.values();
    }
}
