package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import dev.kamilpolak.rocketgame.components.TextureComponent;
import dev.kamilpolak.rocketgame.components.TransformComponent;
import dev.kamilpolak.rocketgame.ecs.Entity;

public class EntityFactory {
    private final World world;
    private final BodyFactory bodyFactory;
    private final AssetManager assets;

    public EntityFactory(World world, AssetManager assets) {
        this.world = world;
        this.bodyFactory = new BodyFactory(world);
        this.assets = assets;
    }

    public Entity createEntity(float x, float y, float z, TextureRegion texture) {
        TextureComponent textureComponent = new TextureComponent();
        textureComponent.region = texture;
        TransformComponent transformComponent = new TransformComponent();
        transformComponent.position.set(x, y, z);
        Entity entity = new Entity();
        entity.addComponent(textureComponent);
        entity.addComponent(transformComponent);
        return entity;
    }

    private TextureRegion getTextureRegion(Asset asset) {
        Texture texture = assets.get(asset.getPath(), Texture.class);
        return new TextureRegion(texture);
    }
}
