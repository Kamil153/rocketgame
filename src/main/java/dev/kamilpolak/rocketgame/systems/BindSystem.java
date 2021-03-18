package dev.kamilpolak.rocketgame.systems;

import com.badlogic.gdx.math.Vector2;
import dev.kamilpolak.rocketgame.Util;
import dev.kamilpolak.rocketgame.components.BindComponent;
import dev.kamilpolak.rocketgame.components.TransformComponent;
import dev.kamilpolak.rocketgame.ecs.Entity;
import dev.kamilpolak.rocketgame.ecs.IteratingSystem;
import dev.kamilpolak.rocketgame.ecs.Query;

public class BindSystem extends IteratingSystem {
    private static final Query query = (new Query())
            .include(BindComponent.class)
            .include(TransformComponent.class);

    public BindSystem(int priority) {
        super(priority, query);
    }

    @Override
    protected void updateEntity(float deltaTime, Entity entity) {
        TransformComponent transform = entity.getComponent(TransformComponent.class);
        BindComponent binding = entity.getComponent(BindComponent.class);
        TransformComponent targetTransform = binding.target.getComponent(TransformComponent.class);
        float targetRotation = targetTransform.rotation;
        transform.rotation = targetRotation;
        transform.position.set(
                Util.calculateRelativePosition(
                        new Vector2(targetTransform.position.x, targetTransform.position.y),
                        targetRotation,
                        binding.offset),
                transform.position.z);
    }
}
