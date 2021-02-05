package dev.kamilpolak.rocketgame.systems;

import dev.kamilpolak.rocketgame.components.EngineStateComponent;
import dev.kamilpolak.rocketgame.components.FuelComponent;
import dev.kamilpolak.rocketgame.ecs.Entity;
import dev.kamilpolak.rocketgame.ecs.Query;

public class FuelSystem extends IteratingSystem {
    private static final Query query = (new Query())
            .include(FuelComponent.class);

    public FuelSystem(int priority) {
        super(priority, query);
    }

    @Override
    protected void updateEntity(float deltaTime, Entity entity) {
        FuelComponent fuel = entity.getComponent(FuelComponent.class);
        if(fuel.fuel == 0.0f && entity.hasComponent(EngineStateComponent.class)) {
            entity.getComponent(EngineStateComponent.class).running = false;
        }
    }
}
