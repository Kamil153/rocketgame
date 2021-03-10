package dev.kamilpolak.rocketgame.systems;

import dev.kamilpolak.rocketgame.components.EngineStateComponent;
import dev.kamilpolak.rocketgame.components.FuelComponent;
import dev.kamilpolak.rocketgame.ecs.Entity;
import dev.kamilpolak.rocketgame.ecs.Query;

public class FuelSystem extends IteratingSystem {
    private static final Query query = (new Query())
            .include(FuelComponent.class)
            .include(EngineStateComponent.class);
    private static final float FUEL_PER_SECOND = 1;

    public FuelSystem(int priority) {
        super(priority, query);
    }

    @Override
    protected void updateEntity(float deltaTime, Entity entity) {
        FuelComponent fuelComponent = entity.getComponent(FuelComponent.class);
        EngineStateComponent engineState = entity.getComponent(EngineStateComponent.class);
        if(engineState.running) {
            fuelComponent.fuel = Math.max(0.0f, fuelComponent.fuel - deltaTime * fuelComponent.fuelConsumption);
            if(fuelComponent.fuel == 0.0f) {
                entity.getComponent(EngineStateComponent.class).running = false;
            }
        }
    }
}
