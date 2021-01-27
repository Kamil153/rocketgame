package dev.kamilpolak.rocketgame.ecs.builtins;

import dev.kamilpolak.rocketgame.ecs.EntitySystem;
import dev.kamilpolak.rocketgame.ecs.Query;

abstract public class IteratingSystem extends EntitySystem {
    Query query;

    public IteratingSystem(int priority, Query query) {
        super(priority);
        this.query = query;
    }
}
