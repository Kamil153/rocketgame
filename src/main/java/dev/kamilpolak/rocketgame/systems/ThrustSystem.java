package dev.kamilpolak.rocketgame.systems;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import dev.kamilpolak.rocketgame.Util;
import dev.kamilpolak.rocketgame.components.*;
import dev.kamilpolak.rocketgame.ecs.Entity;
import dev.kamilpolak.rocketgame.ecs.Query;

import java.util.Random;

public class ThrustSystem extends IteratingSystem {
    private static final float RANDOM_ANGLE_STD = 0.2f;
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
            float angle = (float)Math.PI/2.0f + body.getAngle();
            if(entity.hasComponent(EngineAngleComponent.class)) {
                angle += entity.getComponent(EngineAngleComponent.class).angle;
            }
            if(!entity.hasComponent(FinsComponent.class) && entity.hasComponent(ThrustNoiseComponent.class)) {
                angle += (float)random.nextGaussian() * RANDOM_ANGLE_STD;
            }
            float xThrust = thrustComponent.thrust*(float)Math.cos(angle)*1000;
            float yThrust = thrustComponent.thrust*(float)Math.sin(angle)*1000;
            Vector2 force = new Vector2(xThrust, yThrust);
            Vector2 point = Util.calculateRelativePosition(body.getPosition(), body.getAngle(), thrustComponent.offset);
            body.applyForce(force, point, true);
        }
    }
}
