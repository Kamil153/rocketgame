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
}
