package dev.kamilpolak.rocketgame;

public class Upgrade {
    private final String name;
    private final int price;
    private final String description;
    private boolean installed = false;

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

    public boolean isInstalled() {
        return installed;
    }

    public void setInstalled(boolean installed) {
        this.installed = installed;
    }
}
