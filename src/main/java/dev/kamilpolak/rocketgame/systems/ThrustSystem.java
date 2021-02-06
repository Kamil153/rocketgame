package dev.kamilpolak.rocketgame.systems;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import dev.kamilpolak.rocketgame.components.BodyComponent;
import dev.kamilpolak.rocketgame.components.EngineStateComponent;
import dev.kamilpolak.rocketgame.components.FinsComponent;
import dev.kamilpolak.rocketgame.components.ThrustComponent;
import dev.kamilpolak.rocketgame.ecs.Entity;
import dev.kamilpolak.rocketgame.ecs.Query;

import java.util.Random;

public class ThrustSystem extends IteratingSystem {
    private static final Random random = new Random();
    private static final Query query = (new Query())
            .include(EngineStateComponent.class)
            .include(BodyComponent.class)
            .include(ThrustComponent.class);

    public ThrustSystem(int priority) {
        super(priority, query);
    }

    @Override
    protected void updateEntity(float deltaTime, Entity entity) {
        boolean running = entity.getComponent(EngineStateComponent.class).running;
        if(running) {
            Body body = entity.getComponent(BodyComponent.class).body;
            ThrustComponent thrustComponent = entity.getComponent(ThrustComponent.class);
            float yThrust = thrustComponent.thrust;
            float xThrust = (float)random.nextGaussian()*100;
            if(!entity.hasComponent(FinsComponent.class)) {
                xThrust *= 10;
            }
            Vector2 force = new Vector2(xThrust, yThrust);
            Vector2 point = body.getPosition().cpy().add(thrustComponent.offset);
            body.applyForce(force, point, true);
        }
    }
}
