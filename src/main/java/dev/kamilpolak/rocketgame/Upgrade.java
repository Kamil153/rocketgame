package dev.kamilpolak.rocketgame;

public class Upgrade {
    private final String name;
    private final int price;

    public Upgrade(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
