package dev.kamilpolak.rocketgame.components;

import dev.kamilpolak.rocketgame.ecs.IComponent;

public class FuelComponent implements IComponent {
    public float maxFuel = 100.0f;
    public float fuel = 0.0f;
    public float fuelConsumption = 1.0f;
}
