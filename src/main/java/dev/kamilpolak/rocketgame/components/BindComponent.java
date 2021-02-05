package dev.kamilpolak.rocketgame.components;

import dev.kamilpolak.rocketgame.ecs.Entity;
import dev.kamilpolak.rocketgame.ecs.IComponent;

public class BindComponent implements IComponent {
    public final Entity target;

    BindComponent(Entity target) {
        this.target = target;
    }
}
