package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import dev.kamilpolak.rocketgame.components.*;
import dev.kamilpolak.rocketgame.ecs.Entity;
import dev.kamilpolak.rocketgame.systems.RenderingSystem;

public class EntityFactory {
    private final World world;
    private final BodyFactory bodyFactory;
    private final AssetManager assets;

    public EntityFactory(World world, AssetManager assets) {
        this.world = world;
        this.bodyFactory = new BodyFactory(world);
        this.assets = assets;
    }

    public Entity createEntity(float x, float y, float z, float angle, TextureRegion texture) {
        TextureComponent textureComponent = new TextureComponent();
        textureComponent.region = texture;
        TransformComponent transformComponent = new TransformComponent();
        transformComponent.position.set(x, y, z);
        transformComponent.rotation = angle;
        Entity entity = new Entity();
        entity.addComponent(textureComponent);
        entity.addComponent(transformComponent);
        return entity;
    }

    private TextureRegion getTextureRegion(Asset asset) {
        Texture texture = assets.get(asset.getPath(), Texture.class);
        return new TextureRegion(texture);
    }

    public Entity createRocket(float x, float y, float z, float angle) {
        TextureRegion region = getTextureRegion(Asset.ROCKET_TEXTURE);
        float width = RenderingSystem.getForegroundLength(region.getRegionWidth());
        float height = RenderingSystem.getForegroundLength(region.getRegionHeight());
        Entity rocket = createEntity(x, y, z, angle, region);
        Body body = bodyFactory.createBody(x, y, angle, assets.get(Asset.ROCKET_BODY.getPath()));
        ThrustComponent thrustComponent = new ThrustComponent();
        thrustComponent.offset.y = -height/2.0f;
        EngineStateComponent engine = new EngineStateComponent();
        engine.running = false;
        rocket.addComponent(thrustComponent);
        rocket.addComponent(engine);
        rocket.addComponent(new FuelComponent());
        rocket.addComponent(new BodyComponent(body));
        rocket.addComponent(new RocketComponent());
        return rocket;
    }

    public Entity createRocket(Vector3 position, float angle) {
        return createRocket(position.x, position.y, position.z, angle);
    }

    public Entity createRocket() {
        return createRocket(EntityData.ROCKET_POSITION, EntityData.ROCKET_ANGLE);
    }

    public Entity createRocketPlume(float z, Entity rocket) {
        TextureRegion texture = getTextureRegion(Asset.PLUME_TEXTURE);
        Entity plume = createEntity(0, 0, z, 0, texture);
        Texture rocketTexture = assets.get(Asset.ROCKET_TEXTURE.getPath());
        float height = RenderingSystem.getForegroundLength(texture.getRegionHeight());
        float rocketHeight = RenderingSystem.getForegroundLength(rocketTexture.getHeight());
        float offsetX = 0;
        float offsetY = -height/2.0f - rocketHeight/2.0f;
        BindComponent binding = new BindComponent(rocket, new Vector2(offsetX, offsetY));
        plume.addComponent(new PlumeComponent());
        plume.addComponent(binding);
        return plume;
    }

    public Entity createRocketPlume(Entity rocket) {
        return createRocketPlume(EntityData.PLUME_Z, rocket);
    }

    public Entity createEarth(float x, float y, float z, float rotation) {
        TextureRegion region = getTextureRegion(Asset.EARTH_TEXTURE);
        float width = RenderingSystem.getForegroundLength(region.getRegionWidth());
        float height = RenderingSystem.getForegroundLength(region.getRegionHeight());
        Entity earth = createEntity(x, y, z, rotation, region);
        Body body = bodyFactory.createStaticRectangle(x, y, width, height, rotation);
        earth.addComponent(new BodyComponent(body));
        return earth;
    }

    public Entity createEarth(float x) {
        return createEarth(x, EntityData.EARTH_Y, EntityData.EARTH_Z, 0);
    }

    public Entity createEarth() {
        return createEarth(0);
    }

    public Entity createLaunchpad(float x, float y, float z, float rotation) {
        TextureRegion region = getTextureRegion(Asset.LAUNCHPAD_TEXTURE);
        Entity launchpad = createEntity(x, y, z, rotation, region);
        Body body = bodyFactory.createBody(x, y, rotation, assets.get(Asset.LAUNCHPAD_BODY.getPath()));
        launchpad.addComponent(new BodyComponent(body));
        return launchpad;
    }

    public Entity createLaunchpad(Vector3 position, float rotation) {
        return createLaunchpad(position.x, position.y, position.z, rotation);
    }

    public Entity createLaunchpad() {
        return createLaunchpad(EntityData.LAUNCHPAD_POSITION, EntityData.LAUNCHPAD_ANGLE);
    }

    public Entity createTrees(float x, float y, float z, float rotation) {
        TextureRegion region = getTextureRegion(Asset.TREES_TEXTURE);
        return createEntity(x, y, z, rotation, region);
    }

    public Entity createTrees(float x) {
        return createTrees(x, EntityData.TREES_Y, EntityData.TREES_Z, 0);
    }

    public Entity createTrees() {
        return createTrees(0);
    }

    public Entity createMountains(float x, float y, float z, float rotation) {
        TextureRegion region = getTextureRegion(Asset.MOUNTAINS_TEXTURE);
        return createEntity(x, y, z, rotation, region);
    }

    public Entity createMountains(float x) {
        return createMountains(x, EntityData.MOUNTAINS_Y, EntityData.MOUNTAINS_Z, 0);
    }

    public Entity createMountains() {
        return createMountains(0);
    }
}
