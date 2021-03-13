package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.physics.box2d.Body;
import dev.kamilpolak.rocketgame.components.BodyComponent;
import dev.kamilpolak.rocketgame.components.FuelComponent;
import dev.kamilpolak.rocketgame.components.TransformComponent;
import dev.kamilpolak.rocketgame.ecs.Entity;
import dev.kamilpolak.rocketgame.models.Rocket;

public class RocketController {
    private Entity rocketEntity;
    private Rocket rocket;

    public RocketController(Entity rocketEntity) {
        this.rocketEntity = rocketEntity;
        rocket = new Rocket();
    }

    public void update() {
        TransformComponent transform = rocketEntity.getComponent(TransformComponent.class);
        float fuel = rocketEntity.getComponent(FuelComponent.class).fuel;
        Body body = rocketEntity.getComponent(BodyComponent.class).body;
        rocket.setPosition(transform.position.x, transform.position.y);
        rocket.setFuel(fuel);
        rocket.setVelocity(body.getLinearVelocity().len());
    }

    public Rocket getRocket() {
        return rocket;
    }
}
