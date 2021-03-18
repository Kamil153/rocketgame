package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import dev.kamilpolak.rocketgame.ecs.Engine;
import dev.kamilpolak.rocketgame.ecs.Entity;
import dev.kamilpolak.rocketgame.systems.*;

public class GameWorld extends Engine {
    private final World world;
    private final BodyFactory bodyFactory;
    private final EntityFactory entityFactory;
    private final Entity rocket;

    public GameWorld(AssetManager assets, OrthographicCamera camera) {
        world = new World(new Vector2(0, -10), true);
        bodyFactory = new BodyFactory(world);
        entityFactory = new EntityFactory(world, assets);
        rocket = entityFactory.createRocket();
        addEntity(rocket);
        addEntity(entityFactory.createEarth());
        addEntity(entityFactory.createRocketPlume(rocket));
        addEntity(entityFactory.createLaunchpad());
        addEntity(entityFactory.createMountains());final int GROUND_COUNT = 16;
        final int TREES_COUNT = 5;
        float groundWidth = assets.get(Asset.EARTH_TEXTURE.getPath(), Texture.class).getWidth();
        addEntity(entityFactory.createEarth());
        float groundX = 0;
        for(int i = 1; i < GROUND_COUNT; i++) {
            groundX = -groundX;
            if(i % 2 == 1) {
                groundX += groundWidth;
            }
            addEntity(entityFactory.createEarth(groundX));
        }
        float treesWidth = assets.get(Asset.TREES_TEXTURE.getPath(), Texture.class).getWidth();
        addEntity(entityFactory.createTrees());
        float treesX = 0;
        for(int i = 1; i < TREES_COUNT; i++) {
            treesX = -treesX;
            if(i % 2 == 1) {
                treesX += treesWidth;
            }
            addEntity(entityFactory.createTrees(treesX));
        }

        addSystem(new FuelSystem(18));
        addSystem(new RocketTurnSystem(17));
        addSystem(new ThrustSystem(16));
        addSystem(new PhysicsSystem(15, world));
        addSystem(new FlightTerminationSystem(14));
        addSystem(new BindSystem(13));
        addSystem(new CameraSystem(12, camera, 0));
        addSystem(new RenderingSystem(10, new SpriteBatch(), camera));
        addSystem(new PlumeSystem(7));
        addSystem(new DebugRenderSystem(5, camera, world));
    }

    public Entity getRocketEntity() {
        return rocket;
    }
}
