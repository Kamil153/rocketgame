package dev.kamilpolak.rocketgame.systems;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import dev.kamilpolak.rocketgame.components.RocketComponent;
import dev.kamilpolak.rocketgame.components.TransformComponent;
import dev.kamilpolak.rocketgame.ecs.Entity;
import dev.kamilpolak.rocketgame.ecs.Query;

public class CameraSystem extends IteratingSystem {
    private static final Query query = (new Query())
            .include(RocketComponent.class)
            .include(TransformComponent.class);

    private final OrthographicCamera camera;
    private final float lowerBound;

    public CameraSystem(int priority, OrthographicCamera camera, float lowerBound) {
        super(priority, query);
        this.camera = camera;
        this.lowerBound = lowerBound;
    }

    @Override
    protected void updateEntity(float deltaTime, Entity entity) {
        Vector3 position = entity.getComponent(TransformComponent.class).position;
        float x = position.x;
        float y = Math.max(position.y, lowerBound);
        camera.position.set(x, y, camera.position.z);
    }
}
