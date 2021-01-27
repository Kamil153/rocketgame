package dev.kamilpolak.rocketgame.ecs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Entity {
    private final HashMap<Class<? extends Component>, Component> components = new HashMap<>();
    private final Collection<EntityListener> listeners = new ArrayList<>();

    public Entity() { }

    public <T extends Component> T getComponent(Class<T> componentClass) {
        return componentClass.cast(components.get(componentClass));
    }

    public void addComponent(Component component) {
        components.put(component.getClass(), component);
        listeners.forEach(listener -> listener.componentAdded(component.getClass(), this));
    }

    public void removeComponent(Class<? extends Component> componentClass) {
        components.remove(componentClass);
        listeners.forEach(listener -> listener.componentRemoved(componentClass, this));
    }

    public void removeComponent(Component component) {
        removeComponent(component.getClass());
    }

    public void addListener(EntityListener listener) {
        listeners.add(listener);
    }

    public void removeListener(EntityListener listener) {
        listeners.remove(listener);
    }

    public boolean hasComponent(Class<? extends Component> componentClass) {
        return components.containsKey(componentClass);
    }

    public Collection<Component> getComponents() {
        return components.values();
    }
}
