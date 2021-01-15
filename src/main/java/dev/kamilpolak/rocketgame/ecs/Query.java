package dev.kamilpolak.rocketgame.ecs;

import java.util.*;

public class Query {
    private Set<Class<? extends Component>> include;
    private Set<Class<? extends Component>> exclude;

    Query() {
        this(Collections.emptyList(), Collections.emptyList());
    }

    Query(Collection<Class<? extends Component>> include) {
        this(include, Collections.emptyList());
    }

    Query(Collection<Class<? extends Component>> include, Collection<Class<? extends Component>> exclude) {
        this.include = new HashSet<>(include);
        this.exclude = new HashSet<>(include);
    }

    public Iterator<Class<? extends Component>> getIncludedComponents() {
        return Collections.unmodifiableSet(include).iterator();
    }

    public Iterator<Class<? extends Component>> getExcludedComponents() {
        return Collections.unmodifiableSet(exclude).iterator();
    }
}
