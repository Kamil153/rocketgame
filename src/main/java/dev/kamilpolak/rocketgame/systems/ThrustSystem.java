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
    private static final float RANDOM_THRUST_STD = 0.2f;
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
            if(!entity.hasComponent(FinsComponent.class)) {
                angle += (float)random.nextGaussian()*RANDOM_THRUST_STD;
            }
            float xThrust = thrustComponent.thrust*(float)Math.cos(angle);
            float yThrust = thrustComponent.thrust*(float)Math.sin(angle);
            Vector2 force = new Vector2(xThrust, yThrust);
            Vector2 point = calculateThrustPoint(body.getPosition(), body.getAngle(), thrustComponent.offset);
            body.applyForce(force, point, true);
        }
    }

    private static Vector2 calculateThrustPoint(Vector2 position, float rotation, Vector2 offset) {
        float radius = offset.len();
        float angle = offset.angleRad();
        Vector2 result = new Vector2();
        result.x = position.x + radius*(float)Math.cos(rotation + angle);
        result.y = position.y + radius*(float)Math.sin(rotation + angle);
        return result;
    }
}
