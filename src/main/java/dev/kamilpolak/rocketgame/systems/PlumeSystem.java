package dev.kamilpolak.rocketgame.systems;

import dev.kamilpolak.rocketgame.components.BindComponent;
import dev.kamilpolak.rocketgame.components.EngineStateComponent;
import dev.kamilpolak.rocketgame.components.PlumeComponent;
import dev.kamilpolak.rocketgame.components.TransformComponent;
import dev.kamilpolak.rocketgame.ecs.Entity;
import dev.kamilpolak.rocketgame.ecs.IteratingSystem;
import dev.kamilpolak.rocketgame.ecs.Query;

public class PlumeSystem extends IteratingSystem {
    private static final Query query = (new Query())
            .include(PlumeComponent.class)
            .include(BindComponent.class)
            .include(TransformComponent.class);

    public PlumeSystem(int priority) {
        super(priority, query);
    }

    @Override
    protected void updateEntity(float deltaTime, Entity entity) {
        Entity rocket = entity.getComponent(BindComponent.class).target;
        EngineStateComponent engine = rocket.getComponent(EngineStateComponent.class);
        TransformComponent plumeTransform = entity.getComponent(TransformComponent.class);
        plumeTransform.hidden = !engine.running;
    }
}
