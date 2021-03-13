package dev.kamilpolak.rocketgame.upgrades;

import dev.kamilpolak.rocketgame.UpgradeData;
import dev.kamilpolak.rocketgame.ecs.Entity;

abstract public class Upgrade {
    private final String name;
    private final int price;
    private final String description;

    public Upgrade(UpgradeData data) {
        this(data.getName(), data.getPrice(), data.getDescription());
    }

    public Upgrade(String name, int price) {
        this(name, price, "");
    }

    public Upgrade(String name, int price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    abstract public void install(Entity rocket);
}
