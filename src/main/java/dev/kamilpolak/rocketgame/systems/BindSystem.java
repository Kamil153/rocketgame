package dev.kamilpolak.rocketgame.systems;

import dev.kamilpolak.rocketgame.components.BindComponent;
import dev.kamilpolak.rocketgame.components.TransformComponent;
import dev.kamilpolak.rocketgame.ecs.Entity;
import dev.kamilpolak.rocketgame.ecs.Query;

public class BindSystem extends IteratingSystem {
    private static final Query query = (new Query())
            .include(BindComponent.class)
            .include(TransformComponent.class);

    public BindSystem(int priority) {
        super(priority, query);
    }

    @Override
    protected void updateEntity(float deltaTime, Entity entity) {

    }
}
