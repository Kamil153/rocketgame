package dev.kamilpolak.rocketgame;

public class UpgradeData {
    private final String className;
    private final String name;
    private final int price;
    private final String description;

    public UpgradeData(String className, String name, int price, String description) {
        this.className = className;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String getClassName() {
        return className;
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
}
