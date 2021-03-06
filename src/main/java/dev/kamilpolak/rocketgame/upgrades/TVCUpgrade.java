package dev.kamilpolak.rocketgame.upgrades;

import dev.kamilpolak.rocketgame.UpgradeData;
import dev.kamilpolak.rocketgame.components.EngineControlComponent;
import dev.kamilpolak.rocketgame.ecs.Entity;

public class TVCUpgrade extends Upgrade{
    public TVCUpgrade(UpgradeData data) {
        super(data);
    }

    public TVCUpgrade(String name, int price) {
        super(name, price);
    }

    public TVCUpgrade(String name, int price, String description) {
        super(name, price, description);
    }

    @Override
    public void install(Entity rocket) {
        rocket.addComponent(new EngineControlComponent());
    }
}
