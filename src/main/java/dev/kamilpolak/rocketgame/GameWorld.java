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

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class GameWorld extends Engine {
    private final World world;
    private final BodyFactory bodyFactory;
    private final EntityFactory entityFactory;
    private final Entity rocket;
    private final AssetManager assets;

    public GameWorld(AssetManager assets, OrthographicCamera camera) {
        world = new World(new Vector2(0, -10), true);
        bodyFactory = new BodyFactory(world);
        entityFactory = new EntityFactory(world, assets);
        rocket = entityFactory.createRocket();
        this.assets = assets;
        addEntity(rocket);
        addEntity(entityFactory.createEarth());
        addEntity(entityFactory.createRocketPlume(rocket));
        addEntity(entityFactory.createLaunchpad());

        addRepeatingEntities(Asset.EARTH_TEXTURE, 20, entityFactory::createEarth);
        addRepeatingEntities(Asset.TREES_TEXTURE, 10, entityFactory::createTrees);
        addRepeatingEntities(Asset.MOUNTAINS_TEXTURE, 10, entityFactory::createMountains);

        addSystem(new FuelSystem(18));
        addSystem(new RocketTurnSystem(17));
        addSystem(new ThrustSystem(16));
        addSystem(new PhysicsSystem(15, world));
        addSystem(new FlightTerminationSystem(14));
        addSystem(new BindSystem(13));
        addSystem(new CameraSystem(12, camera, 0));
        addSystem(new RenderingSystem(10, new SpriteBatch(), camera));
        addSystem(new PlumeSystem(7));
        //addSystem(new DebugRenderSystem(5, camera, world));
    }

    private void addRepeatingEntities(Asset texture, int quantity, Function<Float, Entity> factory) {
        float textureWidth = assets.get(texture.getPath(), Texture.class).getWidth();
        float x = 0;
        for(int i = 1; i < quantity; i++) {
            x = -x;
            if(i % 2 == 1) {
                x += textureWidth;
            }
            addEntity(factory.apply(x));
        }
    }

    public Entity getRocketEntity() {
        return rocket;
    }
}
