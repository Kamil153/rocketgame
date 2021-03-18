package dev.kamilpolak.rocketgame.systems;

import dev.kamilpolak.rocketgame.components.FlightTerminationComponent;
import dev.kamilpolak.rocketgame.components.RocketComponent;
import dev.kamilpolak.rocketgame.components.TransformComponent;
import dev.kamilpolak.rocketgame.ecs.Entity;
import dev.kamilpolak.rocketgame.ecs.IteratingSystem;
import dev.kamilpolak.rocketgame.ecs.Query;

public class FlightTerminationSystem extends IteratingSystem {
    private static final Query query = (new Query())
            .include(RocketComponent.class)
            .include(TransformComponent.class)
            .exclude(FlightTerminationComponent.class);

    public FlightTerminationSystem(int priority) {
        super(priority, query);
    }

    @Override
    protected void updateEntity(float deltaTime, Entity entity) {
        TransformComponent transformComponent = entity.getComponent(TransformComponent.class);
        float absoluteRotation = (float)Math.abs(transformComponent.rotation % (2*Math.PI));
        if(absoluteRotation > Math.PI) {
            absoluteRotation = (float) (2*Math.PI - absoluteRotation);
        }
        if(absoluteRotation > Math.PI/2) {
            entity.addComponent(new FlightTerminationComponent());
        }
    }
}
