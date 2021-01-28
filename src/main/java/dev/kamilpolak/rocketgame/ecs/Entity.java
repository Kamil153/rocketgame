package dev.kamilpolak.rocketgame.ecs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Entity {
    private final HashMap<Class<? extends IComponent>, IComponent> components = new HashMap<>();
    private final Collection<IEntityListener> listeners = new ArrayList<>();

    public Entity() { }

    public <T extends IComponent> T getComponent(Class<T> componentClass) {
        return componentClass.cast(components.get(componentClass));
    }

    public void addComponent(IComponent component) {
        components.put(component.getClass(), component);
        listeners.forEach(listener -> listener.componentAdded(component.getClass(), this));
    }

    public void removeComponent(Class<? extends IComponent> componentClass) {
        components.remove(componentClass);
        listeners.forEach(listener -> listener.componentRemoved(componentClass, this));
    }

    public void removeComponent(IComponent component) {
        removeComponent(component.getClass());
    }

    public void addListener(IEntityListener listener) {
        listeners.add(listener);
    }

    public void removeListener(IEntityListener listener) {
        listeners.remove(listener);
    }

    public boolean hasComponent(Class<? extends IComponent> componentClass) {
        return components.containsKey(componentClass);
    }

    public Collection<IComponent> getComponents() {
        return components.values();
    }
}
