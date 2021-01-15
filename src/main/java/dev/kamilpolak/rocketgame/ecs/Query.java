package dev.kamilpolak.rocketgame.ecs;

import java.util.*;

public class Query {
    private final Set<Class<? extends Component>> included;
    private final Set<Class<? extends Component>> excluded;

    Query() {
        this(Collections.emptyList(), Collections.emptyList());
    }

    Query(Collection<Class<? extends Component>> included) {
        this(included, Collections.emptyList());
    }

    Query(Collection<Class<? extends Component>> included, Collection<Class<? extends Component>> excluded) {
        this.included = new HashSet<>(included);
        this.excluded = new HashSet<>(included);
    }

    public Query include(Class<? extends Component> componentClass) {
        included.add(componentClass);
        return this;
    }

    public Iterator<Class<? extends Component>> getIncludedComponents() {
        return Collections.unmodifiableSet(included).iterator();
    }

    public Iterator<Class<? extends Component>> getExcludedComponents() {
        return Collections.unmodifiableSet(excluded).iterator();
    }
}
