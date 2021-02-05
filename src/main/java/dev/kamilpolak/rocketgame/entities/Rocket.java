package dev.kamilpolak.rocketgame.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dev.kamilpolak.rocketgame.components.FuelComponent;
import dev.kamilpolak.rocketgame.components.TextureComponent;
import dev.kamilpolak.rocketgame.components.TransformComponent;
import dev.kamilpolak.rocketgame.ecs.Entity;

public class Rocket extends Entity {
    public Rocket(TextureRegion texture) {
        super();
        addComponent(new FuelComponent());
        addComponent(new TransformComponent());
        TextureComponent textureComponent = new TextureComponent();
        textureComponent.region = texture;
        addComponent(textureComponent);
    }
}
