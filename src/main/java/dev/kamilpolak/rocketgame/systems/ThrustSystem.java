package dev.kamilpolak.rocketgame.systems;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
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
        boolean running = entity.getComponent(EngineStateComponent.class).running;
        if(running) {
            Body body = entity.getComponent(BodyComponent.class).body;
            Vector2 force = new Vector2(0, 20);
            body.applyForceToCenter(force, true);
        }
    }
}
