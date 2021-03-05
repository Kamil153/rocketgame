package dev.kamilpolak.rocketgame.upgrades;

import dev.kamilpolak.rocketgame.components.FinsComponent;
import dev.kamilpolak.rocketgame.ecs.Entity;

public class FinsUpgrade extends Upgrade {
    public FinsUpgrade(String name, int price) {
        super(name, price);
    }

    public FinsUpgrade(String name, int price, String description) {
        super(name, price, description);
    }

    @Override
    public void install(Entity rocket) {
        rocket.addComponent(new FinsComponent());
    }
}
