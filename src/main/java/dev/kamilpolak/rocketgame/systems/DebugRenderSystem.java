package dev.kamilpolak.rocketgame.systems;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import dev.kamilpolak.rocketgame.ecs.EntitySystem;

public class DebugRenderSystem extends EntitySystem {
    private final OrthographicCamera camera;
    private final World world;
    private final Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();

    public DebugRenderSystem(int priority, OrthographicCamera camera, World world) {
        super(priority);
        this.camera = camera;
        this.world = world;
    }

    @Override
    protected void preUpdate(float deltaTime) {

    }

    @Override
    protected void update(float deltaTime) {
        debugRenderer.render(world, camera.combined);
    }
}
