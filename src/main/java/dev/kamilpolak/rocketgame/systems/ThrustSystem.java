package dev.kamilpolak.rocketgame.systems;

import dev.kamilpolak.rocketgame.components.BodyComponent;
import dev.kamilpolak.rocketgame.components.EngineStateComponent;
import dev.kamilpolak.rocketgame.ecs.Entity;
import dev.kamilpolak.rocketgame.ecs.Query;

public class ThrustSystem extends IteratingSystem {
    private static final Query query = (new Query())
            .include(EngineStateComponent.class)
            .include(BodyComponent.class);

    public ThrustSystem(int priority) {
        super(priority, query);
    }

    @Override
    protected void updateEntity(float deltaTime, Entity entity) {

    }
}
