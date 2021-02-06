package dev.kamilpolak.rocketgame.components;

import com.badlogic.gdx.math.Vector2;
import dev.kamilpolak.rocketgame.ecs.Entity;
import dev.kamilpolak.rocketgame.ecs.IComponent;

public class BindComponent implements IComponent {
    public final Entity target;
    public final Vector2 offset;

    public BindComponent(Entity target, Vector2 offset) {
        this.target = target;
        this.offset = offset.cpy();
    }

    public BindComponent(Entity target) {
        this(target, new Vector2());
    }
}
