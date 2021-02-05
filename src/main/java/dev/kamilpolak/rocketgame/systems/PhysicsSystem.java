package dev.kamilpolak.rocketgame.systems;

import dev.kamilpolak.rocketgame.components.BodyComponent;
import dev.kamilpolak.rocketgame.components.TransformComponent;
import dev.kamilpolak.rocketgame.ecs.Entity;
import dev.kamilpolak.rocketgame.ecs.Query;

public class PhysicsSystem extends IteratingSystem {
    private static final Query query = (new Query())
            .include(BodyComponent.class)
            .include(TransformComponent.class);

    public PhysicsSystem(int priority, Query query) {
        super(priority, query);
    }

    @Override
    protected void updateEntity(float deltaTime, Entity entity) {

    }
}
