package dev.kamilpolak.rocketgame.entities;

import dev.kamilpolak.rocketgame.components.FuelComponent;
import dev.kamilpolak.rocketgame.components.TransformComponent;
import dev.kamilpolak.rocketgame.ecs.Entity;

public class Rocket extends Entity {
    public Rocket() {
        super();
        addComponent(new FuelComponent());
        addComponent(new TransformComponent());
    }
}
