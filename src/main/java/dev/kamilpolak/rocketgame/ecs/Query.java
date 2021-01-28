package dev.kamilpolak.rocketgame.ecs;

import java.util.*;

public class Query {
    private final Set<Class<? extends IComponent>> included;
    private final Set<Class<? extends IComponent>> excluded;

    Query() {
        this(Collections.emptyList(), Collections.emptyList());
    }

    Query(Collection<Class<? extends IComponent>> included) {
        this(included, Collections.emptyList());
    }

    Query(Collection<Class<? extends IComponent>> included, Collection<Class<? extends IComponent>> excluded) {
        this.included = new HashSet<>(included);
        this.excluded = new HashSet<>(excluded);
    }

    public Query include(Class<? extends IComponent> componentClass) {
        included.add(componentClass);
        return this;
    }

    public Query exclude(Class<? extends IComponent> componentClass) {
        excluded.add(componentClass);
        return this;
    }

    public Iterator<Class<? extends IComponent>> getIncludedComponents() {
        return Collections.unmodifiableSet(included).iterator();
    }

    public Iterator<Class<? extends IComponent>> getExcludedComponents() {
        return Collections.unmodifiableSet(excluded).iterator();
    }

    public boolean check(Entity entity) {
        for(var component: included) {
            if(!entity.hasComponent(component)) return false;
        }
        for(var component: excluded) {
            if(entity.hasComponent(component)) return false;
        }
        return true;
    }

    public Set<Class<? extends IComponent>> getRelevantComponents() {
        var components = new HashSet<>(included);
        components.addAll(excluded);
        return components;
    }
}
