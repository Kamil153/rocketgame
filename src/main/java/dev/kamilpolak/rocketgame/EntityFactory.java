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
        float width = RenderingSystem.pixelsToMeters(region.getRegionWidth());
        float height = RenderingSystem.pixelsToMeters(region.getRegionHeight());
        Entity rocket = createEntity(x, y, z, angle, region);
        Body body = bodyFactory.createDynamicRectangle(x, y, width, height, angle);
        ThrustComponent thrustComponent = new ThrustComponent();
        thrustComponent.offset.y = -height/2.0f;
        EngineStateComponent engine = new EngineStateComponent();
        engine.running = true;
        rocket.addComponent(thrustComponent);
        rocket.addComponent(engine);
        rocket.addComponent(new FuelComponent());
        rocket.addComponent(new BodyComponent(body));
        rocket.addComponent(new RocketComponent());
        rocket.addComponent(new EngineAngleComponent());
        rocket.addComponent(new ControlComponent());
        return rocket;
    }

    public Entity createRocket(Vector3 position, float angle) {
        return createRocket(position.x, position.y, position.z, angle);
    }

    public Entity createRocket() {
        return createRocket(EntityData.ROCKET_POSITION, EntityData.ROCKET_ANGLE);
    }

    public Entity createRocketPlume(Entity rocket) {
        TextureRegion texture = getTextureRegion(Asset.PLUME_TEXTURE);
        Entity plume = createEntity(0, 0, 0, 0, texture);
        Texture rocketTexture = assets.get(Asset.ROCKET_TEXTURE.getPath());
        float height = RenderingSystem.pixelsToMeters(texture.getRegionHeight());
        float rocketHeight = RenderingSystem.pixelsToMeters(rocketTexture.getHeight());
        float offsetX = 0;
        float offsetY = -height/2.0f - rocketHeight/2.0f;
        BindComponent binding = new BindComponent(rocket, new Vector2(offsetX, offsetY));
        plume.addComponent(new PlumeComponent());
        plume.addComponent(binding);
        return plume;
    }
}
