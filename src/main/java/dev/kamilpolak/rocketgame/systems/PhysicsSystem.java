package dev.kamilpolak.rocketgame.systems;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import dev.kamilpolak.rocketgame.components.BodyComponent;
import dev.kamilpolak.rocketgame.components.TransformComponent;
import dev.kamilpolak.rocketgame.ecs.Entity;
import dev.kamilpolak.rocketgame.ecs.Query;

import java.util.ArrayList;
import java.util.Collection;

public class PhysicsSystem extends IteratingSystem {
    private static final Query query = (new Query())
            .include(BodyComponent.class)
            .include(TransformComponent.class);
    private static final float TIME_STEP = 1/45f;

    private final Collection<Entity> entities = new ArrayList<>();

    private final World world;
    private float accumulator = 0.0f;

    public PhysicsSystem(int priority, World world) {
        super(priority, query);
        this.world = world;
    }

    @Override
    protected void update(float deltaTime) {
        super.update(deltaTime);
        float frameTime = Math.min(deltaTime, 0.25f);
        accumulator += frameTime;
        while(accumulator >= TIME_STEP) {
            world.step(TIME_STEP, 6, 2);
            accumulator -= TIME_STEP;
        }
        for(Entity entity: entities) {
            Body body = entity.getComponent(BodyComponent.class).body;
            TransformComponent transform = entity.getComponent(TransformComponent.class);
            Vector2 position = body.getPosition();
            transform.position.x = body.getPosition().x;
            transform.position.y = body.getPosition().y;
            transform.rotation = body.getAngle();
        }
    }

    @Override
    protected void updateEntity(float deltaTime, Entity entity) {
        entities.add(entity);
    }
}
